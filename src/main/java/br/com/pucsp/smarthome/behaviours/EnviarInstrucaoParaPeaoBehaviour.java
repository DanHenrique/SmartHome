package br.com.pucsp.smarthome.behaviours;

import br.com.pucsp.smarthome.messages.Instrucao;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class EnviarInstrucaoParaPeaoBehaviour extends OneShotBehaviour {

    // Atributos
    public Instrucao instrucao;
    public AID receiver;

    // Métodos
    public EnviarInstrucaoParaPeaoBehaviour(Agent agent, Instrucao instrucao, AID receiver){
        super(agent);
        this.instrucao = instrucao;
        this.receiver = receiver;
    }

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(this.instrucao.toJSON());
        msg.addReceiver(this.receiver);
        Agent a = this.getAgent();
        a.send(msg);

    }
}