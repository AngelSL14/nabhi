package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PropertiesServices {

    @Autowired
    AdpProperties adpProperties;

    @Autowired
    GesProperties gesProperties;

    @Autowired
    InqProperties inqProperties;

    @Autowired
    JdbProperties jdbProperties;

    @Autowired
    JkeProperties jkeProperties;

    @Autowired
    IsoProperties isoProperties;

    @Autowired
    NchProperties nchProperties;

    @Autowired
    StmtProperties stmtProperties;

    @Autowired
    WdlProperties wdlProperties;

    @Autowired
    SrhProperties srhProperties;

    @Autowired
    ReversalProperties reversalProperties;

    @Autowired
    Properties props;

    public Map<String, Object> getProperties()
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("adp", adpProperties);
        properties.put("ges", gesProperties);
        properties.put("inq", inqProperties);
        properties.put("jke", jkeProperties);
        properties.put("iso", isoProperties);
        properties.put("nch", nchProperties);
        properties.put("stmt", stmtProperties);
        properties.put("wdl", wdlProperties);
        properties.put("jdb", jdbProperties);
        properties.put("srh", srhProperties);
        properties.put("reversal", reversalProperties);
        properties.put("props", props);

        return properties;
    }



}
