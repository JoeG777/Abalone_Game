//import static org.junit.Assert.*;

import org.junit.BeforeClass;
//import org.junit.Test;

import abalone.Spielbrett;

public class SpielbrettTest {

	static Spielbrett spielbrett;
	
	@BeforeClass
	public static void setUp() {
		spielbrett = new Spielbrett();
	}

}
