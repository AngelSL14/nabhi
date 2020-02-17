package us.gonet.jxiserver.dbprosa.entity;


import javax.persistence.*;

@Entity
@Table(name = "TBL_RCPT")
public class RcptEntity {

    @Id
    @Column(name = "PK_KEY", length = 6)
    private String key;

    @Lob
    private String header;

    @Lob
    private String body;

    @Lob
    private String trailer;

    public String getKey () {
        return key;
    }

    public void setKey ( String key ) {
        this.key = key;
    }

    public String getHeader () {
        return header;
    }

    public void setHeader ( String header ) {
        this.header = header;
    }

    public String getBody () {
        return body;
    }

    public void setBody ( String body ) {
        this.body = body;
    }

    public String getTrailer () {
        return trailer;
    }

    public void setTrailer ( String trailer ) {
        this.trailer = trailer;
    }
}
