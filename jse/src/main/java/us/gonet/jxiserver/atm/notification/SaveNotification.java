package us.gonet.jxiserver.atm.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.codesatm.AtmEvent;
import us.gonet.jxiserver.codesatm.AtmResponse;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.model.request.AtmNotificationModel;

@Component
public class SaveNotification {

    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    JournalWritter journalWritter;

    public void saveJournal(AtmNotificationModel model, AtmEvent atmEvent)
    {
        journalWritter.writeJournal(model.getTermId(), atmEvent.getJournalMessage());
    }

    public void saveJournalResponse(AtmNotificationModel model, AtmResponse atmEvent)
    {
        journalWritter.writeJournal(model.getTermId(), atmEvent.getMessage());
    }

    public void saveDeviceNotif(AtmNotificationModel model, String atmEvent)
    {
        journalWritter.writeDeviceNotif(model.getTermId(), atmEvent);
    }
}
