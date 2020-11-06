package br.com.pucsp.smarthome.messages;

import br.com.pucsp.smarthome.enums.Severity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.util.leap.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusEquipamento implements Serializable {

    private final static Logger log = LoggerFactory.getLogger(StatusEquipamento.class);

    // Atributos
    @JsonProperty
    public String severity;
    @JsonProperty("entity_id")
    public String entityID;
    @JsonProperty
    public int brightness;
    @JsonProperty
    public int temperature;

    // Métodos
    public StatusEquipamento() {
        this.calculateSeverity();
    }

    public StatusEquipamento(String entity, int temperature, int brightness) {
        this.setEntityID(entity);
        this.setBrightness(brightness);
        this.setTemperature(temperature);
        this.calculateSeverity();
    }

    public String getSeverity() {
        return this.severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity.getText();
    }

    public String getEntityID() {
        return this.entityID;
    }

    public void setEntityID(String entity) {
        this.entityID = entity;
    }

    public int getBrightness() {
        return this.brightness;
    }

    public void setBrightness(int domain) {
        this.brightness = domain;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void setTemperature(int service) {
        this.temperature = service;
    }

    public void calculateSeverity() {
        if (this.brightness == 0 || this.temperature == 0)
            this.setSeverity(Severity.VERY_HIGH);
        else if (this.brightness < 20 || this.temperature > 100)
            this.setSeverity(Severity.VERY_HIGH);
        else if (this.brightness < 50 || this.temperature > 80)
            this.setSeverity(Severity.HIGH);
        else if (this.brightness < 65 || this.temperature > 50)
            this.setSeverity(Severity.MEDIUM);
        else if (this.brightness < 85 || this.temperature > 40)
            this.setSeverity(Severity.LOW);
        else
            this.setSeverity(Severity.NONE);
    }

    public String toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getStackTrace().toString());
        }
        return json;
    }
}
