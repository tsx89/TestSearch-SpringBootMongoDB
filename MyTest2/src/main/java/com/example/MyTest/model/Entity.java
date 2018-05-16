package com.example.MyTest.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "A0")
public class Entity {

    private String ID;

    private String STREET_NO;

    private String STREET_NAME;

    private String POSTAL_MUNICIPALITY_NAME;

    private String POSTAL_CODE;

    private String SOURCE_SYSTEM ;
    private String SOURCE_UNIQUE_ID;

    public String getID() {
        return ID;
    }

    public String getSTREET_NO() {
        return STREET_NO;
    }

    public String getSTREET_NAME() {
        return STREET_NAME;
    }

    public String getPOSTAL_MUNICIPALITY_NAME() {
        return POSTAL_MUNICIPALITY_NAME;
    }

    public String getPOSTAL_CODE() {
        return POSTAL_CODE;
    }

    public String getSOURCE_SYSTEM() { return SOURCE_SYSTEM; }

    public String getSOURCE_UNIQUE_ID() { return SOURCE_UNIQUE_ID; }
}
