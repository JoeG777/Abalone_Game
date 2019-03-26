package abalone;

public class Historie {

	private String zuege = "";
	private int zaehler = 1;
	
	/**
	 * Konstruktor der Klasse Historie
	 */
	public Historie() {
	}
	
	/**
	 * Diese Methode dient zum Hinzufuegen eines Spielzugs
	 * in die Historie nach Abschluss des Spielzuges
	 * 
	 * @param Spielzug Wird der Historie hinzugefügt
	 */
	public void spielzugHinzufuegen(Spielzug spielzug) {
		zuege = zuege + zaehler + ". " + spielzug.getVon() + " - " 
				+ spielzug.getNach() + "\n";
		zaehler++;
	}
	/**
	 * Gibt einen String mit allen vergangenen Spielzuegen zurueck
	 * @return String mit allen vergangenen Spielzuegen
	 */
	public String getZuege() {
		return zuege;
	}
}
