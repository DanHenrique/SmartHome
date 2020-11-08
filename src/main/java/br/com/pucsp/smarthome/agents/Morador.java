package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.interfaces.MoradorInterface;
import br.com.pucsp.smarthome.messages.Instrucao;
import br.com.pucsp.smarthome.messages.StatusEquipamento;
import com.fasterxml.jackson.core.JsonProcessingException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Morador extends Agent implements MoradorInterface {

    public String service = "";

    @Override
    protected void setup() {
        registerO2AInterface(MoradorInterface.class, this);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);


                if(service.equals("turn_off"))
                    service = "turn_on";
                else
                    service = "turn_off";

                Instrucao instrucao = new Instrucao("light.lampada", "homeassistant", service);
                String json = instrucao.toJSON();
                msg.setContent(json);

                AID receiver = new AID("Comandante", AID.ISLOCALNAME);

                msg.addReceiver(receiver);
                send(msg);
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                try {
                    if (msg != null) {
                        String json = msg.getContent();
                        log.info("[{}] - Dados dos sensores recebido: {} do {}", this.getAgent().getAID().getName(), json, msg.getSender().getName());
                        StatusEquipamento status = objectMapper.readValue(json, StatusEquipamento.class);
                    } else {
                        block();
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
