package us.gonet.jxiserver.business;

import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.ScreenCapabilities;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.jdb.Screen;

public interface ICapabilitiesScreen {

    public ResponseWrapper<ScreenCapabilities> getButtons(Generic generic);
    public ResponseWrapper<Screen> getPublicity(Generic generic);

}
