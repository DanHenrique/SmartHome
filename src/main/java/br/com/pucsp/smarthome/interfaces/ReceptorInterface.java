package br.com.pucsp.smarthome.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public interface ReceptorInterface {
    Logger log = LoggerFactory.getLogger(ReceptorInterface.class);

    ObjectMapper objectMapper = new ObjectMapper();

    // Range sensor de luz
    int minBrightness = 0;
    int maxBrightness = 100;

    // Range sensor de temperatura
    int minTemperature = 18;
    int maxTemperature = 80;

    int generateTemperature();

    int generateBrightness();
}