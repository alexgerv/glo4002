package ca.ulaval.glo4002.devices;

import ca.ulaval.glo4002.devices.Siren;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SirenTest {

	private Siren siren;
	
	@Before
	public void setUp() {
		siren = new Siren();
	}
	
	@Test
	public void whenInitializedSirenDoesNotRing() {
		assertFalse(siren.isRigging());
	}
	
	@Test
	public void whenSirenActivatedItIsRigging() {
		siren.activate();
		assertTrue(siren.isRigging());
	}
	
	@Test
	public void givenAnActivatedSirenWhenDeactivatedThenDoesNotRing() {
		siren.activate();
		siren.deactivate();
		assertFalse(siren.isRigging());
	}
}
