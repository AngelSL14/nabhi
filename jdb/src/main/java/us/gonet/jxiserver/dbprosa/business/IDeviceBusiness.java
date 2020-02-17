package us.gonet.jxiserver.dbprosa.business;


import us.gonet.serverutils.model.jdb.Device;

import java.util.List;

public interface IDeviceBusiness {
    us.gonet.jxiserver.dbprosa.response.ResponseWrapper<Device> saveBatch (List<Device> devices);
}
