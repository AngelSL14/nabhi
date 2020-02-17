package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;

@Entity @Table(name = "TBL_JOURNAL")
public class JournalEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_JOURNAL")
    private int idJournal;

    @Lob
    private String message;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ATM_JOURNAL"))
    private AtmEntity fkAtmTerminalId;

    public JournalEntity() {
    }

    public JournalEntity(String message, AtmEntity fkAtmTerminalId) {
        this.message = message;
        this.fkAtmTerminalId = fkAtmTerminalId;
    }

    public int getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(int idJournal) {
        this.idJournal = idJournal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AtmEntity getFkAtmTerminalId() {
        return fkAtmTerminalId;
    }

    public void setFkAtmTerminalId(AtmEntity fkAtmTerminalId) {
        this.fkAtmTerminalId = fkAtmTerminalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalEntity entity = (JournalEntity) o;

        if (idJournal != entity.idJournal) return false;
        if (message != null ? !message.equals(entity.message) : entity.message != null) return false;
        return fkAtmTerminalId != null ? fkAtmTerminalId.equals(entity.fkAtmTerminalId) : entity.fkAtmTerminalId == null;
    }

    @Override
    public int hashCode() {
        int result = idJournal;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (fkAtmTerminalId != null ? fkAtmTerminalId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JournalEntity{" +
                "idJournal=" + idJournal +
                ", message='" + message + '\'' +
                ", fkAtmTerminalId=" + fkAtmTerminalId +
                '}';
    }
}
