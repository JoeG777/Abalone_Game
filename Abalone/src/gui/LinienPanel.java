package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class LinienPanel extends JPanel{
	int linienZahl;
	
	public LinienPanel(int linienZahl) {
		this.linienZahl = linienZahl;
		switch(linienZahl) {
		case 1: 
			this.add((new FeldPanel("A1",2)));
			this.add((new FeldPanel("A2",2)));
			this.add((new FeldPanel("A3",2)));
			this.add((new FeldPanel("A4",2)));
			this.add((new FeldPanel("A5",2)));
			break;
		case 2: 
			this.add((new FeldPanel("B1",2)));
			this.add((new FeldPanel("B2",2)));
			this.add((new FeldPanel("B3",2)));
			this.add((new FeldPanel("B4",2)));
			this.add((new FeldPanel("B5",2)));
			this.add((new FeldPanel("B6",2)));
			break;
		case 3:
			this.add((new FeldPanel("C1",0)));
			this.add((new FeldPanel("C2",0)));
			this.add((new FeldPanel("C3",2)));
			this.add((new FeldPanel("C4",2)));
			this.add((new FeldPanel("C5",2)));
			this.add((new FeldPanel("C6",0)));
			this.add((new FeldPanel("C7",0)));
			break;
		case 4:
			this.add((new FeldPanel("D1",0)));
			this.add((new FeldPanel("D2",0)));
			this.add((new FeldPanel("D3",0)));
			this.add((new FeldPanel("D4",0)));
			this.add((new FeldPanel("D5",0)));
			this.add((new FeldPanel("D6",0)));
			this.add((new FeldPanel("D7",0)));
			this.add((new FeldPanel("D8",0)));
			break;
		case 5:
			this.add((new FeldPanel("E1",0)));
			this.add((new FeldPanel("E2",0)));
			this.add((new FeldPanel("E3",0)));
			this.add((new FeldPanel("E4",0)));
			this.add((new FeldPanel("E5",0)));
			this.add((new FeldPanel("E6",0)));
			this.add((new FeldPanel("E7",0)));
			this.add((new FeldPanel("E8",0)));
			this.add((new FeldPanel("E9",0)));
			break;
		case 6:
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			break;
		case 7:
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",0)));
			this.add((new FeldPanel("B1",0)));
			break;
		case 8:
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			break;
		case 9:
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			this.add((new FeldPanel("B1",1)));
			break;
		}
		this.setBackground(Color.WHITE);
	}
}
