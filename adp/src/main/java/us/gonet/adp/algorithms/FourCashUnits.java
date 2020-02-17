package us.gonet.adp.algorithms;

import org.springframework.stereotype.Component;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FourCashUnits {

    private static final String ESTADO = "estado";
    private static final String DENOMINATION = "denominacion";

    public String getBills(int monto, List<Map<String, Object>> cajero, List<Casetero> caseteros) throws ServerException {

        boolean [] casDisponibles = getCasDisp(cajero);
        int multiplo = getMultiplo(cajero);
        int[] denoms = getDenoms(cajero);

        int c3Denom = denoms[2];
        int c4Denom = denoms[3];

        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;

        if (casDisponibles[0] || casDisponibles[1])
        {
            int[] cs = lowDenomsOk(multiplo, monto, casDisponibles, denoms);
            c1= cs[0];
            c2= cs[1];
            c3= cs[2];
            c4= cs[3];
        }
        else if(casDisponibles[2] && casDisponibles[3])
        {
            int[] cs = highDenomsOk(monto, denoms);
            c1= cs[0];
            c2= cs[1];
            c3= cs[2];
            c4= cs[3];
        }
        else if(casDisponibles[3])
        {
            if(monto % c4Denom == 0)
            {
                c4 = c4 + (monto/c4Denom);
            }
            else
            {
                throw throwIncorrectAmount();
            }
        }
        else if(casDisponibles[2])
        {
            if(monto % c3Denom == 0)
            {
                c3 = c3 + (monto/c3Denom);
            }
            else
            {
                throw throwIncorrectAmount();
            }
        }
        return bills(c1, c2, c3, c4, caseteros);
    }

    private int[] lowDenomsOk(int multiplo, int monto, boolean [] casDisponibles, int [] denoms) throws ServerException
    {
        if (multiplo != 0 && monto % multiplo == 0)
        {
            return lowDenoms(monto, casDisponibles, denoms);
        }
        else
        {
            throw throwIncorrectAmount();
        }

    }

    private int[] highDenomsOk(int monto, int [] denoms) throws ServerException {
        int c2Denom = denoms[1];
        int c3Denom = denoms[2];
        int c4Denom = denoms[3];
        int[] cs = {0, 0, 0, 0};
        if (monto >= c3Denom && monto % c2Denom == 0)
        {
            if(monto >= c4Denom)
            {
                cs = highDenoms(monto, denoms);
            }
            else if (monto % c3Denom == 0)
            {
                cs[2]= cs[2] + (monto/c3Denom);
            }
            else
            {
                throw throwIncorrectAmount();
            }
        }
        else
        {
            throw throwIncorrectAmount();
        }
        return cs;
    }

    private static String bills (int c1, int c2, int c3, int c4, List<Casetero> caseteroList) throws ServerException {
        StringBuilder respuesta = new StringBuilder();
        if(c1+c2+c3+c4 > 0 && c1+c2+c3+c4 < 50)
        {
            int [] bills = {c1, c2, c3, c4};
            int cas = 0;
            for(Casetero c : caseteroList)
            {
                if(c.getType().equals("BILLCASSETTE") || c.getType().equals("RECYCLING"))
                {
                    respuesta.append(String.format("%02d", bills[cas]));
                    cas++;
                }
                else
                {
                    respuesta.append("00");
                }
            }
        }
        else if(c1+c2+c3+c4 >= 50)
        {
            List<ErrorWS> errorWS = new ArrayList<>();
            errorWS.add(new ErrorWS("ADP-04", "El monto solicitado excede 50 billetes"));
            throw new ServerException("El monto solicitado excede 50 billetes", errorWS);
        }
        return respuesta.toString();
    }

    private boolean[] getCasDisp(List<Map<String, Object>> cajero) throws ServerException {
        boolean [] casDisponibles = {false, false, false, false};
        int notOkCashUnits = 0;
        for(int i = 0; i<cajero.size(); i++)
        {
            casDisponibles[i] =(boolean) cajero.get(i).get(ESTADO);
            if(!(boolean) cajero.get(i).get(ESTADO))
            {
                notOkCashUnits++;
            }
        }
        if(notOkCashUnits == 4)
        {
            List<ErrorWS> errorWS = new ArrayList<>();
            errorWS.add(new ErrorWS("ADP-02", "No hay caseteros disponibles para dispensar"));
            throw new ServerException("No hay caseteros disponibles para dispensar", errorWS);
        }
        return casDisponibles;
    }

    private int getMultiplo(List<Map<String, Object>> cajero)
    {
        int multiplo = 0;
        for(Map casetero : cajero)
        {
            if((boolean)casetero.get(ESTADO))
            {
                multiplo =(int) casetero.get(DENOMINATION);
                break;
            }
        }

        return multiplo;
    }

    private int[] getDenoms(List<Map<String, Object>> cajero)
    {
        int [] denoms = {0,0,0,0};
        for(int i = 0; i< cajero.size(); i++)
        {
            denoms[i] = (int) cajero.get(i).get(DENOMINATION);
        }
        return denoms;
    }

    private int[] lowDenoms(int monto, boolean [] casDisponibles, int [] denoms)
    {
        int c1Denom = denoms[0];
        int c2Denom = denoms[1];
        int c3Denom = denoms[2];

        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;

        while (monto > 0){
            if (casDisponibles[0] && monto >= c1Denom){
                c1 = c1 + 1;
                monto = monto - c1Denom;
            }
            if (casDisponibles[1] && monto >= c2Denom){
                c2 = c2 + 1;
                monto = monto - c2Denom;
            }
            if (casDisponibles[2] && monto >= c3Denom){
                c3 = c3 + 1;
                monto = monto - c3Denom;
            }

            int[] highBills = billsFromhigher(monto, casDisponibles, denoms);
            c1 = c1 + highBills[0];
            c2 = c2 + highBills[1];
            c3 = c3 + highBills[2];
            c4 = c4 + highBills[3];
            monto = highBills[4];
        }
        return new int[]{c1, c2, c3, c4};
    }

    private int[] highDenoms(int monto, int [] denoms)
    {
        int c3Denom = denoms[2];
        int c4Denom = denoms[3];

        int c3 = 0;
        int c4 = 0;

        while(monto > 0)
        {
            if(monto % c4Denom == 0)
            {
                c4 = c4 + (monto / c4Denom);
                monto = monto - (c4Denom * (monto/c4Denom));
            }
            if(monto % c3Denom == 0)
            {
                if(monto != 0)
                {
                    c3 = c3 + 1;
                    monto = monto - c3Denom;
                }

            }
            else
            {
                c4 = c4 + 1;
                monto = monto - c4Denom;
            }
        }

        return new int[]{0, 0, c3, c4};
    }

    private ServerException throwIncorrectAmount() {
        List<ErrorWS> errorWS = new ArrayList<>();
        errorWS.add(new ErrorWS("ADP-03", "El monto solicitado no se puede dispensar"));
        return new ServerException("El monto solicitado no se puede dispensar", errorWS);
    }

    private int[] billsFromhigher( int monto, boolean [] casDisponibles, int [] denoms)
    {
        int c1Denom = denoms[0];
        int c2Denom = denoms[1];
        int c3Denom = denoms[2];
        int c4Denom = denoms[3];

        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;

        if (casDisponibles[3] && monto >= c4Denom){
            c4 = monto / c4Denom;
            monto = monto - (c4 * c4Denom);
        }
        if (casDisponibles[2] && monto >= c3Denom){
            c3 = c3 + (monto / c3Denom);
            monto = monto - (c3Denom * (monto / c3Denom));
        }
        if (casDisponibles[1] && monto >= c2Denom){
            c2 = c2 + (monto / c2Denom);
            monto = monto - (c2Denom * (monto / c2Denom));
        }
        if (casDisponibles[0] && monto >= c1Denom){
            c1 = c1 + (monto / c1Denom);
            monto = monto - (c1Denom * (monto / c1Denom));
        }
        return new int[]{c1, c2, c3, c4, monto};
    }

}
