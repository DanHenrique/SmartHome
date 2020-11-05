package SmartHome.agents;

import SmartHome.messages.Instrucao;
import SmartHome.messages.StatusEquipamento;
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
        System.out.println("Setting up "+ this.AIDName +"...");

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                try {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContentObject(new Instrucao("light.bathroomlight", "homeassistant", "turn_on"));

                AID receiver = new AID("Comandante", AID.ISLOCALNAME);

                msg.addReceiver(receiver);
                send(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

