//import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.BeforeClass;
//import org.junit.Test;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spielbrett;
import abalone.Spielfeld;
import abalone.Spielzug;

public class SpielbrettTest {

	static Spielbrett spielbrett;

	@BeforeClass
	public static void setUp() {
		spielbrett = new Spielbrett();
	}



	@Test 
	public void bekommeRichtungTest() {
		Spielzug zug = new Spielzug("E2E4", "F4");
		int richtung = spielbrett.bekommeRichtung(zug);
		assertEquals(1, richtung);
	}

	@Test 
	public void getVorderstenSteinTest() {
		Spielfeld feld1 = spielbrett.getFeld("F6");
		Spielfeld feld2 = spielbrett.getFeld("E6");
		Spielfeld feld3 = spielbrett.getFeld("D6");

		Spielfeld[] felder = {feld1, feld2, feld3};
		Spielfeld vorderstes = spielbrett.getVorderstenStein(felder, 5);

		assertEquals(feld3, vorderstes);
	}

	@Test 
	public void getAusgangsfelderTest() {
		Spielzug zug = new Spielzug("C3C4","C4", 0, FarbEnum.WEISS);
		Spielfeld[] felder = spielbrett.getAusgangsfelder(zug);
		Spielfeld feld1 = spielbrett.getFeld("C3");
		Spielfeld feld2 = spielbrett.getFeld("C4");
		Spielfeld feld3 = spielbrett.getFeld("C5");

		Spielfeld[] gemeinteFelder= {feld1, feld2};

		Assert.assertArrayEquals(felder, gemeinteFelder);	
	}




	@Test 
	/**
	 * Testet 3zu1 Sumito.
	 */
	public void zieheTest() {
		spielbrett = new Spielbrett();
		spielbrett.getFeld("E5").setFigur(spielbrett.getFeld("C3").getFigur());
		spielbrett.getFeld("C3").setFigur(null);
		
		spielbrett.getFeld("E4").setFigur(spielbrett.getFeld("C4").getFigur());
		spielbrett.getFeld("C4").setFigur(null);
		
		spielbrett.getFeld("E3").setFigur(spielbrett.getFeld("C5").getFigur());
		spielbrett.getFeld("C5").setFigur(null);
		
		spielbrett.getFeld("E2").setFigur(spielbrett.getFeld("G5").getFigur());
		spielbrett.getFeld("G5").setFigur(null);
		
		spielbrett.getFeld("E1").setFigur(spielbrett.getFeld("G6").getFigur());
		spielbrett.getFeld("G6").setFigur(null);
		
		Spielzug zug = new Spielzug("E3E5", "E2",0, FarbEnum.SCHWARZ);
		Spielzug[] zuege = {zug};
		boolean erfolg = spielbrett.ziehe(zuege);
		
		
		assertTrue(erfolg);
	}
	
	/**
	 * Testet 2zu1 Sumito.
	 */
	
	@Test
	public void zieheTest1() {
		spielbrett = new Spielbrett();
		spielbrett.getFeld("C2").setFigur(spielbrett.getFeld("C3").getFigur());
		spielbrett.getFeld("C3").setFigur(null);
		
		spielbrett.getFeld("D2").setFigur(spielbrett.getFeld("C4").getFigur());
		spielbrett.getFeld("C4").setFigur(null);
		
		spielbrett.getFeld("E2").setFigur(spielbrett.getFeld("C5").getFigur());
		spielbrett.getFeld("C5").setFigur(null);
		
		spielbrett.getFeld("F2").setFigur(spielbrett.getFeld("G5").getFigur());
		spielbrett.getFeld("G5").setFigur(null);
		
		
		Spielzug zug = new Spielzug("E3C2", "F2", 1, FarbEnum.SCHWARZ);
		Spielzug[] zuege = {zug};
		boolean erfolg = spielbrett.ziehe(zuege);
		
		assertTrue(erfolg);
		
	}
	
	/**
	 * Testet, ob 3 eigene Kugeln 3 gegnerische 
	 * Schieben koennen (nicht moeglich).
	 */
	@Test
	public void zieheTest2() {
		spielbrett = new Spielbrett();
		spielbrett.getFeld("D3").setFigur(spielbrett.getFeld("C3").getFigur());
		spielbrett.getFeld("C3").setFigur(null);
		
		spielbrett.getFeld("E4").setFigur(spielbrett.getFeld("C4").getFigur());
		spielbrett.getFeld("C4").setFigur(null);
		
		spielbrett.getFeld("F5").setFigur(spielbrett.getFeld("C5").getFigur());
		spielbrett.getFeld("C5").setFigur(null);

		
		Spielzug zug = new Spielzug("D3F5", "G6",0, FarbEnum.SCHWARZ);
		Spielzug[] zuege = {zug};
		boolean erfolg = spielbrett.ziehe(zuege);
		assertFalse(erfolg);
	}
	
	@Test
	public void testGetFelderMitFarbe() {
		assertEquals();
	}
	
	@Test
	public void testGetFeld() {
		assertEquals();
	}
	
	@Test
	public void testGetBrett() {
		assertEquals();
	}
	
	@Test
	public void testWeiseKeyFeldZu() {
		assertEquals();
	}
}
