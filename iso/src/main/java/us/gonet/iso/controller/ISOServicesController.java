package us.gonet.iso.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.iso.business.IISOServices;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.iso.ServicesInformation;

@RestController
@RequestMapping ( "iso" )
@CrossOrigin ( origins = { "*" } )
@Api ( tags = " ISO Services " , value = " ISO Services ")
public class ISOServicesController {

    @Autowired
    @Qualifier ( "isoservices" )
    IISOServices business;

    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age","is_admin" };

    @InitBinder("ISO")
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }

    @ResponseBody
    @GetMapping ( "/logon" )
    public ResponseWrapper < ServicesInformation > sendLogon () {
        return business.sendLogon();
    }

    @ResponseBody
    @GetMapping ( "/logoff" )
    public ResponseWrapper < ServicesInformation > sendLogoff () {
        return business.sendLogoff();
    }

    @ResponseBody
    @PostMapping ( "/sampleService" )
    public ResponseWrapper < ISO > sendRequestsampleService ( @RequestBody ISO iso ) {
        return business.sampleService( iso );
    }

    @ResponseBody
    @GetMapping ( "/statusSocket" )
    public ResponseWrapper < ServicesInformation > statusSocket () {
        return business.statusSocket();
    }

    @ResponseBody
    @GetMapping ( "/onsocket" )
    public ResponseWrapper < ServicesInformation > turnOnSocket () {
        return business.turnOnSocket();
    }

    @ResponseBody
    @GetMapping ( "/offsocket" )
    public ResponseWrapper < ServicesInformation > turnOffSocket () {
        return business.turnOffSocket();
    }

    @ResponseBody
    @GetMapping ( "/echo" )
    public ResponseWrapper < ServicesInformation > echo () {
        return business.echo();
    }

    @ResponseBody
    @PostMapping ( "/process" )
    public ResponseWrapper < ISO > sendRequest ( @RequestBody ISO iso ) {//RECIBE SOLO POJOS (CLASE SIMPLE)
        return business.sendRequest( iso );
    }

}
