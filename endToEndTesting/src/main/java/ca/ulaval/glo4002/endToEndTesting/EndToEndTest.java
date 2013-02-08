package ca.ulaval.glo4002.endToEndTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ca.ulaval.glo4002.centralServer.main.CentralServer;
import ca.ulaval.glo4002.client.DetectionSignalAdapter;
import ca.ulaval.glo4002.client.Signal;
import ca.ulaval.glo4002.client.SignalHandler;
import ca.ulaval.glo4002.emergencyServer.main.EmergencyServer;

public class EndToEndTest {
  private String RESPONSE_TO_POST_REQUEST_FROM_EMERGENCY = "POST request received at emergency server";
  private String RESPONSE_TO_POST_REQUEST_FROM_CENTRAL = "POST request received at central server";
  private String RESPONSE_TO_POST_REQUEST = RESPONSE_TO_POST_REQUEST_FROM_CENTRAL
      + RESPONSE_TO_POST_REQUEST_FROM_EMERGENCY;
  private int TIME_TO_DISARM_THE_ALARM_WHEN_SECONDARY_DOOR_OPENS = 0;
  private SignalHandler signalHandler;
  private DetectionSignalAdapter detectSignal;

  @Before
  public void setUp() throws Exception {
    CentralServer.startServer();
    EmergencyServer.startServer();

    signalHandler = SignalHandler.getInstance();
    detectSignal = new DetectionSignalAdapter();
  }

  @After
  public void tearDown() throws Exception {
    CentralServer.stopServer();
    EmergencyServer.stopServer();
  }

  @Ignore
  @Test
  public void endToEndTest() {
    detectSignal.sendSignalToSignalHandler(Signal.DetectorType.SecondaryDoor,
        TIME_TO_DISARM_THE_ALARM_WHEN_SECONDARY_DOOR_OPENS);
    String lastResponseFromServer = signalHandler.getLastResponseReceived();

    assertEquals(RESPONSE_TO_POST_REQUEST, lastResponseFromServer);
  }
}
