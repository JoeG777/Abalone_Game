import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import abalone.Historie;
import abalone.Spielzug;

public class HistorieTest {

	static Spielzug sz1, sz2, sz3, sz4, sz5;
	static Historie historie1, historie2;
	
	@BeforeClass
	public static void setUp() {
		sz1 = new Spielzug("C3", "D3");
		sz2 = new Spielzug("D3D5", "E3");
		sz3 = new Spielzug("C3D3", "C4");
		sz4 = new Spielzug("C5C3", "D5");
		sz5 = new Spielzug("A1", "A2");
		
		historie1 = new Historie();
		historie2 = new Historie();
	}
	
	@Test
	public void testSpielzugHinzufuegen1() {
		historie1.spielzugHinzufuegen(sz1);
		String h1 = historie1.getZug(1);
		assertEquals("1. C3 - D3", h1);
	}
	
	@Test
	public void testSpielzugHinzufuegen2() {
		historie1.spielzugHinzufuegen(sz2);
		String h2 = historie1.getZug(2);
		assertEquals("2. D3D5 - E3", h2);
	}
	
	@Test
	public void testSpielzugHinzufuegen3() {
		historie1.spielzugHinzufuegen(sz3);
		String h3 = historie1.getZug(3);
		assertEquals("3. C3D3 - C4", h3);
	}
	
	@Test
	public void testSpielzugHinzufuegen4() {
		historie2.spielzugHinzufuegen(sz4);
		historie2.spielzugHinzufuegen(sz4);
		historie2.spielzugHinzufuegen(sz4);
		historie2.spielzugHinzufuegen(sz4);
		historie2.spielzugHinzufuegen(sz4);
		historie2.spielzugHinzufuegen(sz4);
		String h4 = historie2.getZug(6);
		
		assertEquals("6. C5C3 - D5", h4);
	}
	
	@Test (expected = RuntimeException.class)
	public void testSpielzugHinzufuegen5() {
		historie2.spielzugHinzufuegen(null);
	}
	
	@Test 
	public void testGetZug() {
		String actual = historie1.getZug(0);
		String expected = "Diesen Zug gibt es nicht!";
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetZug1() {
		String actual = historie1.getZug(2);
		String expected = "2. D3D5 - E3";
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testGetZug2() {
		String actual = historie1.getZug(4);
		String expected = "Diesen Zug gibt es nicht!";
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testGetZuege() {
		ArrayList<String> actual = historie1.getZuege();
		ArrayList<String> expected = new ArrayList<String>();
		
		expected.add("1. C3 - D3");
		expected.add("2. D3D5 - E3");
		expected.add("3. C3D3 - C4");
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testToString() {
		String h1 = historie1.toString();
		String expected = "1. C3 - D3\n2. D3D5 - E3\n3. C3D3 - C4\n";

		assertEquals(expected, h1);
	}

}
