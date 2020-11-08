package br.com.pucsp.smarthome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute implements Serializable {

    @JsonProperty("friendly_name")
    public String friendlyName;

    @JsonProperty("supported_features")
    public int supportedFeatures;

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public int getSupportedFeatures() {
        return supportedFeatures;
    }

    public void setSupportedFeatures(int supportedFeatures) {
        this.supportedFeatures = supportedFeatures;
    }
}
