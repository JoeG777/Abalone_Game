package abalone;

import java.util.ArrayList;

public class Historie {
	
	private ArrayList<String> zuege;
	private int zaehler;
	
	/**
	 * Konstruktor der Klasse Historie
	 */
	public Historie() {
		setZuege(new ArrayList<String>());
		setZaehler(1);
	}
	
	/**
	 * Gibt das Zuege-Attribut der Historie zurück.
	 * @return ArrayList aus Strings, in der die Zuege stehen.
	 */
	public ArrayList<String> getZuege() {
		return zuege;
	}
	
	/**
	 * Setzt das Zuege Attribut der Historie.
	 * @param zuege ArrayList aus Strings.
	 */
	private void setZuege(ArrayList<String> zuege) {
		this.zuege = zuege;
	}
	
	/**
	 * Gib die Nummer des naechsten Zuges zurueck.
	 * @return aktueller Wert des Zaehler Attributs.
	 */
	private int getZaehler() {
		return this.zaehler;
	}
	
	/**
	 * Setzt das Zaehler Attribut.
	 * @param nummer Eine Nummer die groeßer als 0 sein muss.
	 */
	private void setZaehler(int nummer) {
		if(nummer < 1) {
			throw new RuntimeException("Kann nur von eins zaehlen");
		}
		
		this.zaehler = nummer;
		
	}
	
	/**
	 * Erhoeht den Zaehler um eins.
	 */
	private void incZaehler() {
		zaehler++;
	}
	
	/**
	 * Gibt den Zug an der uebergebenen Stelle aus.
	 * @param nummer Nummer des Zuges in Historie.
	 * @return Zug in Form eines Strings.
	 */
	public String getZug(int nummer) {
		if(nummer > zuege.size() || nummer <= 0) {
			return "Diesen Zug gibt es nicht!";
		}
		return this.zuege.get(nummer-1);
	}
	
	/**
	 * Gibt die Historie als String zurück.
	 */
	@Override
	public String toString()  {
		StringBuilder sb = new StringBuilder();
		
		for(String zug : zuege) {
			sb.append(zug);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Diese Methode dient zum Hinzufuegen eines Spielzugs
	 * in die Historie nach Abschluss des Spielzuges
	 * 
	 * @param Spielzug Wird der Historie hinzugefügt
	 */
	public void spielzugHinzufuegen(Spielzug spielzug) {
		if(spielzug == null) {
			throw new RuntimeException("Spielzug muss existieren!");
		}
		zuege.add(getZaehler() + ". " + spielzug.getVon() + " - " 
				+ spielzug.getNach());
		incZaehler();
	}
	
	
}
