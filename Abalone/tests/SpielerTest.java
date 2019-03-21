import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spieler;

public class SpielerTest {

	
	static Spieler a;
	FarbEnum farbe;
	String name;
	
	@BeforeClass
	public static void setUp() {
		a = new Spieler("Johannes", FarbEnum.WEISS); 
	}
	
	@Test
	public void testGetFarbe() {
		assertEquals(FarbEnum.WEISS, a.getFarbe());
	}
	
	@Test
	public void testSetFarbe() {
		assertFalse(a.getFarbe() == new Spieler("Jens", FarbEnum.WEISS).getFarbe());
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
	public void testToString() {
		String s = "Spieler 1 mit dem Namen Johannes spielt die Farbe WEISS "
				+ "und hat schon 0 Kugeln eliminiert";
		assertEquals(s, a.toString());
	}
	
	@Test
	public void testHashCode() {
		assertTrue(a.hashCode() == 1);
	}
	
	@Test
	public void testGetEliminierteKugeln() {
		assertEquals(3, a.getEliminierteKugeln());
	}
	
	@Test
	public void testEliminiereKugel() {
		a.eliminiereKugel();
		a.eliminiereKugel();
		a.eliminiereKugel();
		assertTrue(3 == a.getEliminierteKugeln());
	}
}
