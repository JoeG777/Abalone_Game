import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spielbrett;
import abalone.Spielfeld;
import abalone.Spielfigur;

public class SpielfigurTest {
	static Spielbrett spielbrett;
	static Spielfeld feld;
	static Spielfigur testFigur;

	@BeforeClass
	public static void setUp() {
		spielbrett = new Spielbrett();
		feld = spielbrett.getFeld("C4");
		testFigur = feld.getFigur();
	}
	
	@Test
	public void testGetfarbe() {
		assertEquals(FarbEnum.SCHWARZ, testFigur.getFarbe());
	}
	
	@Test
	public void testSpielfigur() {
		Spielfigur figur = new Spielfigur(feld, "SCHWARZ");
		FarbEnum farbe = FarbEnum.SCHWARZ;
		
		assertEquals(farbe, figur.getFarbe());
	}
	
	@Test
	public void testSpielfigur1() {
		Spielfigur figur = new Spielfigur(feld, FarbEnum.SCHWARZ);
		FarbEnum farbe = FarbEnum.SCHWARZ;
		
		assertEquals(farbe, figur.getFarbe());
		
	}
	
	@Test (expected = RuntimeException.class)
	public void testSpielfigurEx() {
		Spielfigur figur = new Spielfigur(null, FarbEnum.WEISS);
	}
	
	
	@Test
	public void testSetFarbe() {
		testFigur.setFarbe(FarbEnum.WEISS);
		assertTrue(FarbEnum.WEISS.equals(testFigur.getFarbe()));
	}
	
	@Test
	public void testToString() {
		String actual = testFigur.toString();
		String expected = "Eine Figur der Farbe " + FarbEnum.SCHWARZ;
		
		assertEquals(expected, actual);
	}
	


}
