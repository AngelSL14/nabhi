package us.gonet.adp.validate;

import org.springframework.stereotype.Component;
import us.gonet.serverutils.model.jdb.Casetero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Validation {
    private static final String ESTADO = "estado";
    private static final String DENOMINATION = "denominacion";

    public List<Map<String,Object>> validateCashUnits(List<Casetero> caseteroList)
    {
        List<Map<String,Object>> cajero = new ArrayList<>();

        for(Casetero casetero : caseteroList)
        {
            if(casetero.getType().equals("BILLCASSETTE") || casetero.getType().equals("RECYCLING"))
            {
                Map<String , Object> atm = new HashMap<>();
                atm.put(DENOMINATION, casetero.getDenominacion());
                atm.put("number", casetero.getNumberCasetero());
                atm.put(ESTADO,getStatus(casetero));
                cajero.add(atm);
            }
        }


        return mirrorCashDispenser(cajero);

    }
    private boolean getStatus(Casetero casetero)
    {
        boolean state = false;
        if((casetero.getStatus().equals("OK") || (casetero.getStatus().equals("LOW")
                || casetero.getStatus().equals("FULL") || casetero.getStatus().equals("HIGH")))
                && casetero.getActual() >= 100)
        {
            state = true;
        }
        return state;
    }

    private List<Map<String,Object>> mirrorCashDispenser(List<Map<String,Object>> estados)
    {
        List<Map<String,Object>> news = estados;
        for(int i = 1; i < news.size(); i++)
        {
            boolean estado = (boolean)news.get(i-1).get(ESTADO);
            boolean estado2 = (boolean)news.get(i).get(ESTADO);
            int cas = (int) news.get(i-1).get(DENOMINATION);
            int cas2 = (int) news.get(i).get(DENOMINATION);
            if((estado == estado2) && (cas == cas2))
            {
                estados.get(i-1).put(ESTADO, false);
            }
        }
        return estados;
    }

}
