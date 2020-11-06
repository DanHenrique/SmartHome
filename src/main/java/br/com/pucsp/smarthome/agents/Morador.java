package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.interfaces.ComandanteInterface;
import br.com.pucsp.smarthome.interfaces.MoradorInterface;
import br.com.pucsp.smarthome.messages.Instrucao;
import br.com.pucsp.smarthome.messages.StatusEquipamento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class Morador extends Agent implements MoradorInterface {

    @Override
    protected void setup() {
        registerO2AInterface(MoradorInterface.class, this);
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                Instrucao instrucao = new Instrucao("light.bathroomlight", "homeassistant", "turn_on");
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
