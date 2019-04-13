import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.Spielfeld;
import abalone.spielbrett.Spielfeld.Spielfigur;

public class SpielfigurTest {
	static Spielbrett spielbrett;
	static Spielfeld feld;
	static Spielfigur testFigur;
	static Spielfeld feld2;
	static Spielfigur testFigur2;

	@BeforeClass
	public static void setUp() {
		spielbrett = new Spielbrett();
		feld = spielbrett.getFeld("C4");
		testFigur = feld.getFigur();
		feld2 = spielbrett.getFeld("G5");
		testFigur2 = feld2.getFigur();
	}
	
	@Test
	public void testGetfarbe() {
		assertEquals(FarbEnum.SCHWARZ, testFigur.getFarbe());
	}
	
	@Test
	public void testSpielfigur() {
		Spielfigur figur = feld.new Spielfigur(feld, "SCHWARZ");
		FarbEnum farbe = FarbEnum.SCHWARZ;
		
		assertEquals(farbe, figur.getFarbe());
	}
	
	@Test
	public void testSpielfigur1() {
		Spielfigur figur = feld.new Spielfigur(feld, FarbEnum.SCHWARZ);
		FarbEnum farbe = FarbEnum.SCHWARZ;
		
		assertEquals(farbe, figur.getFarbe());
		
	}
	
	@Test (expected = RuntimeException.class)
	public void testSpielfigurEx() {
		Spielfigur figur = feld.new Spielfigur(null, FarbEnum.WEISS);
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
	
	@Test
	public void testToString2() {
		String actual = testFigur2.toString();
		String expected = "Eine Figur der Farbe " + FarbEnum.WEISS;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGleicheFarbe() {
		FarbEnum weiss = FarbEnum.WEISS;
		assertEquals(true, testFigur2.gleicheFarbe(weiss));
	}
	
	@Test
	public void testGleicheFarbe2() {
		FarbEnum schwarz = FarbEnum.SCHWARZ;
		assertEquals(true, testFigur.gleicheFarbe(schwarz));
	}
	
	@Test
	public void testGleicheFarbeMitNullWert() {
		FarbEnum weiss = null;
		assertEquals(false, testFigur.gleicheFarbe(weiss));
	}

}
