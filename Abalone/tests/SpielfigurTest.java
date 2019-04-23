import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielfeldException;

public class SpielfigurTest {
	static Spielbrett spielbrett;
	static String feld;
//	static Spielfigur testFigur;
	static String feld2;
//	static Spielfigur testFigur2;

	@BeforeClass
	public static void setUp() throws SpielfeldException {
		spielbrett = new Spielbrett();
		feld = spielbrett.getFeld("C4");
//		testFigur = feld.getFigur();
		feld2 = spielbrett.getFeld("G5");
//		testFigur2 = feld2.getFigur();
	}
	
	@Test
	public void testGetfarbe() {
		assertEquals(FarbEnum.SCHWARZ, spielbrett.getFarbeDerFigurById(feld));
	}
	
	@Test
	public void testSpielfigur() {
		spielbrett.getFeldById(feld).setAndInit("SCHWARZ");
		
		assertEquals(FarbEnum.SCHWARZ, spielbrett.getFarbeDerFigurById(feld));
	}
	
	@Test
	public void testSpielfigur1() {
		spielbrett.getFeldById(feld).setAndInitFigur(FarbEnum.SCHWARZ);
		
		assertEquals(FarbEnum.SCHWARZ,spielbrett.getFarbeDerFigurById(feld));
		
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
