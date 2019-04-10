//import static org.junit.Assert.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
//import org.junit.Test;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spielzug;
import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.Spielfeld;

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
	public void testGetFelderMitFarbe1() {
		Spielzug zug = new Spielzug("C3", "D4");
		Spielzug zug1 = new Spielzug("A1", "G9");
		Spielzug zug2 = new Spielzug("B3", null);
		
		Spielzug[] zuege =  {zug, zug1, zug2};
		spielbrett.ziehe(zuege);
		
		ArrayList<Spielfeld> felderMitFarbe = spielbrett.getFelderMitFarbe((FarbEnum.SCHWARZ));
		ArrayList<Spielfeld> gemeinteFelder = new ArrayList<Spielfeld>();
		
		String[] idSpielfelder = {"A2", "A3", "A4", "A5", "B1", "B2", "B4", "B5", "B6", "C4", "C5", "G9", "D4"};

		for(String id : idSpielfelder) {
			gemeinteFelder.add(spielbrett.getFeld(id));
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
	
	@Test
	public void testWerfen() {
		Spielbrett testBrett = new Spielbrett();
		Spielzug zug3 = new Spielzug("A3", "A2");
		Spielzug zug2 = new Spielzug("A2", "A1");
		Spielzug zug1 = new Spielzug("A1", null);
		Spielzug[] zuege = {zug1, zug2, zug3};
		
		Spielbrett echtesBrett = new Spielbrett();
		
		testBrett.getFeld("A3").setFigur(echtesBrett.getFeld("A3").getFigur());
		testBrett.getFeld("A2").setFigur(echtesBrett.getFeld("A2").getFigur());
		testBrett.getFeld("A1").setFigur(echtesBrett.getFeld("I9").getFigur());
		
		echtesBrett.getFeld("A3").setFigur(null);
		echtesBrett.getFeld("A2").setFigur(echtesBrett.getFeld("C5").getFigur());
		echtesBrett.getFeld("A1").setFigur(echtesBrett.getFeld("C5").getFigur());
		
		testBrett.ziehe(zuege);
		
		String testBrettString = testBrett.toString();
		String echtesBrettString = echtesBrett.toString();
		
		assertEquals(echtesBrettString, testBrettString);
		
	}
	
	@Test
	public void zieheFalscheEingabe() {
		Spielbrett testBrett = new Spielbrett();
		Spielzug[] zuege = {null};
		testBrett.ziehe(zuege);
		try {
			testBrett.ziehe(zuege);
		}catch(IllegalArgumentException e) {
			String erwarteteMeldungString = "Ungueltiger Zug";
			assertEquals(erwarteteMeldungString, e.getMessage());
		}
	}
	
	@Test
	public void testeToString() {
		Spielbrett testBrett = new Spielbrett();
		String erwartet = "                    \n" +
				 "I      O O O O O \n" + 
				"H     O O O O O O \n" + 
				"G    - - O O O - - \n" + 
				"F   - - - - - - - - \n" + 
				"E  - - - - - - - - - \n" + 
				"D   - - - - - - - - \n" + 
				"C    - - X X X - -   9\n" + 
				"B     X X X X X X   8\n" + 
				"A      X X X X X   7\n" + 
				"                  6\n" + 
				"         1 2 3 4 5 \n";
		assertEquals(erwartet, testBrett.toString());
	}
}
