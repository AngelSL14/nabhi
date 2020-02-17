package us.gonet.jxiserver.dbprosa.business;

import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.serverutils.model.jdb.Button;

import java.util.List;

public interface IButtonsBusiness {

    ResponseWrapper<Button> saveBatch (List<Button> buttons, String atmId);
}
