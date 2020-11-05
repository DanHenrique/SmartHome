package SmartHome.models;

public class RequestBody {
    // Atributos
    public String entity_id;

    // Métodos
    public RequestBody(String entity){
        this.setEntity(entity);
    }

    public String getEntity(){
        return this.entity_id;
    }

    public void setEntity(String entity){
        this.entity_id = entity;
    }
}
