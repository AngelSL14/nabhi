package us.gonet.jxiserver.dbprosa.config;

import us.gonet.jxiserver.dbprosa.response.ErrorWS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {

    private Utils() {
    }

    public static boolean stringIsBlank(String val)
    {
        boolean blank = false;
        if(val==null || val.isEmpty())
        {
            blank = true;
        }
        return blank;
    }

    private static final String ZERO_TO_255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
    private static final String IP_REGEXP = ZERO_TO_255 + "\\." + ZERO_TO_255 + "\\."+ ZERO_TO_255 + "\\." + ZERO_TO_255;
    private static final Pattern IP_PATTERN = Pattern.compile(IP_REGEXP);

    private static boolean isIp(String address) {
            return IP_PATTERN.matcher(address).matches();
    }


    public static List<ErrorWS> validateIp (String atmIp) {
        if(Utils.stringIsBlank(atmIp) || !Utils.isIp(atmIp))
        {
            List<ErrorWS> errors = new ArrayList<>();
            errors.add(new ErrorWS("JDB020", "Parametro de entrada Invalido" ));
            return errors;
        }
        return Collections.emptyList();
    }

    public static String validateIp1 (String atmIp) {
        if(Utils.stringIsBlank(atmIp) || !Utils.isIp(atmIp))
        {
            return null;
        }
        return atmIp;
    }
}
