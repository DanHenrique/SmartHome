package br.com.pucsp.smarthome.services;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class JADEService {

    public String host;

    private static Logger logger = LoggerFactory.getLogger(JADEService.class);

    @EventListener(ApplicationReadyEvent.class)
    public void initJADEContainer() {

        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.GUI, "false");
        ContainerController cc = rt.createMainContainer(p);

        AgentController ac;

        try {
            // Morador
            //ac = cc.createNewAgent("Morador", "br.com.pucsp.smarthome.agents", null);
            logger.info("Iniciando Agente Morador...");
            //ac.start();
            // Comandante
            ac = cc.createNewAgent("Comandante", "br.com.pucsp.smarthome.agents.Comandante", null);
            logger.info("Iniciando Agente Comandante...");
            ac.start();
            // Peao
            ac = cc.createNewAgent("Peao.light.bathroomlight", "br.com.pucsp.smarthome.agents.Peao", null);
            logger.info("Iniciando Agente Peao...");
            ac.start();
            // Receptor
            ac = cc.createNewAgent("Receptor", "br.com.pucsp.smarthome.agents.Receptor", null);
            logger.info("Iniciando Agente Receptor...");
            ac.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
