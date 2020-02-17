package us.gonet.serverutils.model;

import com.google.gson.GsonBuilder;
import us.gonet.serverutils.model.node.NodeSingleModel;

import java.util.Map;

public class ATMRequestModel {

    private String fromAccount;
    private String toAccount;
    private String amount;
    private String traceNumber;
    private String entryMode;
    private String surcharge;
    private String track2;
    private String sequenceNumber;
    private String termId;
    private String termOwnerName;
    private String termCity;
    private String termState;
    private String termCountry;
    private String groupAllow;
    private String currencyCode;
    private String pinBlock;
    private String newPinBlock;
    private String newPinBlock2;
    private String termFiid;
    private String lNet;
    private String timeOffSet;
    private Map < String, String > emv;
    private String termType;
    private String company;
    private String phoneNumber;
    private String phoneNumber2;
    private NodeSingleModel nodeSingle;


    public String getFromAccount () {
        return fromAccount;
    }

    public void setFromAccount ( String fromAccount ) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount () {
        return toAccount;
    }

    public void setToAccount ( String toAccount ) {
        this.toAccount = toAccount;
    }

    public String getAmount () {
        return amount;
    }

    public void setAmount ( String amount ) {
        this.amount = amount;
    }

    public String getTraceNumber () {
        return traceNumber;
    }

    public void setTraceNumber ( String traceNumber ) {
        this.traceNumber = traceNumber;
    }

    public String getEntryMode () {
        return entryMode;
    }

    public void setEntryMode ( String entryMode ) {
        this.entryMode = entryMode;
    }

    public String getSurcharge () {
        return surcharge;
    }

    public void setSurcharge ( String surcharge ) {
        this.surcharge = surcharge;
    }

    public String getTrack2 () {
        return track2;
    }

    public void setTrack2 ( String track2 ) {
        this.track2 = track2;
    }

    public String getSequenceNumber () {
        return sequenceNumber;
    }

    public void setSequenceNumber ( String sequenceNumber ) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getTermId () {
        return termId;
    }

    public void setTermId ( String termId ) {
        this.termId = termId;
    }

    public String getTermOwnerName () {
        return termOwnerName;
    }

    public void setTermOwnerName ( String termOwnerName ) {
        this.termOwnerName = termOwnerName;
    }

    public String getTermCity () {
        return termCity;
    }

    public void setTermCity ( String termCity ) {
        this.termCity = termCity;
    }

    public String getTermState () {
        return termState;
    }

    public void setTermState ( String termState ) {
        this.termState = termState;
    }

    public String getTermCountry () {
        return termCountry;
    }

    public void setTermCountry ( String termCountry ) {
        this.termCountry = termCountry;
    }

    public String getGroupAllow () {
        return groupAllow;
    }

    public void setGroupAllow ( String groupAllow ) {
        this.groupAllow = groupAllow;
    }

    public String getCurrencyCode () {
        return currencyCode;
    }

    public void setCurrencyCode ( String currencyCode ) {
        this.currencyCode = currencyCode;
    }

    public String getPinBlock () {
        return pinBlock;
    }

    public void setPinBlock ( String pinBlock ) {
        this.pinBlock = pinBlock;
    }

    public String getNewPinBlock () {
        return newPinBlock;
    }

    public void setNewPinBlock ( String newPinBlock ) {
        this.newPinBlock = newPinBlock;
    }

    public String getNewPinBlock2() {
        return newPinBlock2;
    }

    public void setNewPinBlock2( String newPinBlock2 ) {
        this.newPinBlock2 = newPinBlock2;
    }

    public String getTermFiid () {
        return termFiid;
    }

    public void setTermFiid ( String termFiid ) {
        this.termFiid = termFiid;
    }

    public String getlNet () {
        return lNet;
    }

    public void setlNet ( String lNet ) {
        this.lNet = lNet;
    }

    public String getTimeOffSet () {
        return timeOffSet;
    }

    public void setTimeOffSet ( String timeOffSet ) {
        this.timeOffSet = timeOffSet;
    }

    public Map < String, String > getEmv () {
        return emv;
    }

    public void setEmv ( Map < String, String > emv ) {
        this.emv = emv;
    }

    public String getTermType () {
        return termType;
    }

    public void setTermType ( String termType ) {
        this.termType = termType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany( String company ) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2( String phoneNumber2 ) {
        this.phoneNumber2 = phoneNumber2;
    }

    public NodeSingleModel getNodeSingle () {
        return nodeSingle;
    }

    public void setNodeSingle ( NodeSingleModel nodeSingle ) {
        this.nodeSingle = nodeSingle;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
