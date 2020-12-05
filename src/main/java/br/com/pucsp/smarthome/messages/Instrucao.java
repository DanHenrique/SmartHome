package br.com.pucsp.smarthome.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.util.leap.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Instrucao implements Serializable {

    private final static Logger log = LoggerFactory.getLogger(Instrucao.class);

    // Atributos
    @JsonProperty("entity_id")
    public String entityID;
    @JsonProperty("domain")
    public String domain;
    @JsonProperty("service")
    public String service;

    // Métodos
    public Instrucao(){
    }

    public Instrucao(String entity, String domain, String service){
        this.setEntityID(entity);
        this.setDomain(domain);
        this.setService(service);
    }

    public String getEntityID() {
        return this.entityID;
    }

    public void setEntityID(String entity) {
        this.entityID = entity;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
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
