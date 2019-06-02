package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Das Brettpanel verwaltet das Spielbrett der GUI.
 *
 */
public class BrettPanel extends JPanel{
	private static Controller controller;
	private ArrayList<LinienPanel> panels;
	public BrettPanel(Controller c, String[][] feldDaten) {
		if(controller == null) {
			controller = c;
		}
		String[][][] feldArray = sortiereFeldDaten(feldDaten);
		setSize(350, 350);
		setLayout(new GridBagLayout());
		panels = new ArrayList<LinienPanel>();
		LinienPanel p1 = new LinienPanel(controller,1,feldArray[0]);
		addToGridBag(p1,1,1,0,0);
		p1 = new LinienPanel(controller,2,feldArray[1]);
		addToGridBag(p1,1,2,0,0);
		p1 = new LinienPanel(controller,3,feldArray[2]);
		addToGridBag(p1,1,3,0,0);
		p1 = new LinienPanel(controller,4,feldArray[3]);
		addToGridBag(p1,1,4,0,0);
		p1 = new LinienPanel(controller,5,feldArray[4]);
		addToGridBag(p1,1,5,0,0);
		p1 = new LinienPanel(controller,6,feldArray[5]);
		addToGridBag(p1,1,6,0,0);
		p1 = new LinienPanel(controller,7,feldArray[6]);
		addToGridBag(p1,1,7,0,0);
		p1 = new LinienPanel(controller,8,feldArray[7]);
		addToGridBag(p1,1,8,0,0);
		p1 = new LinienPanel(controller,9,feldArray[8]);
		addToGridBag(p1,1,9,0,0);
		
		this.setBackground(Color.WHITE);
	}
	
	
	/**
	 * Hilfsmethode für das Hinzufügen von Komponenten zum Hauptpanel.
	 * @param component die Komponente, die hinzugefuegt werden soll. 
	 * @param x Der zu setzende Wert für das 
	 * gridx-Attribut des GridbagConstraints-Objektes.
	 * @param y Der zu setzende Wert für das 
	 * gridy-Attribut des GridbagConstraints-Objektes.
	 * @param xWeight Der zu setzende Wert für das 
	 * weightx-Attribut des GridbagConstraints-Objektes.
	 * @param yWeight Der zu setzende Wert für das 
	 * weighty-Attribut des GridbagConstraints-Objektes.
	 */
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		panels.add((LinienPanel) component);
		this.add(component,c);
	}
	
	/**
	 * Hilfsmethode für das Hinzufügen von Komponenten zum Hauptpanel.
	 * @param component die Komponente, die hinzugefuegt werden soll. 
	 * @param x Der zu setzende Wert für das 
	 * gridx-Attribut des GridbagConstraints-Objektes.
	 * @param y Der zu setzende Wert für das 
	 * gridy-Attribut des GridbagConstraints-Objektes.
	 * @param xWeight Der zu setzende Wert für das 
	 * weightx-Attribut des GridbagConstraints-Objektes.
	 * @param yWeight Der zu setzende Wert für das 
	 * weighty-Attribut des GridbagConstraints-Objektes.
	 * @param width Der zu setzende Wert für die Weite 
	 * des GridbagConstraints-Objektes.
	 */
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight, int width) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		c.gridwidth = width;
	}
	
	/**
	 * Aktualisiert alle Felder mit den übergebenen IDs.
	 * @param ids IDs, die aktualisiert werden sollen.
	 */
	public void aktualisiere(String ids[][]) {
		String[][][] sortiert = this.sortiereFeldDaten(ids);
		for(int i = 0; i < panels.size(); i++) {
			panels.get(i).aktualisiere(sortiert[i]);
		}
		this.validate();
	}
	
	/**
	 * Sortiert die übergebenen Felddaten.
	 * @param feldDaten die Felddaten, die sortiert werden sollen.
	 * @return sortierte Felddaten
	 */
	private String[][][] sortiereFeldDaten(String[][] feldDaten){
		String[][][] sortiert = new String[9][][];
		int anzahlFelder = 0;
		for(int i = 0; i < sortiert.length; i++) {
			switch(i) {
			case 0 :
			case 8 :anzahlFelder = 5;
					break;
			case 1 : 
			case 7 :anzahlFelder = 6;
					break;
			
			case 2 : 
			case 6 : anzahlFelder = 7;
						break;
			
			case 3 :
			case 5: anzahlFelder = 8;
						break;
			
			case 4: anzahlFelder = 9;
					break;
			
			}
			
			sortiert[i] = new String[anzahlFelder][3];
		}
		for(String[] feld : feldDaten) {
			String feldId = feld[1];
			char feldBuchstabe = feldId.charAt(0);
			char feldZahl = feldId.charAt(1);
			int buchstabenIndex = getIndexByBuchstabe(feldBuchstabe);
			int zahl = feldZahl -48;
			int off = 0;
			switch(feldBuchstabe) {
				case 'I': 
						  off = 4;
						  break;
				case 'H': 
						  off = 3;
						  break;
				case 'G': 
						  off = 2;
						  break;
				case 'F':
						  off = 1;
						  break;
			}
			sortiert[buchstabenIndex][zahl-1-off] = feld;
			
		}
		
		return sortiert;
	}
	
	/**
	 * Gibt das Feld mit der übergebenen ID zurück.
	 * @param id
	 * @return
	 */
	public FeldPanel getFeld(String id) {
		return panels.get(getIndexByBuchstabe(id.charAt(0))).getFeld(id);
		
	}
	
	/**
	 * Wandelt einen Buchstabe zum passenden Index des Brettpanels um.
	 * @param feldBuchstabe der Buchstabe, der umgewandelt werden soll
	 * @return der Index des Buchstaben im Brettpanel.
	 */
	private int getIndexByBuchstabe(char feldBuchstabe) {
		int buchstabenIndex = 0;
		switch(feldBuchstabe) {
		case 'I': buchstabenIndex = 0;
				  break;
		case 'H': buchstabenIndex = 1;
				  break;
		case 'G': buchstabenIndex = 2;
				  break;
		case 'F': buchstabenIndex = 3;
				  break;
		case 'E': buchstabenIndex = 4;
				  break;
		case 'D': buchstabenIndex = 5;
		  break;
		case 'C': buchstabenIndex = 6;
		  break;
		case 'B': buchstabenIndex = 7;
		  break;
		case 'A': buchstabenIndex = 8;
		  break;
	}
		return buchstabenIndex;
	}
	
	/**
	 * Bestimmt, ob ein Reset gewählt werden kann. 
	 */
	public void resetAuswaehlbar() {
		for(LinienPanel p : panels) {
			p.resetAuswaehlbar();
		}
	}
	
	/**
	 * 
	 */
	public void resetAusgewaehlt() {
		for(LinienPanel p : panels) {
			p.resetAusgewaehlt();
		}
	}
}

