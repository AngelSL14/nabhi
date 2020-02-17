package us.gonet.jxiserver.business;

import us.gonet.jxiserver.model.request.AtmNotificationModel;
import us.gonet.serverutils.exceptionutils.ServerException;

public interface IAtmNotificationsBusiness {

    boolean updateDevice(AtmNotificationModel model);
    boolean dispNotif(AtmNotificationModel model) throws ServerException;
    void sendToDevice(AtmNotificationModel model);
}
