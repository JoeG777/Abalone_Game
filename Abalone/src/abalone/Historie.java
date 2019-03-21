package abalone;

public class Historie {

	private String zuege = "";
	private int zaehler = 1;
	
	/**
	 * Konstruktor der Klasse Historie
	 * @param zuege String, welcher alle vergangenen Spielzuege enthaelt
	 */
	public Historie(String zuege) {
		this.zuege = zuege;
	}
	
	/**
	 * Diese Methode dient zum Hinzufuegen eines Spielzugs
	 * in die Historie nach Abschluss des Spielzuges
	 * 
	 * @param Spielzug Wird der Historie hinzugefügt
	 */
	public void spielzugHinzufuegen(Spielzug spielzug) {
		zuege = zuege + zaehler + ". " + spielzug.getSpielzugVon() + " - " 
				+ spielzug.getSpielzugNach() + "\n";
		zaehler++;
	}
}
