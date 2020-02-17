package us.gonet.jxiserver.dbprosa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.DeviceStatusEntity;
import us.gonet.jxiserver.dbprosa.repository.DeviceStatusRepository;
import us.gonet.jxiserver.dbprosa.repository.lite.DeviceStatusRepositoryLite;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.DeviceStatus;
import us.gonet.serverutils.model.jdb.lite.DeviceStatusLite;

@RestController
@RequestMapping("deviceStatus")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class DeviceStatusController {

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    DeviceStatusRepository deviceStatusRepository;

    @Autowired
    DeviceStatusRepositoryLite deviceStatusRepositoryLite;


    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] {"id", "atm", "status", "device"};

    @InitBinder( "DeviceStatus" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/update/{device}/{atm}/{status}")
    public ResponseWrapper<DeviceStatus> updateStatus(@PathVariable String device, @PathVariable String atm, @PathVariable String status)
    {
        DeviceStatusEntity entity = null;
        try {
            entity = deviceStatusRepository.findByAtmIdAndDeviceType(streamFilter.sanitizeString(atm),
                    streamFilter.sanitizeString(device));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        entity.setStatus(status);
        return responseFactory.buildResponseSingle(deviceStatusRepository.save(entity), DeviceStatus.class, device, atm, status);
    }


    @ResponseBody
    @GetMapping("/{atmId}")
    public ResponseWrapper<DeviceStatusLite> getStatusbyAtm(@PathVariable String atmId)
    {
        try {
            return responseFactory.buildResponseList(deviceStatusRepositoryLite.findByAtm(streamFilter.sanitizeString(atmId)), DeviceStatusLite.class, atmId);
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
    }
}

