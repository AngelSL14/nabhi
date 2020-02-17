package us.gonet.jxiserver.dbprosa.business;


import us.gonet.serverutils.model.jdb.County;

import java.util.List;

public interface ICountyBusiness {
    Iterable<County> saveBatch(List<County> counties);

}
