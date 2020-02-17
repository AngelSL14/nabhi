package us.gonet.jxiserver.dbprosa.business;


import us.gonet.serverutils.model.jdb.State;

import java.util.List;

public interface IStateBusiness {

    Iterable<State> saveBatch (List<State> states);
}
