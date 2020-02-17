package us.gonet.security.rest;

import us.gonet.security.rest.model.User;

public interface IUser {

    User findById ( String id );
}
