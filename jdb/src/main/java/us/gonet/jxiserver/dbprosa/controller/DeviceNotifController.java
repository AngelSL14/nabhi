package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.DeviceNotifEntity;
import us.gonet.jxiserver.dbprosa.repository.DeviceNotifRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.serverutils.model.jdb.DeviceNotif;

import java.sql.Timestamp;

@RestController
@RequestMapping("deviceNotif")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class DeviceNotifController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DeviceNotifRepository deviceNotifRepository;

    @Autowired
    ResponseFactory responseFactory;

    private static final String[] ALLOWED_FIELDS = new String[] {"idDeviceNotif", "fkAtmTerminalId", "message"};

    @InitBinder( "DeviceNotif" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("")
    public ResponseWrapper<DeviceNotif> saveDeviceNotf(@RequestBody DeviceNotif deviceNotif)
    {
        DeviceNotifEntity entity = new DeviceNotifEntity();
        entity.setFkAtmTerminalId(deviceNotif.getFkAtmTerminalId());
        entity.setMessage(deviceNotif.getMessage());
        return responseFactory.buildResponseSingle(deviceNotifRepository.save(entity), DeviceNotif.class, deviceNotif.toString());
    }

    @ResponseBody
    @PostMapping("/write/{atm}")
    public ResponseWrapper<DeviceNotif> writeDeviceNotification(@PathVariable String atm, @RequestBody String message)
    {
        DeviceNotifEntity entity = new DeviceNotifEntity();
        entity.setFkAtmTerminalId(atm);
        entity.setMessage(message);
        entity.setWriteDate(new Timestamp(System.currentTimeMillis()));
        return responseFactory.buildResponseSingle(deviceNotifRepository.save(entity), DeviceNotif.class, atm);
    }
}
