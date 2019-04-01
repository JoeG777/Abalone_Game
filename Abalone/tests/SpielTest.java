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
	
	/*
	@Test
	public void testKoordinatenValidieren() {
		char[][] geparsterZug = {{'G', '5'}, {'G', '6'}};
		assertEquals(true, spiel.koordinatenValidieren(geparsterZug));
	}
	
	public void testSindEigeneFiguren() {
		String id = "A2";
		String id2 = "A3";
		String id3 = "A3";
		String name = "Horst-Johannes";
		FarbEnum farbe = FarbEnum.SCHWARZ;
		Spieler spielerAmZug = new Spieler(name,farbe);
		Spielfigur figur = new Spielfigur(new Spielfeld(new Spielbrett(),id),farbe);
		
		Spielfeld [] spielfelder = {new Spielfeld(brett,id,farbe,figur),
				new Spielfeld(brett,id2,farbe,figur), new Spielfeld(brett, id3,farbe,figur)};
		
		assertTrue(spiel.sindEigeneFiguren(spielfelder, spielerAmZug));
	}
	*/
	
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
	
	@Test
	public void testBekommeRichtung() {
		assertEquals(1, spiel.bekommeRichtung(zug));
	}
	
	@Test
	public void testFormatiere() {
		Spielzug vergleichsZug = new Spielzug("C3C4C5", "D3");
		assertNotEquals(vergleichsZug.getVon(), spiel.formatieren(zug).getVon());
	}
	
	@Test
	public void testZugValidieren() {
		Spielzug[] zuege = {
				new Spielzug("C3", "D3"), 
				new Spielzug("C4", "D4"), 
				new Spielzug("C5", "D5")};
		assertTrue(spiel.zugValidieren(zuege));
	}
}
