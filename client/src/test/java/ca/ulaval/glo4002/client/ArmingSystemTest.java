package ca.ulaval.glo4002.client;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ArmingSystemTest {

    private static final String VALID_NIP = "1234";
    private static final String RAPID_NIP = "#0";
    @Mock
    private SystemState systemState;
    @Mock
    private DelayTimer delayTimer;
    @InjectMocks
    private ArmingSystem armingSystem;

    @Before
    public void setUp() {
        armingSystem = new ArmingSystem();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canArmSystemWhenSystemIsReady() {
        doReturn(true).when(systemState).isReady();
        armingSystem.performNIPValidation(VALID_NIP);
        verify(systemState).changeStatusToExitDelay();
    }

    @Test
    public void canArmSystemWhenDelayIsExpired() {
        doReturn(true).when(systemState).isReady();
        doReturn(false).when(systemState).isArmed();
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                armingSystem.delayExpired(invocation.getArguments()[1]);
                return null;
            }
        }).when(delayTimer).startDelay(anyInt(), any());

        armingSystem.performNIPValidation(VALID_NIP);

        verify(systemState).changeStatusToArmed();
    }

    @Test
    public void canArmSystemWithRapidArmingNIP() {
        doReturn(true).when(systemState).isReady();
        armingSystem.performNIPValidation(RAPID_NIP);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                armingSystem.delayExpired(invocation.getArguments()[1]);
                return null;
            }
        }).when(delayTimer).startDelay(anyInt(), any());

        armingSystem.performNIPValidation(VALID_NIP);

        verify(systemState).changeStatusToArmed();
    }

    @Test
    public void canDisarmSystem() {
        doReturn(true).when(systemState).isArmed();
        armingSystem.performNIPValidation(VALID_NIP);
        verify(systemState).changeStatusToDisarmed();
    }

    @Test
    public void cantDisarmWithRapidArmingNIP() {
        doReturn(true).when(systemState).isArmed();
        armingSystem.performNIPValidation(RAPID_NIP);
        verify(systemState, never()).changeStatusToDisarmed();
    }
}
