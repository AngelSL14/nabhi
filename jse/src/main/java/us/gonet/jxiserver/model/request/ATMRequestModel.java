package us.gonet.jxiserver.model.request;

import com.google.gson.GsonBuilder;

import java.util.Map;

public class ATMRequestModel {

    private String fromAccount;//-------------------------------OK
    private String toAccount;//usualmente cero------------------OK
    private String amount;//------------------------------------OK
    private String traceNumber;//node prosa por nodename--------!!!!!GUS
    private String entryMode;//Proviene del track code 21/22----OK
    private String surcharge;//TABLA SURCHRGE-------------------!!!!!CUAU
    private String track2;//------------------------------------OK
    private String sequenceNumber;//Tabla de ATM ATD------------OK
    private String termId;//ATM.--------------------------------OK
    private String termOwnerName;//IDF NAME---------------------OK
    private String termCity;//ATM COUNTY NAME TABLA COUNTRY-----OK
    private String termState;//TABLA COUNTY FKSTATE-------------OK
    private String termCountry; // IDF FKCOUNTRY CODE-----------OK/////////////
    private String groupAllow;//PENDIENTE!!!!!!!!---------------!!!!CUAU
    private String currencyCode;//PKCOUNTRY---countyCodeId------OK/////////////
    private String pinBlock;//----------------------------------OK
    private String newPinBlock;//REVISAR------------------------OK
    private String newPinBlock2;//REVISAR-----------------------OK
    private String termFiid;//IDF PF_FIID-----------------------OK
    private String lNet;//IDF LOGICAL_NET-----------------------OK

    private String timeOffSet;// PENDIENTE !!!!-----------------!!!!CUAU
    private Map < String, String > emv;//-----------------------OK
    private String termType;//ATD DEVICE TYPE-------------------OK
    private String company;//-----------------------------------OK
    private String phoneNumber;//-------------------------------OK
    private String phoneNumber2;//------------------------------OK


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

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
