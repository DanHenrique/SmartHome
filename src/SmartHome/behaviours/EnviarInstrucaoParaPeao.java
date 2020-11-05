package SmartHome.behaviours;

import SmartHome.messages.Instrucao;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class EnviarInstrucaoParaPeao extends OneShotBehaviour {

    // Atributos
    public Instrucao instrucao;
    public AID receiver;

    // Métodos
    public EnviarInstrucaoParaPeao(Agent agent, Instrucao instrucao, AID receiver){
        super(agent);
        this.instrucao = instrucao;
        this.receiver = receiver;
    }

    @Override
    public void action() {
        try {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContentObject(this.instrucao);
            //AID receiver = new AID("Comandante", AID.ISLOCALNAME);
            msg.addReceiver(this.receiver);
            Agent a = this.getAgent();
            a.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
