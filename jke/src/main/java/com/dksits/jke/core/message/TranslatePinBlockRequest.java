package com.dksits.jke.core.message;

import com.dksits.jke.core.util.Utilities;


/**
 *
 * @author Gustavo Mancilla Flores.
 */
public class TranslatePinBlockRequest implements Request {
    
    private static final String CLASS = "9";
    private static final String COMMAND = "2";
    private final String atmRemote;
    private final String termType;
    private final String atmLocal;
    private final String pinBlock;
    private final String track2;
    private final String ipk;
    private final String sequence;
    
    public TranslatePinBlockRequest( String termType, String atmRemote, String atmLocal, String pinBlock, String track2, String ipk ){
        this.termType = termType;
        this.atmRemote = MessageUtils.fillSpaces( atmRemote );
        this.atmLocal = MessageUtils.fillSpaces( atmLocal );
        this.pinBlock = pinBlock;
        this.track2 = ";" +track2 +"?";
        this.ipk = ipk;
        this.sequence = Utilities.setRandomSequence();
    }

    
    @Override
    public String getSequence(){
        return sequence;
    }

    @Override
    public String toString(){
        String fs = "" + ( char ) 28;
        return CLASS + fs +
                COMMAND + fs +
                atmRemote + fs +
                termType + fs + 
                atmLocal + fs +
                pinBlock + fs +
                track2 + fs +
                ipk + fs +
                sequence;
    }
}
