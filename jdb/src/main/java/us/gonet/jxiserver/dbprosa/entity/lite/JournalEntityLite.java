package us.gonet.jxiserver.dbprosa.entity.lite;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity @Table(name = "TBL_JOURNAL")
public class JournalEntityLite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_JOURNAL")
    private int id;

    @Lob
    private String message;

    @Column(length = 16, name = "FK_ATM_TERMINAL_ID", nullable = false)
    private String atm;

    private Timestamp writeDate;

    public JournalEntityLite() {
    }

    public JournalEntityLite(String message, String atm, Timestamp writeDate) {
        this.message = message;
        this.atm = atm;
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

    public Timestamp getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Timestamp writeDate) {
        this.writeDate = writeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalEntityLite that = (JournalEntityLite) o;

        if (id != that.id) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (atm != null ? !atm.equals(that.atm) : that.atm != null) return false;
        return writeDate != null ? writeDate.equals(that.writeDate) : that.writeDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (atm != null ? atm.hashCode() : 0);
        result = 31 * result + (writeDate != null ? writeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JournalEntityLite{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", atm='" + atm + '\'' +
                ", writeDate=" + writeDate +
                '}';
    }
}
