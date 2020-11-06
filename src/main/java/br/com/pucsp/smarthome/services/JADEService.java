package br.com.pucsp.smarthome.services;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class JADEService {

    @Value("${jade.host}")
    public String host;

    @Value("${jade.gui}")
    public String gui;

    @Autowired
    HomeAssistantService homeAssistantService;

    private final static Logger log = LoggerFactory.getLogger(JADEService.class);

    @EventListener(ApplicationReadyEvent.class)
    public void initJADEContainer() {

        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, host);
        p.setParameter(Profile.GUI, gui);
        ContainerController cc = rt.createMainContainer(p);

        AgentController ac;

        try {
            // Morador
            ///*
            ac = cc.createNewAgent("Morador", "br.com.pucsp.smarthome.agents.Morador", null);
            log.info("Iniciando Agente Morador...");
            ac.start();
            //*/
            // Comandante
            ac = cc.createNewAgent("Comandante", "br.com.pucsp.smarthome.agents.Comandante", null);
            log.info("Iniciando Agente Comandante...");
            ac.start();
            // Peao
            ac = cc.createNewAgent("Peao.light.bathroomlight", "br.com.pucsp.smarthome.agents.Peao", null);
            log.info("Iniciando Agente Peao...");
            ac.start();
            // Receptor
            ac = cc.createNewAgent("Receptor", "br.com.pucsp.smarthome.agents.Receptor", null);
            log.info("Iniciando Agente Receptor...");
            ac.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
