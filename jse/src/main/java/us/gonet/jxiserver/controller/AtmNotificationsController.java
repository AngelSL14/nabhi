package us.gonet.jxiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.business.IAtmNotificationsBusiness;
import us.gonet.jxiserver.catalog.DevicesCat;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.model.request.AtmNotificationModel;


@RestController
@RequestMapping("ntf")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class AtmNotificationsController {

    @Autowired @Qualifier("notifBus")
    IAtmNotificationsBusiness bus;

    @Autowired
    JournalWritter journalWritter;

    private static final String[] ALLOWED_FIELDS = new String[] { "ip", "termId", "device", "status", "extra"};

    @InitBinder( "AtmNotificationModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @PostMapping("")
    public boolean atmNotification(@RequestBody AtmNotificationModel model)
    {
        bus.sendToDevice(model);
        return true;
    }

    @ResponseBody
    @PostMapping("/response")
    public boolean atmResponse(@RequestBody AtmNotificationModel model)
    {
        if(!model.getExtra().get("msg1").toString().contains("-"))
        {
            journalWritter.writeJournal(model.getTermId(), model.getExtra().get("msg1").toString());
        }
        model.setDevice(DevicesCat.PINPAD.name());
        return bus.updateDevice(model);
    }
}
