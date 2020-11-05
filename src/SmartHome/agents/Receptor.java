package SmartHome.agents;

import SmartHome.messages.StatusEquipamento;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Receptor extends Agent {

    // Range sensor de luz
    private int minBrightness = 0;
    private int maxBrightness = 100;

    // Range sensor de temperatura
    private int minTemperature = 18;
    private int maxTemperature = 80;

    public String AIDName;

    @Override
    protected void setup() {
        this.AIDName = this.getAID().getName();
        System.out.println("Setting up "+ this.AIDName +"...");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                try {

                    // Gerando dados do sensor de temperatura
                    int randomTemperature = ThreadLocalRandom.current().nextInt(minTemperature, maxTemperature + 1);
                    // Gerando dados do sensor de luz
                    int randomBrightness = ThreadLocalRandom.current().nextInt(minBrightness, maxBrightness + 1);


                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.setContentObject(new StatusEquipamento("light.bathroomlight", randomTemperature, randomBrightness));

                    AID receiver = new AID("Morador", AID.ISLOCALNAME);

                    msg.addReceiver(receiver);
                    //send(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
