package us.gonet.jxiserver.dao.journal.entity;

import java.sql.Timestamp;

public class JournalEntity {

    private int id;

    private String message;

    private String atm;

    private Timestamp writeDate;

    public JournalEntity() {
    }

    public JournalEntity(int id, String message, String atm, Timestamp writeDate) {
        this.id = id;
        this.message = message;
        this.atm = atm;
        this.writeDate = writeDate;
    }

    public Timestamp getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Timestamp writeDate) {
        this.writeDate = writeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAtm() {
        return atm;
    }

    public void setAtm(String atm) {
        this.atm = atm;
    }

    @Override
    public String toString() {
        return "JournalEntity{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", atm='" + atm + '\'' +
                '}';
    }
}
