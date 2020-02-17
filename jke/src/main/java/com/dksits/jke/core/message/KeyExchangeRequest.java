package com.dksits.jke.core.message;

import com.dksits.jke.core.util.Utilities;

public class KeyExchangeRequest implements Request {
    
    private static final String CLASS = "9";
    private static final String COMMAND = "1";
    private final String termType;
    private final String atmRemote;
    private final String atmLocal;
    private final String sequence;
    
    public KeyExchangeRequest( String termType, String atmRemote, String atmLocal ){
        this.termType = termType;
        this.atmRemote = MessageUtils.fillSpaces( atmRemote );
        this.atmLocal = MessageUtils.fillSpaces( atmLocal );
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
                sequence;
    }
}
