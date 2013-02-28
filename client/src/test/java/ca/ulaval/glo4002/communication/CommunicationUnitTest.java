package ca.ulaval.glo4002.communication;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.communication.CommunicationUnit.CommunicationType;

public class CommunicationUnitTest {
    private static final int USER_ID = 1;
    private static final CommunicationType COMMUNICATION_TYPE = CommunicationType.INTRUSION;
    @Mock
    private POSTRequestSender postRequestSender;
    @InjectMocks
    private CommunicationUnit communicationUnit = new CommunicationUnit(
            USER_ID, COMMUNICATION_TYPE);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void callsSendPostRequestWhenSending() {
        communicationUnit.send();
        verify(postRequestSender).sendPostRequest(anyString(), anyString());
    }
}