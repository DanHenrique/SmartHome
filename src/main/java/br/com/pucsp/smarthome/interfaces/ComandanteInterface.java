package br.com.pucsp.smarthome.interfaces;

import br.com.pucsp.smarthome.messages.Instrucao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ComandanteInterface {
    Logger log = LoggerFactory.getLogger(ComandanteInterface.class);

    ObjectMapper objectMapper = new ObjectMapper();

    void enviarInstrucaoParaPeao(Instrucao instrucao);
}
