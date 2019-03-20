
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spieler;

public class SpielerTest {

	
	Spieler a;
	FarbEnum farbe;
	String name;
	
	@BeforeClass
	public void setUp() {
		a = new Spieler("Johannes", FarbEnum.WEISS);
	}
	
	@Test
	public void testGetFarbe() {
		// Warum ist die Farbe nicht WEISS?
		assertEquals(FarbEnum.WEISS, a.getFarbe());
	}

	@Test
	public void testSpielerID() {
		assertEquals(1, a.getSpielerID());
	}
	
	@Test
	public void testEquals() {
		//wegen der SpielerID
		assertFalse(a.equals(new Spieler("Johannes", FarbEnum.WEISS)));
	}
	
		@Test
	public void testSetFarbe() {
		assertFalse(a.getFarbe() == new Spieler("Jens", FarbEnum.WEISS).getFarbe());
	}
}
