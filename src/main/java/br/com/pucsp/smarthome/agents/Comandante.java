package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.behaviours.EnviarInstrucaoParaPeaoBehaviour;
import br.com.pucsp.smarthome.interfaces.ComandanteInterface;
import br.com.pucsp.smarthome.messages.Instrucao;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Comandante extends Agent implements ComandanteInterface {

    @Override
    protected void setup() {
        registerO2AInterface(ComandanteInterface.class, this);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                try {
                    if (msg != null) {
                        String json = msg.getContent();
                        Instrucao instrucao = objectMapper.readValue(json, Instrucao.class);
                        //System.out.println("["+AIDName+"] - Instrucao recebida: " + instrucao.toJSON() + " do " + msg.getSender().getName());
                        log.info("[{}] - Instrucao recebida: {} do {}", this.myAgent.getAID().getName(), json, msg.getSender().getName());
                        enviarInstrucaoParaPeao(instrucao);
                    } else {
                        block();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void enviarInstrucaoParaPeao(Instrucao instrucao){
        String receiverName = String.format("Peao.%s", instrucao.getEntityID());
        AID receiver = new AID(receiverName, AID.ISLOCALNAME );
        log.info("[{}] - Enviando instrucao para {}", this.getAID().getName(), receiverName);
        addBehaviour(new EnviarInstrucaoParaPeaoBehaviour(this, instrucao, receiver));
    }
}
