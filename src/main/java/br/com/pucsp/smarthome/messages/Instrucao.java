package br.com.pucsp.smarthome.messages;

import br.com.pucsp.smarthome.services.HomeAssistantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.util.leap.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Instrucao implements Serializable {

    private final static Logger log = LoggerFactory.getLogger(Instrucao.class);

    // Atributos
    public String entity_id;
    public String domain;
    public String service;

    // Métodos
    public Instrucao(String entity, String domain, String service){
        this.setEntity(entity);
        this.setDomain(domain);
        this.setService(service);
    }

    public String getEntity() {
        return this.entity_id;
    }

    public void setEntity(String entity) {
        this.entity_id = entity;
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
    /*
    public String toJSON(){
        String json = String.format("{\n\t\"entity_id\": \"%s\",\n\t\"domain\": \"%s\",\n\t\"service\": \"%s\"\n}", this.getEntity(), this.getDomain(), this.getService());
        return json;
    }
     */
}
