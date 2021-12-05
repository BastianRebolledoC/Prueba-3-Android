package com.bastianrebolledo.prueba3.models;

public class Sensor {
    private String UID;
    private String Name;
    private String Type;
    private String Value;
    private String Ubication;
    private String DateTime;
    private String Extra;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getUbication() {
        return Ubication;
    }

    public void setUbication(String ubication) {
        Ubication = ubication;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public Sensor(String UID, String name, String type, String value, String ubication, String dateTime, String extra) {
        this.UID = UID;
        Name = name;
        Type = type;
        Value = value;
        Ubication = ubication;
        DateTime = dateTime;
        Extra = extra;
    }
}
