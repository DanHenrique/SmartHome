package br.com.pucsp.smarthome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State implements Serializable {

    @JsonProperty("entity_id")
    public String entityID;

    @JsonProperty("state")
    public String state;

    @JsonProperty("attributes")
    public Attribute attributes;

    @JsonProperty("last_changed")
    public String lastChanged;

    @JsonProperty("last_updated")
    public String lastUpdated;

    @JsonProperty("context")
    public Context context;

}
