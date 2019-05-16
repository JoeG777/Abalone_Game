package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class LinienPanel extends JPanel{
	int linienZahl;
	private static Controller controller;
	public LinienPanel(Controller c,int linienZahl) {
		if(controller == null)
			controller = c;
		this.linienZahl = linienZahl;
		switch(linienZahl) {
		case 1: 
			this.add((new FeldPanel("I5",controller)));
			this.add((new FeldPanel("I6",controller)));
			this.add((new FeldPanel("I7",controller)));
			this.add((new FeldPanel("I8",controller)));
			this.add((new FeldPanel("I9",controller)));
			break;
		case 2: 
			this.add((new FeldPanel("H4",controller)));
			this.add((new FeldPanel("H5",controller)));
			this.add((new FeldPanel("H6",controller)));
			this.add((new FeldPanel("H7",controller)));
			this.add((new FeldPanel("H8",controller)));
			this.add((new FeldPanel("H9",controller)));
			break;
		case 3:
			this.add((new FeldPanel("G3",controller)));
			this.add((new FeldPanel("G4",controller)));
			this.add((new FeldPanel("G5",controller)));
			this.add((new FeldPanel("G6",controller)));
			this.add((new FeldPanel("G7",controller)));
			this.add((new FeldPanel("G8",controller)));
			this.add((new FeldPanel("G9",controller)));
			break;
		case 4:
			this.add((new FeldPanel("F2",controller)));
			this.add((new FeldPanel("F3",controller)));
			this.add((new FeldPanel("F4",controller)));
			this.add((new FeldPanel("F5",controller)));
			this.add((new FeldPanel("F6",controller)));
			this.add((new FeldPanel("F7",controller)));
			this.add((new FeldPanel("F8",controller)));
			this.add((new FeldPanel("F9",controller)));
			break;
		case 5:
			this.add((new FeldPanel("E1",controller)));
			this.add((new FeldPanel("E2",controller)));
			this.add((new FeldPanel("E3",controller)));
			this.add((new FeldPanel("E4",controller)));
			this.add((new FeldPanel("E5",controller)));
			this.add((new FeldPanel("E6",controller)));
			this.add((new FeldPanel("E7",controller)));
			this.add((new FeldPanel("E8",controller)));
			this.add((new FeldPanel("E9",controller)));
			break;
		case 6:
			this.add((new FeldPanel("D1",controller)));
			this.add((new FeldPanel("D2",controller)));
			this.add((new FeldPanel("D3",controller)));
			this.add((new FeldPanel("D4",controller)));
			this.add((new FeldPanel("D5",controller)));
			this.add((new FeldPanel("D6",controller)));
			this.add((new FeldPanel("D7",controller)));
			this.add((new FeldPanel("D8",controller)));
			break;
		case 7:
			this.add((new FeldPanel("C1",controller)));
			this.add((new FeldPanel("C2",controller)));
			this.add((new FeldPanel("C3",controller)));
			this.add((new FeldPanel("C4",controller)));
			this.add((new FeldPanel("C5",controller)));
			this.add((new FeldPanel("C6",controller)));
			this.add((new FeldPanel("C6",controller)));
			break;
		case 8:
			this.add((new FeldPanel("B1",controller)));
			this.add((new FeldPanel("B2",controller)));
			this.add((new FeldPanel("B3",controller)));
			this.add((new FeldPanel("B4",controller)));
			this.add((new FeldPanel("B5",controller)));
			this.add((new FeldPanel("B6",controller)));
			break;
		case 9:
			this.add((new FeldPanel("A1",controller)));
			this.add((new FeldPanel("A2",controller)));
			this.add((new FeldPanel("A3",controller)));
			this.add((new FeldPanel("A4",controller)));
			this.add((new FeldPanel("A5",controller)));
			break;
		}
		this.setBackground(Color.WHITE);
	}
}
