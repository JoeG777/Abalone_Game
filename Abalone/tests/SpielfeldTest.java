import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abalone.spielbrett.Spielbrett;
//import abalone.spielbrett.Spielfeld;
import abalone.FarbEnum;

public class SpielfeldTest {
	Spielbrett brett;
//	Spielfeld hauptFeld;

	
	@Before
	public void setUp() {
//		brett = new Spielbrett();
//		hauptFeld = brett.getFeld("A1").setAndInitFigur(brett, "A1", FarbEnum.WEISS, null);
	}
	
//	@Test
//	public void testeSetzeNachbarn1() {
//		Spielfeld feld = brett.getBrett().get("A1");
//		Spielfeld[] nachbarn = feld.getNachbarn();
//		
//		Spielfeld rechts = brett.getBrett().get("A2"); 
//		Spielfeld rechtsOben = brett.getBrett().get("B2");
//		Spielfeld linksOben = brett.getBrett().get("B1");
//		
//		Spielfeld[] echteNachbarn = {null, linksOben, null,rechts, rechtsOben, null};
//		
//		assertArrayEquals(echteNachbarn, nachbarn);
//		
//	}
	
//	@Test
//	public void testeSetzeNachbarn2() {
//		Spielfeld feld = brett.getBrett().get("F5");
//		Spielfeld[] nachbarn = feld.getNachbarn();
//		
//		Spielfeld links = brett.getBrett().get("F4");
//		Spielfeld linksOben = brett.getBrett().get("G5");
//		Spielfeld linksUnten = brett.getBrett().get("E4");
//		Spielfeld rechts = brett.getBrett().get("F6");
//		Spielfeld rechtsOben = brett.getBrett().get("G6");
//		Spielfeld rechtsUnten = brett.getBrett().get("E5");
//		
//		Spielfeld[] echteNachbarn = {links, linksOben, linksUnten, rechts, rechtsOben, rechtsUnten};
//		
//		assertArrayEquals(nachbarn, echteNachbarn);
//	}
	
//	@Test 
//	public void testeSetzeNachbarn3() {
//		Spielfeld feld = brett.getBrett().get("E1");
//		Spielfeld[] nachbarn = feld.getNachbarn();
//		
//		Spielfeld rechts = brett.getBrett().get("E2");
//		Spielfeld rechtsOben = brett.getBrett().get("F2");
//		Spielfeld rechtsUnten = brett.getBrett().get("D1");
//		
//		Spielfeld[] echteNachbarn = {null, null, null, rechts, rechtsOben, rechtsUnten};
//		
//		assertArrayEquals(nachbarn, echteNachbarn);
//	}
	
//	@Test
//	public void testeHatNachbar1() {
//		Spielfeld feld = brett.getBrett().get("E4");
//		String nachbar = "F5";
//		
//		boolean hatNachbar = feld.hatNachbar(nachbar);
//		
//		assertTrue(hatNachbar);
//		
//	}
	
//	@Test
//	public void testeHatNachbar2() {
//		Spielfeld feld = brett.getBrett().get("A1");
//		String nachbar = "A0";
//		
//		boolean hatNachbar = feld.hatNachbar(nachbar);
//		
//		assertFalse(hatNachbar);
//	}
	
	@Test
	public void testIsBesetzt() {
//		assertEquals(false, hauptFeld.istBesetzt());
	}
	
	@Test
	public void testeGetFeldSymbol() {
//		String symbol = hauptFeld.getFeldSymbol();
//		
//		assertEquals( "-", symbol);
	}
	
	@Test
	public void testGetBrett() {
//		assertEquals(brett, hauptFeld.getBrett());
	}
	
	@Test
	public void testGetId() {
//		assertEquals("A1", hauptFeld.getId());
	}
	
	@Test
	public void testGetFarbe() {
//		assertNotEquals(FarbEnum.SCHWARZ, hauptFeld.getFarbe());
	}
	
	@Test
	public void testGetFigur() {
//		assertEquals(null, hauptFeld.getFigur());
	}
}
