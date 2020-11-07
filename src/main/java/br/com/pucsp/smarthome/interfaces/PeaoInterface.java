package br.com.pucsp.smarthome.interfaces;

import br.com.pucsp.smarthome.services.HomeAssistantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface PeaoInterface {
    Logger log = LoggerFactory.getLogger(PeaoInterface.class);

    ObjectMapper objectMapper = new ObjectMapper();

    HomeAssistantService homeAssistantService = new HomeAssistantService();
}
