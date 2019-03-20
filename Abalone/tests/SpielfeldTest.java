import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import abalone.Spielfeld;
import abalone.Spielbrett;

public class SpielfeldTest {
	Spielbrett brett;

	
	@Before
	public void setUp() {
		brett = new Spielbrett();
	}
	
	
	@Test
	
	public void testeSetzeNachbarn() {
		Spielfeld feld = brett.getBrett().get("A1");
		feld.setzeNachbarn();
		Spielfeld[] nachbarn = feld.getNachbarn();
		
		Spielfeld rechts = brett.getBrett().get("A2"); 
		Spielfeld rechtsOben = brett.getBrett().get("B2");
		Spielfeld linksOben = brett.getBrett().get("B1");
		
		Spielfeld[] echteNachbarn = {null, linksOben, null,rechts, rechtsOben, null};
		
		Assert.assertArrayEquals(echteNachbarn, nachbarn);
		
	}
	
	
	
}
