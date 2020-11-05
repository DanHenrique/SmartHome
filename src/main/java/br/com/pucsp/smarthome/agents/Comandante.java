package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.behaviours.EnviarInstrucaoParaPeao;
import br.com.pucsp.smarthome.messages.Instrucao;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Comandante extends Agent {

    private final static Logger log = LoggerFactory.getLogger(Instrucao.class);

    public String AIDName;

    @Override
    protected void setup() {
        this.AIDName = this.getAID().getName();

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                try {
                    if (msg != null) {
                        //Instrucao instrucao = (Instrucao) msg.getContentObject();
                        String instrucao = msg.getContent();
                        //System.out.println("["+AIDName+"] - Instrucao recebida: " + instrucao.toJSON() + " do " + msg.getSender().getName());
                        log.info("[{}] - Instrucao recebida: {} do {}", AIDName, instrucao, msg.getSender().getName());
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

    protected void enviarInstrucaoParaPeao(Instrucao instrucao){
        String receiverName = String.format("Peao.%s", instrucao.getEntity());
        AID receiver = new AID(receiverName, AID.ISLOCALNAME );
        System.out.println("["+this.AIDName+"] Enviando instrucao para " + receiverName );
        addBehaviour(new EnviarInstrucaoParaPeao(this, instrucao, receiver));
    }

    protected void enviarInstrucaoParaPeao(String instrucao){
        //String receiverName = String.format("Peao.%s", instrucao.getEntity());
        //AID receiver = new AID(receiverName, AID.ISLOCALNAME );
        //System.out.println("["+this.AIDName+"] Enviando instrucao para " + receiverName );
        //addBehaviour(new EnviarInstrucaoParaPeao(this, instrucao, receiver));
    }
}
