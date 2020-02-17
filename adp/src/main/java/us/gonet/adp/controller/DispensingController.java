package us.gonet.adp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.adp.business.IDispensingAlg;
import us.gonet.adp.model.BillsModel;
import us.gonet.adp.model.RequestModel;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;


@RestController
@RequestMapping( "adp" )
@CrossOrigin( origins = { "*" }, maxAge = 6000 )
public class DispensingController {

    @Autowired @Qualifier("dispAlg")
    IDispensingAlg algorithm;

    private static final String[] ALLOWED_FIELDS = new String[] { "ip", "cashWithAmount" };

    @InitBinder( "RequestModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @PostMapping("/getBills")
    public ResponseWrapper<BillsModel> getTotalBills(@RequestBody RequestModel model)
    {
        ResponseWrapper<BillsModel> response;
        try {
            response = algorithm.dispenseFourUnits(model.getIp(), Integer.parseInt(model.getCashWithAmount()));
        } catch (ServerException e) {
            response = new ResponseWrapper<>();
            response.setCode("01");
            response.addAllError(e.getErrors());
            response.addError(new ErrorWS(e.getMessage(), "Error al calcular el monto a dispensar"));
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/getMinAmount")
    public ResponseWrapper<Casetero> getMinAmount(@RequestBody RequestModel model)
    {
        ResponseWrapper<Casetero> response;
        try {
            response = algorithm.getMinAmount(model.getIp());
        } catch (ServerException e) {
            response = new ResponseWrapper<>();
            response.setCode("01");
            response.addAllError(e.getErrors());
            response.addError(new ErrorWS(e.getMessage(), "Error al calcular el monto a dispensar"));
        }
        return response;
    }
}
