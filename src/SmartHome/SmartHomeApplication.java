package SmartHome;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class SmartHomeApplication {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.GUI, "true");
        //p.setParameter(Profile.MTPS, "jade.mtp.http.MessageTransportProtocol(http://DESKTOP-4RUSEA1:7778/acc)");
        ContainerController cc = rt.createMainContainer(p);

        AgentController ac;

        try {
            // Morador
            //ac = cc.createNewAgent("Morador", "SmartHome.agents.Morador", null);
            //ac.start();
            // Comandante
            ac = cc.createNewAgent("Comandante", "SmartHome.agents.Comandante", null);
            ac.start();
            // Peao
            ac = cc.createNewAgent("Peao.light.bathroomlight", "SmartHome.agents.Peao", null);
            ac.start();
            // Receptor
            ac = cc.createNewAgent("Receptor", "SmartHome.agents.Receptor", null);
            ac.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
