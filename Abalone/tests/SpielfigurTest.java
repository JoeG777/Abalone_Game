import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spielbrett;
import abalone.Spielfeld;
import abalone.Spielfigur;

public class SpielfigurTest {

	Spielfeld feld;
	Spielfigur figur;
	Spielbrett spielbrett;
	private String id = "A1";
	FarbEnum farbe = FarbEnum.WEISS;
	RuntimeException e = new RuntimeException("Es existiert kein Brett");

	@Before
	public void setSpielfigur() {
		figur = new Spielfigur(new Spielfeld(new Spielbrett(), id), farbe);

	}
	@Test
	public void testGetfarbe() {
		assertEquals(FarbEnum.WEISS, figur.getFarbe());
	}

	@Test
	public void testSetFarbe() {
		figur.setFarbe(FarbEnum.SCHWARZ);
		assertTrue(FarbEnum.SCHWARZ == figur.getFarbe());
	}

}
