package us.gonet.jxiserver.dbprosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.business.IDeviceBusiness;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.model.jdb.Device;

import java.util.List;

@RestController
@RequestMapping("device")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class DeviceController {

    @Autowired
    IDeviceBusiness deviceBusinessImpl;


    private static final String[] ALLOWED_FIELDS = new String[] {"idDevice", "type"};

    @InitBinder( "Device" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/batch")
    public ResponseWrapper<Device> saveBatch(@RequestBody List<Device> buttons)
    {
        return deviceBusinessImpl.saveBatch(buttons);
    }

}
