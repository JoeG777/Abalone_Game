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
import abalone.spielbrett.SpielbrettException;
import abalone.spielbrett.SpielfeldException;

public class SpielbrettTest {

	static Spielbrett spielbrett;

	@BeforeClass
	public static void setUp() throws SpielfeldException {
		spielbrett = new Spielbrett();
	}


	@Test
	public void testGetFelderMitFarbe() {
		ArrayList<String> felderMitFarbe = spielbrett.getFelderMitFarbe(FarbEnum.WEISS);

		ArrayList<String> gemeinteFelder = new ArrayList<String>();
		String[] idSpielfelder = {"I5", "I6", "I7", "I8", "I9", "G5", "G6", "G7", 
				"H4", "H5", "H6", "H7", "H8", "H9", };
		
	
		for(String spielfeld : idSpielfelder) {
			gemeinteFelder.add(spielbrett.getFeld(spielfeld));
		}
		assertEquals(felderMitFarbe, gemeinteFelder);
	}
	
	@Test
	public void testGetFelderMitFarbe1() throws SpielbrettException {
		Spielzug zug = new Spielzug("C3", "D4");
		Spielzug zug1 = new Spielzug("A1", "G9");
		Spielzug zug2 = new Spielzug("B3", null);
		
		Spielzug[] zuege =  {zug, zug1, zug2};
		spielbrett.ziehe(zuege);
		
		ArrayList<String> felderMitFarbe = spielbrett.getFelderMitFarbe((FarbEnum.SCHWARZ));
		ArrayList<String> gemeinteFelder = new ArrayList<String>();
		
		String[] idSpielfelder = {"A2", "A3", "A4", "A5", "B1", "B2", "B4", "B5", "B6", "C4", "C5", "G9", "D4"};

		for(String id : idSpielfelder) {
			gemeinteFelder.add(spielbrett.getFeld(id));
		}
		assertEquals(felderMitFarbe, gemeinteFelder);


	}
	
	@Test
	public void testGetAusgangsfelder() {
		Spielzug zug = new Spielzug ("C3C5", "C4");
		String[] ausgangsfelder = spielbrett.getAusgangsfelder(zug);
		
		String c3 = spielbrett.getFeld("C3");
		String c4 = spielbrett.getFeld("C4");
		String c5 = spielbrett.getFeld("C5");
		
		String[] gemeinteFelder = {c3,c4,c5};
		
		assertArrayEquals(gemeinteFelder, ausgangsfelder);
	}
	
	@Test
	public void testGetAusgangsfelder1() {
		Spielzug zug = new Spielzug ("F2E1", "G1");
		String[] ausgangsfelder = spielbrett.getAusgangsfelder(zug);
		
		String e1 = spielbrett.getFeld("E1");
		String f2 = spielbrett.getFeld("F2");
		
		String[] gemeinteFelder = {f2,e1};
		
		assertArrayEquals(gemeinteFelder, ausgangsfelder);
	}
	
	
//	@Test
//	public void testZiehe1() {
//		Spielbrett testBrett = new Spielbrett();
//		Spielzug zug1 = new Spielzug("F2", null);
//		Spielzug[] zuege = {zug1};
//		
//		Spielbrett echtesBrett = new Spielbrett();
//		echtesBrett.getFeld("F2").setFigur(null);
//
//		testBrett.ziehe(zuege);
//		
//		String testBrettString = testBrett.toString();
//		String echtesBrettString = echtesBrett.toString();
//		
//		assertEquals(echtesBrettString, testBrettString);
//	}
	
//	@Test
//	public void testWerfen() {
//		Spielbrett testBrett = new Spielbrett();
//		Spielzug zug3 = new Spielzug("A3", "A2");
//		Spielzug zug2 = new Spielzug("A2", "A1");
//		Spielzug zug1 = new Spielzug("A1", null);
//		Spielzug[] zuege = {zug1, zug2, zug3};
//		
//		Spielbrett echtesBrett = new Spielbrett();
//		
//		testBrett.getFeld("A3").setFigur(echtesBrett.getFeld("A3").getFigur());
//		testBrett.getFeld("A2").setFigur(echtesBrett.getFeld("A2").getFigur());
//		testBrett.getFeld("A1").setFigur(echtesBrett.getFeld("I9").getFigur());
//		
//		echtesBrett.getFeld("A3").setFigur(null);
//		echtesBrett.getFeld("A2").setFigur(echtesBrett.getFeld("C5").getFigur());
//		echtesBrett.getFeld("A1").setFigur(echtesBrett.getFeld("C5").getFigur());
//		
//		testBrett.ziehe(zuege);
//		
//		String testBrettString = testBrett.toString();
//		String echtesBrettString = echtesBrett.toString();
//		
//		assertEquals(echtesBrettString, testBrettString);
//		
//	}
	
	@Test
	public void zieheFalscheEingabe() throws SpielbrettException {
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
	public void testeToString() throws SpielbrettException {
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
	
	@Test
	public void testGetFarbeDerFigurById() {
		assertEquals(FarbEnum.WEISS, spielbrett.getFarbeDerFigurById("I9"));
	}
	
	@Test
	public void testIstDurchGegnerBesetztById() {
		assertEquals(true, spielbrett.istDurchGegnerBesetztById("B6", FarbEnum.SCHWARZ));
	}
	
	@Test
	public void testHatNachbarById() {
		assertEquals(true, spielbrett.hatNachbarById("A5", "A6"));
	}
	
	@Test
	public void testGetNachbarIndexById() {
		assertEquals(1, spielbrett.getNachbarIndexById("A5", "B6"));
	}
}