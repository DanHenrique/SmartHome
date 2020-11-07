package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.interfaces.MoradorInterface;
import br.com.pucsp.smarthome.interfaces.PeaoInterface;
import br.com.pucsp.smarthome.messages.Instrucao;
import br.com.pucsp.smarthome.models.ServiceData;
import br.com.pucsp.smarthome.models.StateList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

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
                        /*
                        ServiceData sd = new ServiceData(instrucao.getEntityID());
                        StateList sl = homeAssistantService.callService(instrucao.getDomain(), instrucao.getService(), sd);
                        log.info("[{}] - Resposta recebida");
                        */
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
