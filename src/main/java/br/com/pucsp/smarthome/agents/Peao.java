package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.interfaces.PeaoInterface;
import br.com.pucsp.smarthome.messages.Instrucao;
import br.com.pucsp.smarthome.models.ServiceData;
import br.com.pucsp.smarthome.models.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class Peao extends Agent implements PeaoInterface {

    @Override
    protected void setup() {
        registerO2AInterface(PeaoInterface.class, this);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                try {
                    if (msg != null) {
                        String json = msg.getContent();
                        Instrucao instrucao = objectMapper.readValue(json, Instrucao.class);
                        log.info("[{}] - Instrucao recebida: {} do {}", this.myAgent.getAID().getName(), json, msg.getSender().getName());
                        
                        ServiceData sd = new ServiceData(instrucao.getEntityID());
                        List<State> sl = homeAssistantService.callService(instrucao.getDomain(), instrucao.getService(), sd);
                        log.info("[{}] - Controle do equipamento realizado.", this.myAgent.getAID().getName());
                        
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
