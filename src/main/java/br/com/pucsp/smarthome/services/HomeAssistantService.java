package br.com.pucsp.smarthome.services;

import br.com.pucsp.smarthome.models.ServiceData;
import br.com.pucsp.smarthome.models.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HomeAssistantService {

    public HomeAssistantService(){
    }

    private final static Logger log = LoggerFactory.getLogger(HomeAssistantService.class);

    ObjectMapper objectMapper = new ObjectMapper();

    public String apiUrl = "http://raspberrypi:8123/api";

    private String tokenHA = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIwYjMxYzM0Yjk2NGQ0Zjk2ODZjYmJjNTkxYTFiZTYzOSIsImlhdCI6MTYwNDcxNzIwOCwiZXhwIjoxOTIwMDc3MjA4fQ.Z5lwJx9cc0NeeDhmX1grvhYDEmp5iHztndaqY8HtgCk";

    public RestTemplate restTemplate = new RestTemplate();

    public List<State> callService(String domain, String service, ServiceData serviceData){
        String endpoint = String.format("%s/services/%s/%s", apiUrl, domain, service);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHA);


        HttpEntity<ServiceData> request = new HttpEntity<>(serviceData, headers);

        List<State> response = restTemplate.postForObject(endpoint, request, List.class);


        return response;
        /*
        String endpoint = apiUrl + "";
        Quote q = restTemplate.getForObject(endpoint, Quote.class);
        int i = 0;
        i++;
        */
    }
}