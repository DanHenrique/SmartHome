package br.com.pucsp.smarthome.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MoradorInterface {
    Logger log = LoggerFactory.getLogger(MoradorInterface.class);

    ObjectMapper objectMapper = new ObjectMapper();
}
