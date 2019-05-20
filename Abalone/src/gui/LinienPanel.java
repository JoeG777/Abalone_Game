package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LinienPanel extends JPanel{
	int linienZahl;
	private static Controller controller;
	private ArrayList<FeldPanel> panels;
	public LinienPanel(Controller c,int linienZahl) {
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
			p = new FeldPanel(letter + i, controller);
			panels.add(p);
			this.add(p);
		}
		this.setBackground(Color.WHITE);
	}
	
	public void aktualisiere() {
		ArrayList <String> ids = new ArrayList<String>();
		for(FeldPanel p : panels) {
			ids.add(p.getId());
			this.remove(p);
		}
		panels.clear();
		for(String id : ids) {
			FeldPanel p = new FeldPanel(id, controller);
			this.add(p);
			panels.add(p);
		}
		this.validate();
	}
	
	public void aktualisiere(String[] ids) {
		FeldPanel[] alsArray = panels.toArray(new FeldPanel[0]);
		for(FeldPanel p : alsArray) {
			for(String id : ids) {
				if(p.getId().equals(id)) {
					panels.remove(p);
					this.remove(p);
					p = new FeldPanel(id,controller);
					this.add(p);
				}
			}
		}
	}
}
