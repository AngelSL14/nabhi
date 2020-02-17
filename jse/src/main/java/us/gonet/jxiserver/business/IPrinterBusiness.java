package us.gonet.jxiserver.business;

import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.Ticket;

public interface IPrinterBusiness {

	Ticket printingTicket(Generic generic);

}
