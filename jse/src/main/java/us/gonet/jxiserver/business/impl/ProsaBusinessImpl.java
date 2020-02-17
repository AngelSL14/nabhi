package us.gonet.jxiserver.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.business.IProsaBusiness;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.i8583.manager.TransactionManager;
import us.gonet.jxiserver.i8583.misc.ISOUtils;
import us.gonet.jxiserver.model.request.*;
import us.gonet.jxiserver.model.response.GenericProcess;
import us.gonet.jxiserver.utils.UrlFilter;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.jdb.Atm;
import us.gonet.serverutils.model.jdb.BillsModel;
import us.gonet.serverutils.model.jdb.SurchargeModel;
import us.gonet.utils.STMTDecoder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Component("prsBus")
public class ProsaBusinessImpl implements IProsaBusiness {


    @Autowired @Qualifier("manager")
    private TransactionManager transactionManager;

    @Autowired
    private RestRequestFactory restRequestFactory;

    @Autowired
    private ISOUtils isoUtils;

    @Autowired
    private JournalWritter journalWritter;
    private static final Logger LOG = LoggerFactory.getLogger( ProsaBusinessImpl.class );

    private static final String ERROR_4 = "Not Aproved";
    private static final String ERROR_3 = "Invalid Response";


    private static final String WITHD_ERROR = "Error en la autorizacion del retiro";
    private static final String INQ_ERROR = "Error en la autorizacion de la consulta de Saldo";
    private static final String LTRX_ERROR = "Error en la autorizacion de la consulta de Movimientos";
    private static final String NCH_ERROR = "Error en la autorizacion del cambio de NIP";
    private static final String GES_ERROR = "Error en la autorizacion de la recarga de saldo";
    private static final String COMMI_ERROR = "Error al obtener la comision";
    @Autowired
    private UrlFilter urlFilter;

    @Override
    public ResponseWrapper<BillsModel> cashWithAuth(CashWithdrawalModel generic) throws ServerException {
        ResponseWrapper<BillsModel> bills;
        try {
            journalWritter.writeJournal(generic.getTermId(), "Esperando Autorizacion del retiro de efectivo");
            ATMResponseModel atmResponseModel = transactionManager.performTransaction(generic, "01");
            saveAuthorizationInfo(atmResponseModel, generic);
            String errorMessage = "Error al calcular el retiro";
            String service = "".concat("/adp").concat("/getBills");
            Generic bod = CashWithdrawalModel.builder()
                    .withCashWithAmount(urlFilter.sanitizeString(generic.getCashWithAmount()))
                    .withIp(urlFilter.sanitizeString(generic.getIp()))
                    .withTxCommission(urlFilter.sanitizeString(generic.getTxCommission()))
                    .withEmv(urlFilter.sanitizeEmv(generic.getEmv()))
                    .withNip(urlFilter.sanitizeString(generic.getNip()))
                    .withTermId(urlFilter.sanitizeString(generic.getTermId()))
                    .withTrack(urlFilter.sanitizeString(generic.getTrack()))
                    .withTipoCuenta(urlFilter.sanitizeString(generic.getTipoCuenta()))
                    .build();
            bills = restRequestFactory.buildRestRequest(service, BillsModel.class,
                    "adp", errorMessage, "post", bod, null);
            bills.setCode("200");
            journalWritter.writeJournal(generic.getTermId(), "Retiro de efectivo autorizado, BufferAmount: "+bills.getBody().get(0).getBills());

        } catch (ServerException e) {
            writeErrorJournal(e, generic, WITHD_ERROR);
            List<ErrorWS> errorWS = new ArrayList<>();
            errorWS.addAll( e.getErrors());
            errorWS.add(new ErrorWS("-JXI02", WITHD_ERROR));
            throw new ServerException(WITHD_ERROR, errorWS);
        }

        return bills;
    }

    @Override
    public ResponseWrapper<Generic> getCommission(AtmInfo generic) throws ServerException {
        ResponseWrapper<Generic> response = new ResponseWrapper<>();
        try {
            AtmInfo bod = new AtmInfo();
            bod.setIp(urlFilter.sanitizeString(generic.getIp()));
            bod.setTrack(urlFilter.sanitizeString(generic.getTrack()));
            bod.setTransactionCode(urlFilter.sanitizeString(generic.getTransactionCode()));
            ResponseWrapper<SurchargeModel> commission = restRequestFactory.buildRestRequest("/surchage/sendSurchage",
                    SurchargeModel.class, "srh", COMMI_ERROR, "post", bod, null);
            SurchargeModel surcharge =commission.getBody().get(0);
            response.setCode("200");
            response.addBody(Generic.builder().withTxCommission(surcharge.getSurcharge()).build());
        } catch (ServerException e) {
            List<ErrorWS> errorWS = new ArrayList<>();
            errorWS.add(new ErrorWS("-JXI03", COMMI_ERROR));
            errorWS.addAll( e.getErrors());
            throw new ServerException(COMMI_ERROR, errorWS);
        }

        return response;
    }


    @Override
    public ResponseWrapper<Map<String, String>> balInquiryAuth(Generic generic) throws ServerException {
        ResponseWrapper<Map<String, String>> response = new ResponseWrapper();
        try {
            journalWritter.writeJournal(generic.getTermId(), "Esperando Autorizacion de la consulta de saldo");
            ATMResponseModel atmResponseModel = transactionManager.performTransaction(generic, "31");
            saveAuthorizationInfo(atmResponseModel, generic);

            double oldBalance = Double.parseDouble( atmResponseModel.getMessage().getDataElements().get( 43 ).getContentField().substring( 13,25 ) ) /100;

            DecimalFormat f = new DecimalFormat("$#,###,##0.00" );

            String cuenta = atmResponseModel.getMessage().getDataElements().get( 101 ).getContentField();
            String monto = f.format( oldBalance );
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(new Date());
            Map<String, String> datos = new HashMap<>();
            String numCuenta = isoUtils.obfuscateCardNumber(cuenta);

            datos.put("monto", monto);
            datos.put("fecha", date);
            datos.put("numCuenta", numCuenta);

            response.setCode("200");
            response.addBody(datos);
            journalWritter.writeJournal(generic.getTermId(), "Consulta de saldo autorizada");

        } catch (ServerException e) {
            writeErrorJournal(e, generic, INQ_ERROR);
            List<ErrorWS> errorWS = e.getErrors();
            errorWS.add(new ErrorWS("-JXI06", INQ_ERROR));
            throw new ServerException(INQ_ERROR, errorWS);
        }
        return response;
    }




    @Override
    public ResponseWrapper<String> listTrx(Generic generic) throws ServerException {
        ResponseWrapper<String> response = new ResponseWrapper();
        try {
            ATMResponseModel atmResponseModel = transactionManager.performTransaction(generic, "94");
            //Guardar Recibo y cadena Iso

            saveAuthorizationInfo(atmResponseModel, generic);

            //Llenar response
            List<String> trxList = new STMTDecoder( ).decode( atmResponseModel.getMessage().getDataElements().get( 124 ).getContentField());
            response.setCode("200");
            response.setBody(trxList);
            journalWritter.writeJournal(generic.getTermId(), "Consulta de movimientos autorizada");
        } catch (ServerException e) {
            writeErrorJournal(e, generic, LTRX_ERROR);
            List<ErrorWS> errorWS = e.getErrors();
            errorWS.add(new ErrorWS("-JXI11", LTRX_ERROR));
            throw new ServerException(LTRX_ERROR, errorWS);
        }
        return response;
    }


    @Override
    public ResponseWrapper<GenericProcess> changePin(ChangeNipModel generic) throws ServerException {
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            ATMResponseModel responseModel = transactionManager.performTransaction(generic, "96");
            wrapper.setCode("200");
            saveAuthorizationInfo(responseModel, generic);
            journalWritter.writeJournal(generic.getTermId(), "Cambio de NIP autorizado");
            return wrapper;
        } catch (ServerException e) {
            writeErrorJournal(e, generic, NCH_ERROR);
            List<ErrorWS> wsList = e.getErrors();
            wsList.add(new ErrorWS("-JXI12", NCH_ERROR));
            throw new ServerException(NCH_ERROR, wsList);
        }
    }

    @Override
    public ResponseWrapper<GenericProcess> genericSale(GenericSaleModel generic) throws  ServerException {
        ResponseWrapper response = new ResponseWrapper();
        try {
            ATMResponseModel atmResponseModel = transactionManager.performTransaction(generic, "65");
            //Guardar Recibo y cadena Iso
            saveAuthorizationInfo(atmResponseModel, generic);
            //Llenar response
            response.setCode("200");
            journalWritter.writeJournal(generic.getTermId(), "Recarga de tiempo aire autorizada");

        } catch (ServerException e) {
            writeErrorJournal(e, generic, GES_ERROR);
            List<ErrorWS> errorWS = e.getErrors();
            errorWS.add(new ErrorWS("-JXI13", GES_ERROR));
            throw new ServerException(GES_ERROR, errorWS);
        }
        return response;
    }

    private void saveAuthorizationInfo(ATMResponseModel atmResponseModel, Generic generic) throws ServerException {

        String errorMessage = "Error al obtener el registro atm: "+generic.getIp();
        ResponseWrapper<Atm> res = restRequestFactory.buildRestRequest("/atm/{ip}", Atm.class, "jdb",
                errorMessage, "get", "", generic.getIp());
        Atm atm = res.getBody().get(0);
        atm.setReceipt(atmResponseModel.getReceipt());
        atm.setLastTrx(atmResponseModel.getMessage().getMessage());
        restRequestFactory.buildRestRequest("/atm", Atm.class, "jdb", "Error al actualizar el registro",
                "post", atm, "");

    }

    private void writeErrorJournal(ServerException e, Generic generic, String operation) {
        LOG.error("Error en la peticion entre servicios");

        if (e.getMessage().equals(ERROR_4)) {
            journalWritter.writeJournal(generic.getTermId(), operation +" : "+ e.getErrors().get(0).getErrorMessage());
        } else if (e.getMessage().equals(ERROR_3)) {
            journalWritter.writeJournal(generic.getTermId(), operation+" : respuesta invalida del autorizador - "+ e.getErrors().get(0).getErrorMessage());
        }
    }
}