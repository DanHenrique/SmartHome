package br.com.pucsp.smarthome.agents;

import br.com.pucsp.smarthome.interfaces.PeaoInterface;
import br.com.pucsp.smarthome.interfaces.ReceptorInterface;
import br.com.pucsp.smarthome.messages.StatusEquipamento;
import br.com.pucsp.smarthome.services.HomeAssistantService;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Receptor extends Agent implements ReceptorInterface {

    @Override
    protected void setup() {
        registerO2AInterface(ReceptorInterface.class, this);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Gerando dados do sensor de temperatura
                int randomTemperature = generateTemperature();
                // Gerando dados do sensor de luz
                int randomBrightness = generateBrightness();

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                StatusEquipamento status = new StatusEquipamento("light.bathroomlight", randomTemperature, randomBrightness);
                msg.setContent(status.toJSON());

                AID receiver = new AID("Morador", AID.ISLOCALNAME);

                msg.addReceiver(receiver);
                send(msg);
            }
        });
    }

    @Override
    public int generateTemperature() {
        return ThreadLocalRandom.current().nextInt(minTemperature, maxTemperature + 1);
    }

    @Override
    public int generateBrightness() {
        return ThreadLocalRandom.current().nextInt(minBrightness, maxBrightness + 1);
    }
}
