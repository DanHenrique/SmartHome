package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.messages.Instrucao;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

public class Peao extends Agent {

    @Override
    protected void setup() {

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                try {
                    if (msg != null) {
                        Instrucao instrucao = (Instrucao) msg.getContentObject();
                        System.out.println("["+this.getAgent().getAID().getName()+"] - Instrucao recebida: " + instrucao.toJSON() + " do " + msg.getSender().getName());
                    } else {
                        block();
                    }
                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void enviarComandoParaEquipamento(){
        //URL url = new URL("http://raspberrypi:8123/api/services/homeassistant/turn_on");
    }
}
