package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LinienPanel extends JPanel{
	int linienZahl;
	private static Controller controller;
	private ArrayList<FeldPanel> panels;
	public LinienPanel(Controller c,int linienZahl,String[][] feldDaten) {
		if(controller == null)
			controller = c;
		panels = new ArrayList<FeldPanel>();
		this.linienZahl = linienZahl;
		FeldPanel p;
		int off =0;
		int max = 9;
		String letter = "";
		switch(linienZahl) {
		case 1: 
			letter = "I";
			off = 4;
			break;
		case 2: 
			letter = "H";
			off = 3;
			break;
		case 3:
			letter = "G";
			off = 2;
			break;
		case 4:
			letter = "F";
			off = 1;
			break;
		case 5:
			letter = "E";
			break;
		case 6:
			letter = "D";
			max = 8;
			break;
		case 7:
			letter = "C";
			max = 7;
			break;
		case 8:
			letter = "B";
			max = 6;
			break;
		case 9:
			max = 5;
			letter = "A";
			break;
		}
		for (int i = 1 + off; i <= max; i++) {
			String[] daten = null;
			for(String[] datenArr : feldDaten) {
				if(datenArr[1].equals(letter + i))
					daten = datenArr;
			}
			p = new FeldPanel(letter + i, controller, daten);
			panels.add(p);
			this.add(p);
		}
		this.setBackground(Color.WHITE);
	}
	
	public void aktualisiere(String[][] daten) {
		ArrayList <String> ids = new ArrayList<String>();
		for(FeldPanel p : panels) {
			for(String[] datenArr : daten) {
				if(datenArr[1].equals(p.getId()))
					p.aktualisiere(datenArr);
			}
		}
	}
	
	public FeldPanel getFeld(String id) {
		for(FeldPanel panel : panels) {
			if(panel.getId().equals(id))
				return panel;
		}
		return null;
	}
	
	public void resetAuswaehlbar() {
		for(FeldPanel f : panels) {
			f.resetAuswaehlbar();
		}
	}

}
