package us.gonet.jxiserver.dbprosa.esq;

import us.gonet.jxiserver.dbprosa.entity.ATDEntity;
import us.gonet.jxiserver.dbprosa.entity.DeviceStatusEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TBL_ATM")
public class AtmEsqEntity implements Serializable{

    @Id
    private String terminalId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "PK_TERMINAL_ID", referencedColumnName = "PK_ATM_TERMINAL_ID")
    private ATDEntity id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atm")
    private List<DeviceStatusEntity> devices;

    @Column(length = 15)
    private String ip;

    public List<DeviceStatusEntity> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceStatusEntity> devices) {
        this.devices = devices;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public ATDEntity getId() {
        return id;
    }

    public void setId(ATDEntity id) {
        this.id = id;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String toString() {
        return "AtmEsqEntity{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                '}';
    }
}
