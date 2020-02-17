package us.gonet.jxiserver.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.atm.notification.SaveNotification;
import us.gonet.jxiserver.business.IAtmNotificationsBusiness;
import us.gonet.jxiserver.catalog.DevicesCat;
import us.gonet.jxiserver.codesatm.*;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.model.CashUnitCounters;
import us.gonet.jxiserver.model.request.AtmNotificationModel;
import us.gonet.jxiserver.reversal.Reversal;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;
import us.gonet.serverutils.model.jdb.DeviceNotif;
import us.gonet.serverutils.model.jdb.DeviceStatus;

import java.util.ArrayList;
import java.util.List;

@Component("notifBus")
public class AtmNotificationsBusinessImpl implements IAtmNotificationsBusiness {

    private static final Logger LOG = LoggerFactory.getLogger(AtmNotificationsBusinessImpl.class);

    @Autowired
    @Qualifier("reversal")
    private Reversal reversalPartial;

    @Autowired
    private RestRequestFactory restRequestFactory;

    @Autowired
    private JournalWritter journalWritter;

    @Autowired
    private SaveNotification saveNotification;

    @Override
    public void sendToDevice(AtmNotificationModel model)
    {
        switch(model.getDevice())
        {
            case "IDC":
                try
                {
                    IdcEvent event = IdcEvent.getEventFromCode((int) model.getExtra().get("msg1"));
                    deviceEvent(model, event);
                }
                catch (UnsupportedOperationException e)
                {
                    try{
                        IdcResponse response = IdcResponse.getResponseByCode((int) model.getExtra().get("msg1"));
                        deviceResponse(model, response, "CARDREADER");
                    }
                    catch (UnsupportedOperationException ex)
                    {
                        String msg = "El codigo IDC "
                                +IdcResponse.getResponseByCode((int) model.getExtra().get("msg1"))+
                                " no esta soportado";
                        LOG.warn(msg);
                    }

                }
                break;
            case "PTR":
                break;
            case "CDM":
                try
                {
                    CdmEvent event = CdmEvent.getEventFromCode((int) model.getExtra().get("msg1"));
                    cdmEvent(model, event);
                }
                catch (UnsupportedOperationException e)
                {
                    cdmResponse(model);
                }
                break;
            case "PIN":
                try
                {
                    PinEvent event = PinEvent.getEventFromCode((int) model.getExtra().get("msg1"));
                    deviceEvent(model, event);
                }
                catch (UnsupportedOperationException e)
                {
                    PINResponse response = PINResponse.getResponseByCode((int) model.getExtra().get("msg1"));
                    deviceResponse(model, response, "PINPAD");
                }
                break;
            case "SIU":
                break;
            default:
                break;
        }
    }

    public void cdmEvent(AtmNotificationModel model, AtmEvent event)
    {
        String actions = event.getActions();
        if(actions.substring(0,1).equals("1"))
        {
            saveNotification.saveJournal(model, event);
        }
        if(actions.substring(1,2).equals("1"))
        {
            //Se agrega el cambio de contadores
        }
        if(actions.substring(2,3).equals("1"))
        {
            setDeviceNok(model, "DISPENSER");
        }
        if(actions.substring(3,4).equals("1"))
        {
            saveNotification.saveDeviceNotif(model, event.name());
        }
    }

    public void cdmResponse(AtmNotificationModel model) {
        try {
            dispNotif(model);

        } catch (ServerException e) {
            LOG.warn(e.getErrors().toString());
        }
    }

    public void deviceEvent(AtmNotificationModel model, AtmEvent event)
    {
        saveNotification.saveJournal(model, event);
        saveNotification.saveDeviceNotif(model, event.name());
    }

    public void deviceResponse(AtmNotificationModel model, AtmResponse response, String device)
    {
        if(response.getSeverity().equals("-1"))
        {
            saveNotification.saveDeviceNotif(model, response.getMessage());
        }
        else if(response.getSeverity().equals("-2"))
        {
            setDeviceNok(model,device);
            saveNotification.saveDeviceNotif(model, response.getMessage());
        }
        else if(response.getSeverity().equals("00"))
        {
            saveNotification.saveJournalResponse(model, response);
        }
    }

    public void setDeviceNok(AtmNotificationModel model, String device)
    {
        String service ="".concat("/update").concat("/").concat("{device}")
                .concat("/").concat("{termId}").concat("/").concat("{status}");
        try {
            restRequestFactory.buildRestRequest(service, DeviceStatus.class,
                    "jdb", "No se pudo guardar el status", "post","", device, model.getTermId(), "NOK");
        } catch (ServerException e) {
            LOG.warn(e.getErrors().toString());
        }
    }


//==============================================================
    @Override
    public boolean updateDevice(AtmNotificationModel model) {
        boolean update = false;
        try {
            DeviceNotif dev = new DeviceNotif();
            dev.setFkAtmTerminalId(model.getTermId());
            dev.setMessage(model.getExtra().get("msg1").toString());
            restRequestFactory.buildRestRequest("/deviceNotif", DeviceNotif.class, "jdb",
                    "No se guardo notificacion", "post", dev, null);
            update = true;
        } catch (ServerException e) {
            LOG.error("Error en la peticion entre servicios");
        }
        return update;
    }

    @Override
    public boolean dispNotif(AtmNotificationModel model) throws ServerException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            final String code = model.getExtra().get("msg1").toString(); // el codigo xfs: exitoso o  no(error)
            List<String> counters = (List<String>) model.getExtra().get("msg3"); //Contadores fisicos xfs
            List<CashUnitCounters> listCounters = new ArrayList<>();

            String serviceAtm = "".concat("/caseteros").concat("/").concat("{ip}");
            List<Casetero> caseteros = restRequestFactory.buildRestRequest(serviceAtm, Casetero.class, "jdb",
                    "No se encontraron los caseteros: "+model.getIp(), "get", "",
                    model.getIp()).getBody();

            for(String json: counters){
                CashUnitCounters nuevoAfter = mapper.convertValue(json, CashUnitCounters.class);
                listCounters.add(nuevoAfter);
            }
            if(code.contains("-") && compareCode(code))
            {
                int total = compareCounters(model, caseteros);
                String valor = String.valueOf(total);
                String message = "Cash dispensed " + valor;
                LOG.error(message);
                journalWritter.writeJournal(model.getTermId(), "Error al dispensar se procede a realizar reverso");
                makeReversal(total, model);
            }
            else
            {
                journalWritter.writeJournal(model.getTermId(), CdmResponse.getResponseByCode(Integer.parseInt(code)).getMessage());
            }
            model.setDevice(DevicesCat.DISPENSER.name());
                return findAndUpdateCashUnits(listCounters, caseteros);

        } catch (ServerException e) {
            List<ErrorWS> errorWS = new ArrayList<>();
            errorWS.add(new ErrorWS("-JXI02", "Error en la autorizacion del retiro"));
            errorWS.addAll( e.getErrors());
            throw new ServerException("Error en la autorizacion del retiro", errorWS);

        }
    }

    private boolean findAndUpdateCashUnits(List<CashUnitCounters> cashUnit, List<Casetero> caseteros) throws ServerException {
        if (caseteros != null) {
            List<Casetero> entities = new ArrayList<>();
            int i = 0;
            int deviceId = 0;
            for (Casetero e : caseteros)
            {
                e.setIncremento((int) cashUnit.get(i).getInitialCount());
                e.setDispensados(cashUnit.get(i).getDispensedCount());
                e.setPresentados(cashUnit.get(i).getPresentedCount());
                e.setReject(cashUnit.get(i).getRejectedCount());
                deviceId = e.getDevice().getId();
                entities.add(e);
                i++;
            }
            String serviceCasetero = "".concat("/caseteros").concat("/").concat("{deviceId}").concat("/").concat("batch");

            restRequestFactory.buildRestRequest
                    (serviceCasetero, Casetero.class, "jdb","Error al actualizar los caseteros",
                            "post", entities, String.valueOf(deviceId));

            return true;
        }
        return false;
    }

    private boolean compareCode(String code){
        switch(code){
            case "-301":
            case "-302":
            case "-304":
            case "-305":
            case "-306":
            case "-308":
            case "-317":
            case "-333":
            case "-334":
            case "-312":
            case "-313":
            case "-320":
            case "-321":
            case "-322":
            case "-14":
                return true;
            default:
                return  false;
        }
    }

    private int compareCounters(AtmNotificationModel model, List<Casetero> caseteros){
        List<CashUnitCounters> resultado = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 5; i++)
        {

            CashUnitCounters nuevoBefore = mapper.convertValue(((List<String>) model.getExtra().get("msg4")).get(i), CashUnitCounters.class);
            CashUnitCounters nuevoAfter = mapper.convertValue(((List<String>) model.getExtra().get("msg3")).get(i), CashUnitCounters.class);
            if ((nuevoAfter.getStatus().equals("OK") || nuevoAfter.getStatus().equals("LOW")) && nuevoAfter.getType().equals("BILLCASSETTE"))
            {
                CashUnitCounters nuevo = new CashUnitCounters();
                nuevo.setNumber(nuevoAfter.getNumber() - nuevoBefore.getNumber());
                nuevo.setDispensedCount(nuevoAfter.getDispensedCount() - nuevoBefore.getDispensedCount());
                nuevo.setPresentedCount(nuevoAfter.getPresentedCount() - nuevoBefore.getPresentedCount());//LA BUENA
                nuevo.setRetractedCount(nuevoAfter.getRejectedCount() - nuevoBefore.getRejectedCount());
                resultado.add(nuevo);
            }
        }
        int total = 0;
        for (int x = 0; x < resultado.size(); x++) {
            total += caseteros.get(x).getDenominacion() * resultado.get(x).getPresentedCount();
        }
        return total;
    }

    private void makeReversal(int total, AtmNotificationModel model)
    {
        reversalPartial.generateReversal(model, total);

        if (model.getExtra().containsKey("msg3")) {
            model.getExtra().remove("msg3");
        }
        if (model.getExtra().containsKey("msg4")) {
            model.getExtra().remove("msg4");
        }
        journalWritter.writeDeviceNotif(model.getTermId(), model.getExtra().get("msg2").toString());

    }
}


