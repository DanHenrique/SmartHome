package br.com.pucsp.smarthome.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class State implements Serializable {

    @JsonProperty("entity_id")
    public String entityID;

    @JsonProperty("last_changed")
    public String lastChanged;

    @JsonProperty("state")
    public String state;
}
