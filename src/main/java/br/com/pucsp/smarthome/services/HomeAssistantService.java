package br.com.pucsp.smarthome.services;

import br.com.pucsp.smarthome.models.ServiceData;
import br.com.pucsp.smarthome.models.StateList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HomeAssistantService {

    public HomeAssistantService(){
    }

    private final static Logger log = LoggerFactory.getLogger(HomeAssistantService.class);

    @Value("${home-assistant.api.url}")
    public String apiUrl;

    public RestTemplate restTemplate = new RestTemplate();

    public void log(){
        log.info("Chamou");
    }

    public StateList callService(String domain, String service, ServiceData serviceData){
        String endpoint = String.format("%s/services/%s/%s", apiUrl, domain, service);

        HttpEntity<ServiceData> request = new HttpEntity<>(serviceData);
        StateList response = restTemplate.postForObject(endpoint, request, StateList.class);
        int teste = 0;
        teste++;
        return response;
        /*
        String endpoint = apiUrl + "";
        Quote q = restTemplate.getForObject(endpoint, Quote.class);
        int i = 0;
        i++;
        */
    }
}