package br.com.pucsp.smarthome.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ServiceData implements Serializable {

    @JsonProperty("entity_id")
    public String entityID;

    public ServiceData(){        
    }

    public ServiceData(String entityID){
        this.setEntityID(entityID);
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }
}
