import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abalone.FarbEnum;
import abalone.Spielzug;

public class SpielzugTest {
	
	Spielzug zug;
	Spielzug einfacherZug;
	
	@Before
	public void setUp() {
		zug = new Spielzug("B1", "C1", 1, FarbEnum.SCHWARZ);
		einfacherZug = new Spielzug ("B1", "C1");
	}
	
	@Test
	public void testGetVon() {
		assertEquals("B1", zug.getVon());
	}
	
	@Test
	public void testGetNach() {
		assertEquals("C1", zug.getNach());
	}
	
	@Test
	public void testGetRichtung() {
		assertTrue(1 == zug.getRichtung());
	}
	
	@Test
	public void testGetRichtungVonEinfachemSpielzug() {
		assertEquals(0 ,einfacherZug.getRichtung());
	}
	
	@Test
	public void testGetFarbeVonEinfachemSpielzug() {
		assertEquals(null, einfacherZug.getFarbe());
	}
	@Test
	public void testGetFarbe() {
		assertNotEquals(FarbEnum.WEISS, zug.getFarbe());
	}
	
	@Test
	public void testHauptKonstruktor() {
		assertTrue(zug.equals(new Spielzug("B1", "C1", 1, FarbEnum.SCHWARZ)));
	}
	
	@Test
	public void testNebenKonstruktor() {
		assertTrue(einfacherZug.equals(new Spielzug("B1", "C1")));
	}
	
	@Test
	public void testEqualsEinfacherZug() {
		Spielzug vergleichsZugEinfach = new Spielzug("B1", "C1");
		assertTrue(einfacherZug.equals(vergleichsZugEinfach));
	}
	
	@Test
	public void testEqualsZug() {
		Spielzug vergleichsZug = new Spielzug("B1", "C1", 1, FarbEnum.SCHWARZ);
		assertTrue(zug.equals(vergleichsZug));
	}

}
