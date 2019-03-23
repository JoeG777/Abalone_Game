//import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
//import org.junit.Test;
import org.junit.Test;

import abalone.Spielbrett;
import abalone.Spielfeld;
import abalone.Spielzug;

public class SpielbrettTest {

	static Spielbrett spielbrett;
	
	@BeforeClass
	public static void setUp() {
		spielbrett = new Spielbrett();
	}
	
	
	@Test 
	public void getAusgangsfelderTest() {
		Spielzug zug = new Spielzug("C3C4","C4", 0);
		Spielfeld[] felder = spielbrett.getAusgangsfelder(zug);
		Spielfeld feld1 = spielbrett.getFeld("C3");
		Spielfeld feld2 = spielbrett.getFeld("C4");
		Spielfeld feld3 = spielbrett.getFeld("C5");
		
		Spielfeld[] gemeinteFelder= {feld1, feld2};
		
		Assert.assertArrayEquals(felder, gemeinteFelder);
		
	}

}
