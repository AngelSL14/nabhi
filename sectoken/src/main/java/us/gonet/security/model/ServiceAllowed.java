package us.gonet.security.model;

import us.gonet.security.rest.model.User;

public class ServiceAllowed {

    private String service;
    private boolean stop;
    private User user;
    private boolean alive;

    public ServiceAllowed( User user, String service, boolean stop ){
        this.user = user;
        this.service = service;
        this.stop = stop;
    }

    public String getService () {
        return service;
    }

    public void setService ( String service ) {
        this.service = service;
    }

    public boolean isStop () {
        return stop;
    }

    public void setStop ( boolean stop ) {
        this.stop = stop;
    }

    public User getUser () {
        return user;
    }

    public void setUser ( User user ) {
        this.user = user;
    }

    public boolean isAlive () {
        return alive;
    }

    public void setAlive ( boolean alive ) {
        this.alive = alive;
    }
}
