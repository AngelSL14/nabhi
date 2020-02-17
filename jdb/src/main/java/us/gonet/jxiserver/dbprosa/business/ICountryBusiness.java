package us.gonet.jxiserver.dbprosa.business;

import us.gonet.serverutils.model.jdb.Country;

import java.util.List;

public interface ICountryBusiness {

    Iterable<Country> saveBatch (List<Country> countries);
}
