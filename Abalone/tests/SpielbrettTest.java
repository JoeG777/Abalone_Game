//import static org.junit.Assert.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
	public void testGetFelderMitFarbe() {
		ArrayList<Spielfeld> felderMitFarbe = spielbrett.getFelderMitFarbe(FarbEnum.WEISS);

		ArrayList<Spielfeld> gemeinteFelder = new ArrayList<Spielfeld>();
		String[] idSpielfelder = {"I5", "I6", "I7", "I8", "I9", "G5", "G6", "G7", 
				"H4", "H5", "H6", "H7", "H8", "H9", };
		
	
		for(String spielfeld : idSpielfelder) {
			gemeinteFelder.add(spielbrett.getFeld(spielfeld));
		}
		assertEquals(felderMitFarbe, gemeinteFelder);
	}
	
	@Test
	public void testGetFeld() {
		Spielfeld i9 = spielbrett.getBrett().get("I9");
		assertEquals(i9, spielbrett.getFeld("I9"));
	}
	
	@Test
	public void testGetAusgangsfelder() {
		Spielzug zug = new Spielzug ("C3C5", "C4");
		Spielfeld[] ausgangsfelder = spielbrett.getAusgangsfelder(zug);
		
		Spielfeld c3 = spielbrett.getFeld("C3");
		Spielfeld c4 = spielbrett.getFeld("C4");
		Spielfeld c5 = spielbrett.getFeld("C5");
		
		Spielfeld[] gemeinteFelder = {c3,c4,c5};
		
		assertArrayEquals(gemeinteFelder, ausgangsfelder);
	}
	
	@Test
	public void testGetAusgangsfelder1() {
		Spielzug zug = new Spielzug ("F2E1", "G1");
		Spielfeld[] ausgangsfelder = spielbrett.getAusgangsfelder(zug);
		
		Spielfeld e1 = spielbrett.getFeld("E1");
		Spielfeld f2 = spielbrett.getFeld("F2");
		
		Spielfeld[] gemeinteFelder = {f2,e1};
		
		assertArrayEquals(gemeinteFelder, ausgangsfelder);
	}
	
	@Test
	public void testZiehe() {
		Spielbrett testBrett = new Spielbrett();
		Spielzug zug1 = new Spielzug("C3", "D3");
		Spielzug zug2 = new Spielzug("C4", "D4");
		Spielzug zug3 = new Spielzug("C5", "D5");
		Spielzug[] zuege = {zug1, zug2, zug3};
		
		Spielbrett echtesBrett = new Spielbrett();
		echtesBrett.getFeld("D3").setFigur(echtesBrett.getFeld("C3").getFigur());
		echtesBrett.getFeld("D4").setFigur(echtesBrett.getFeld("C4").getFigur());
		echtesBrett.getFeld("D5").setFigur(echtesBrett.getFeld("C5").getFigur());
		echtesBrett.getFeld("C3").setFigur(null);
		echtesBrett.getFeld("C4").setFigur(null);
		echtesBrett.getFeld("C5").setFigur(null);

		testBrett.ziehe(zuege);
		
		String testBrettString = testBrett.toString();
		String echtesBrettString = echtesBrett.toString();
		
		assertEquals(echtesBrettString, testBrettString);
		
		
		
	}
	
	@Test
	public void testZiehe1() {
		Spielbrett testBrett = new Spielbrett();
		Spielzug zug1 = new Spielzug("F2", null);
		Spielzug[] zuege = {zug1};
		
		Spielbrett echtesBrett = new Spielbrett();
		echtesBrett.getFeld("F2").setFigur(null);

		testBrett.ziehe(zuege);
		
		String testBrettString = testBrett.toString();
		String echtesBrettString = echtesBrett.toString();
		
		assertEquals(echtesBrettString, testBrettString);
	}
	
	public void testZiehe2() {
		
	}
}
