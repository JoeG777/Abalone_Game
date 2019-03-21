import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import abalone.Spielfeld;
import abalone.Spielfigur;
import abalone.FarbEnum;
import abalone.Spielbrett;

public class SpielfeldTest {
	Spielbrett brett;

	
	@Before
	public void setUp() {
		brett = new Spielbrett();
	}
	
	
	@Test
	
	public void testeSetzeNachbarn1() {
		Spielfeld feld = brett.getBrett().get("A1");
		Spielfeld[] nachbarn = feld.getNachbarn();
		
		Spielfeld rechts = brett.getBrett().get("A2"); 
		Spielfeld rechtsOben = brett.getBrett().get("B2");
		Spielfeld linksOben = brett.getBrett().get("B1");
		
		Spielfeld[] echteNachbarn = {null, linksOben, null,rechts, rechtsOben, null};
		
		assertArrayEquals(echteNachbarn, nachbarn);
		
	}
	@Test
	
	public void testeSetzeNachbarn2() {
		Spielfeld feld = brett.getBrett().get("F5");
		Spielfeld[] nachbarn = feld.getNachbarn();
		
		Spielfeld links = brett.getBrett().get("F4");
		Spielfeld linksOben = brett.getBrett().get("G5");
		Spielfeld linksUnten = brett.getBrett().get("E4");
		Spielfeld rechts = brett.getBrett().get("F6");
		Spielfeld rechtsOben = brett.getBrett().get("G6");
		Spielfeld rechtsUnten = brett.getBrett().get("E5");
		
		Spielfeld[] echteNachbarn = {links, linksOben, linksUnten, rechts, rechtsOben, rechtsUnten};
		
		assertArrayEquals(nachbarn, echteNachbarn);
		
	}
	
	
	@Test 
	public void testeSetzeNachbarn3() {
		Spielfeld feld = brett.getBrett().get("E1");
		Spielfeld[] nachbarn = feld.getNachbarn();
		
		Spielfeld rechts = brett.getBrett().get("E2");
		Spielfeld rechtsOben = brett.getBrett().get("F2");
		Spielfeld rechtsUnten = brett.getBrett().get("D1");
		
		Spielfeld[] echteNachbarn = {null, null, null, rechts, rechtsOben, rechtsUnten};
		
		assertArrayEquals(nachbarn, echteNachbarn);
	}
	
	@Test
	public void testeHatNachbar1() {
		Spielfeld feld = brett.getBrett().get("E4");
		String nachbar = "F5";
		
		boolean hatNachbar = feld.hatNachbar(nachbar);
		
		assertTrue(hatNachbar);
		
	}
	
	@Test
	public void testeHatNachbar2() {
		Spielfeld feld = brett.getBrett().get("A1");
		String nachbar = "A0";
		
		boolean hatNachbar = feld.hatNachbar(nachbar);
		
		assertFalse(hatNachbar);
		
	}
	
	
	@Test
	public void testeGetFeldSymbol() {
		Spielfeld feld = brett.getBrett().get("A1");
		Spielfigur figur = new Spielfigur(feld, FarbEnum.SCHWARZ);
		feld.setFigur(figur);
		
		String symbol = feld.getFeldSymbol();
		
		assertEquals(symbol, "X");
	}
	
	
	
}
