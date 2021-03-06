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
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                AID receiver = new AID("Morador", AID.ISLOCALNAME);

                // Gerando dados do sensor de temperatura
                int randomTemperature = generateTemperature();
                // Gerando dados do sensor de luz
                int randomBrightness = generateBrightness();

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                StatusEquipamento status = new StatusEquipamento("light.lampada", randomTemperature, randomBrightness);
                msg.setContent(status.toJSON());

                msg.addReceiver(receiver);
                send(msg);

                // Gerando dados do sensor de temperatura
                randomTemperature = generateTemperature();
                // Gerando dados do sensor de luz
                randomBrightness = generateBrightness();

                msg = new ACLMessage(ACLMessage.INFORM);
                status = new StatusEquipamento("switch.tomada", randomTemperature, randomBrightness);
                msg.setContent(status.toJSON());

                msg.addReceiver(receiver);
                send(msg);

                // Gerando dados do sensor de temperatura
                randomTemperature = generateTemperature();
                // Gerando dados do sensor de luz
                randomBrightness = generateBrightness();

                msg = new ACLMessage(ACLMessage.INFORM);
                status = new StatusEquipamento("ar.condicionado", randomTemperature, randomBrightness);
                msg.setContent(status.toJSON());

                msg.addReceiver(receiver);
                send(msg);

                log.info("[{}] - Enviando dados dos sensores para: {} ", this.myAgent.getAID().getName(), receiver.getName());
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
