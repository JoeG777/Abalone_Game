
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spieler;

public class SpielerTest {

	Spieler a;
	Spieler c; //KommentarTest
	
	@Before
	public void setUp() {
		a = new Spieler("Johannes", FarbEnum.WEISS);
	}
	
	@Test
	public void testSpielerID() {
		assertEquals(1, a.getSpielerID());
	}
	
	@Test
	public void testEquals() {
		assertFalse(a.equals(new Spieler("Johannes", FarbEnum.WEISS)));
	}
	
	@Test
	public void testGetFarbe() {
		assertEquals(FarbEnum.WEISS, a.getFarbe());
	}
	
	@Test
	public void testSetFarbe() {
		assertTrue(a.getFarbe() == new Spieler("Jens", FarbEnum.WEISS).getFarbe());
	}
}
