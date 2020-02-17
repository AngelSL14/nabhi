package us.gonet.jxiserver.dbprosa.business;

import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.serverutils.model.jdb.Casetero;

import java.util.List;

public interface ICaseterosBusiness {

    ResponseWrapper<Casetero> saveBatch(List<Casetero> caseteros, Integer atmId);
}
