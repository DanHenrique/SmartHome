package br.com.pucsp.smarthome.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.util.leap.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusEquipamento implements Serializable {

    private final static Logger log = LoggerFactory.getLogger(StatusEquipamento.class);

    // Atributos
    public String entity_id;
    public int brightness;
    public int temperature;

    // Métodos
    public StatusEquipamento(){
    }
    
    public StatusEquipamento(String entity, int temperature, int brightness){
        this.setEntity(entity);
        this.setBrightness(brightness);
        this.setTemperature(temperature);
    }

    public String getEntity() {
        return this.entity_id;
    }

    public void setEntity(String entity) {
        this.entity_id = entity;
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
