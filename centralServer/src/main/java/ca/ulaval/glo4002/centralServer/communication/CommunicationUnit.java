package ca.ulaval.glo4002.centralServer.communication;

import ca.ulaval.glo4002.centralServer.user.User;
import ca.ulaval.glo4002.common.POSTRequestSender;

public class CommunicationUnit {

    private POSTRequestSender postRequestSender = new POSTRequestSender();
    private String resource;

    public static enum CommunicationType {
        FIRE, INTRUSION
    };

    public CommunicationUnit(CommunicationType communicationType) {
        resource = generateResourceURL(communicationType);
    }

    public void sendMessageToEmergencyServer(User obtainedUser) {
        String messageToSend = obtainedUser.getAddress();
        postRequestSender.sendRequest(resource, messageToSend);

    }

    private String generateResourceURL(CommunicationType communicationType) {
        return String.format("%s", communicationType.toString().toLowerCase());
    }

}
