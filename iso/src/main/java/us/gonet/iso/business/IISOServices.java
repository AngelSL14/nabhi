package us.gonet.iso.business;

import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.iso.ServicesInformation;

public interface IISOServices {

    ResponseWrapper < ServicesInformation > sendLogon ();

    ResponseWrapper < ServicesInformation > sendLogoff ();

    ResponseWrapper < ServicesInformation > statusSocket ();

    ResponseWrapper < ServicesInformation > turnOnSocket ();

    ResponseWrapper < ServicesInformation > turnOffSocket ();

    ResponseWrapper < ServicesInformation > echo ();

    ResponseWrapper < ISO > sampleService ( ISO iso );

    ResponseWrapper < ISO > sendRequest ( ISO iso );

}
