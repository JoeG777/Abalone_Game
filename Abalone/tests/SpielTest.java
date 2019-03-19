import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import abalone.Spiel;

public class SpielTest {
	Spiel spiel;
	
	@Before
	public void setUp() {
		spiel = new Spiel();
	}
	
	@Test
	public void testKoordinatenValidieren() {
		char[][] geparsterZug = {{'G', '5'}, {'G', '6'}};
		assertEquals(true, spiel.koordinatenValidieren(geparsterZug));
	}

}
