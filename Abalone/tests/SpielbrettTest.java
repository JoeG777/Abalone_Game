import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import abalone.Spielbrett;

public class SpielbrettTest {

	static Spielbrett spielbrett;
	
	@BeforeClass
	public static void setUp() {
		spielbrett = new Spielbrett();
	}
	
	@Test
	public void testSpielzugZerleger() {
		String[] uebergabeArray = new String[] {"C3C5", "D4"};
		String[][] rueckgabeArray = new String[][] {{"C3", "C4", "C5"},{"D3", "D4", "D5"}};
		assertArrayEquals(rueckgabeArray,spielbrett.spielzugZerleger(uebergabeArray));
	}

}
