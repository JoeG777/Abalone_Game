import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abalone.Spiel;
import abalone.SpielException;
import abalone.bedienerInterface;
import abalone.spielbrett.SpielfeldException;

public class bedienerInterfaceTest {

	bedienerInterface bI;
	
	@Before
	public void init() throws SpielfeldException, SpielException {
		bI = new Spiel();
//		Spieler s1 = new Spieler("Johannes", FarbEnum.WEISS);
//		Spieler s2 = new Spieler("Jens", FarbEnum.SCHWARZ);
		bI.addSpieler("Johannes","weiss",2);
		bI.addSpieler("Jens", "schwarz", 2);
	}
	@Test
	public void testGetSpielerAmZug() {
		assertEquals("Johannes", bI.getSpielerAmZug());
	}
	
	@Test
	public void testGetAlleZuege() {
		assertEquals("", bI.getAlleZuege());
	}
	
	@Test
	public void testZiehe() {
		
	}
	
	@Test
	public void getSpielerImSpielInterface() {
		assertEquals("Johannes,Jens,", bI.getSpielerImSpielInterface());
	}
	
	@Test
	public void testGetStatus() {
	
	}

	@Test
	public void testGetErlaubteZuegeInterface() {
		
	}
	
	@Test
	public void testHatGewonnen() {
		assertFalse(bI.hatGewonnen("Johannes"));
	}
}
