package ca.ulaval.glo4002.communication;

import java.util.HashMap;

public class RegistrationCommunicationUnit extends CommunicationUnit {

    private String response;

    public void sendRegistrationRequest(HashMap<String, String> attributes) {
        String message = messageEncoder.generateEncodedMessage(attributes);
        response = postRequestSender.sendRequest(resource, message);
    }

    public int retrieveUserID() {
        int userID = new Integer(response);
        return userID;
    }
}
