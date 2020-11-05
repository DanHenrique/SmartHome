package SmartHome.messages;

import jade.util.leap.Serializable;

public class StatusEquipamento implements Serializable {
    // Atributos
    public String entity_id;
    public int brightness;
    public int temperature;

    // Métodos
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

    public String toJSON(){
        String json = String.format("{\n\t\"entity_id\": \"%s\",\n\t\"brightness\": \"%d\",\n\t\"temperature\": \"%d\"\n}", this.getEntity(), this.getBrightness(), this.getTemperature());
        return json;
    }
}
