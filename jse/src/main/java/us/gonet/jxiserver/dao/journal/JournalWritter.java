package us.gonet.jxiserver.dao.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.config.PropertiesServices;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.entity.JournalEntity;
import us.gonet.jxiserver.utils.Utils;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ServerException;

@Component
public class JournalWritter {
    private static final Logger LOG = LoggerFactory.getLogger(JournalWritter.class);


    @Autowired
    PropertiesServices propertiesServices;


    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    AuthIn authIn;
    public void writeJournal(String atm, String message)
    {
        atm = Utils.sanitize( atm );
        message = Utils.sanitize( message );
        String service = "/journal/write/{atm}";
        try {
            restRequestFactory.buildRestRequest(service, JournalEntity.class, "jdb","No se guardo informacion en el journal",
                    "post", message, atm);
        } catch (ServerException e) {
            LOG.error("Error en la peticion entre servicios");

        }
    }

    public void writeDeviceNotif(String atm, String message)
    {

        String service = "/deviceNotif/write/{atm}";
        try {
            restRequestFactory.buildRestRequest(service, JournalEntity.class, "jdb",
                    "No se guardo informacion en device Notification",
                     "post", message, atm);
        } catch (ServerException e) {
            LOG.error("Error en la peticion entre servicios");

        }
    }


}
