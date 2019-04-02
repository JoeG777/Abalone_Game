import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spiel;
import abalone.Spielbrett;
import abalone.Spieler;
import abalone.Spielfeld;
import abalone.Spielfigur;
import abalone.Spielzug;

public class SpielTest {
	Spiel spiel;
	Spielbrett brett;
	Spieler spieler;
	Spielzug zug;
	
	@Before
	public void setUp() {
		spiel = new Spiel();
		brett = new Spielbrett();
		spiel.addSpieler("Jens", "weiss");
		zug = new Spielzug("C3C5", "D5");
	
	}

	@Test
	public void testGetSpielerImSpiel() {
		assertEquals("Jens", spiel.getSpielerImSpiel()[0].getName() );
	}
	
	@Test
	public void testGetSpielerAmZug() {
		assertEquals("Jens", spiel.getSpielerAmZug());
	}
	
	@Test
	public void testHatGewonnen() {
		spiel.addSpieler("Johannes", "schwarz");
		assertEquals(false, spiel.hatGewonnen("Jens"));
	}

}
