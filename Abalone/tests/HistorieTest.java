import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import abalone.Historie;
import abalone.Spielzug;

public class HistorieTest {

	static Spielzug sz1, sz2, sz3, sz4, sz5;
	static Historie historie1, historie2;
	
	@BeforeClass
	public static void setUp() {
		sz1 = new Spielzug("C3", "D3");
		sz2 = new Spielzug("D3D5", "E3");
		sz3 = new Spielzug("C3D3", "C4");
		sz4 = new Spielzug("C5C3", "D5");
		sz5 = new Spielzug("A1", "A2");
		
		historie1 = new Historie();
		historie2 = new Historie();
	}
	
	@Test
	public void testSpielzugHinzufuegen1() {
		historie1.spielzugHinzufuegen(sz1);
		String h1 = historie1.getZuege();
		assertEquals("1. C3 - D3\n", h1);
	}
	
	@Test
	public void testSpielzugHinzufuegen2() {
		historie1.spielzugHinzufuegen(sz2);
		String h2 = historie1.getZuege();
		assertEquals("1. C3 - D3\n2. D3D5 - E3\n", h2);
	}
	
	@Test
	public void testSpielzugHinzufuegen3() {
		historie1.spielzugHinzufuegen(sz3);
		String h3 = historie1.getZuege();
		assertEquals("1. C3 - D3\n2. D3D5 - E3\n3. C3D3 - C4\n", h3);
	}
	
	@Test
	public void testSpielzugHinzufuegen4() {
		historie2.spielzugHinzufuegen(sz4);
		String h4 = historie2.getZuege();
		assertEquals("1. C5C3 - D5\n", h4);
	}
	
	@Test
	public void testSpielzugHinzufuegen5() {
		historie2.spielzugHinzufuegen(sz5);
		String h5 = historie2.getZuege();
		assertEquals("1. C5C3 - D5\n2. A1 - A2\n", h5);
	}

}
