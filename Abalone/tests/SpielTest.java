import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spiel;
import abalone.Spielbrett;
import abalone.Spieler;
import abalone.Spielfeld;
import abalone.Spielfigur;

public class SpielTest {
	Spiel spiel;
	Spielbrett brett;
	
	@Before
	public void setUp() {
		spiel = new Spiel();
		brett = new Spielbrett();
	
	}
	
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

}
