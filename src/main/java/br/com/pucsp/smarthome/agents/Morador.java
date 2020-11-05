package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.messages.Instrucao;
import br.com.pucsp.smarthome.messages.StatusEquipamento;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

public class Morador extends Agent {

    public String AIDName;

    @Override
    protected void setup() {
        this.AIDName = this.getAID().getName();

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                Instrucao instrucao = new Instrucao("light.bathroomlight", "homeassistant", "turn_on");
                msg.setContent(instrucao.toJSON());

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
                        StatusEquipamento status = (StatusEquipamento) msg.getContentObject();
                        System.out.println("["+this.getAgent().getAID().getName()+"] - Dados dos sensores recebido: " + status.toJSON() + " do " + msg.getSender().getName());
                    } else {
                        block();
                    }
                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
