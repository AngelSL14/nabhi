package us.gonet.jxiserver.dbprosa.business;


import us.gonet.serverutils.model.jdb.IDF;

import java.util.List;

public interface IIdfBusiness {
    Iterable<IDF> saveBatch(List<IDF> idfs);
}
