package abalone;

public class Historie {

	private String zuege = "";
	
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
	 */
	public void spielzugHinzufuegen(Spielzug spielzug) {
		zuege = zuege + spielzug.getSpielzugVon() + " - " 
				+ spielzug.getSpielzugNach() + "\n";
	}
}
