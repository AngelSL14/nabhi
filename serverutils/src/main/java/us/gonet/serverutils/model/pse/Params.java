package us.gonet.serverutils.model.pse;

import com.google.gson.GsonBuilder;

public class Params {

    private String name;
    private String description;
    private String inputType;
    private String dataType;
    private boolean required;

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public String getInputType () {
        return inputType;
    }

    public void setInputType ( String inputType ) {
        this.inputType = inputType;
    }

    public String getDataType () {
        return dataType;
    }

    public void setDataType ( String dataType ) {
        this.dataType = dataType;
    }

    public boolean isRequired () {
        return required;
    }

    public void setRequired ( boolean required ) {
        this.required = required;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
