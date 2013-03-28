package ca.ulaval.glo4002.communication;

import java.util.HashMap;

public class RegistrationCommunicator extends Communicator {

    private static final String RESOURCE = "register/";

    private String response;

    public RegistrationCommunicator() {
    }

    public void sendRegistrationRequest(HashMap<String, String> attributes) {
        String message = messageEncoder.generateEncodedMessage(attributes);
        response = postRequestSender.sendRequest(RESOURCE, message);
    }

    public int retrieveUserID() {
        int userID = Integer.parseInt(response);
        return userID;
    }

}