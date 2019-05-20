package gui;

import java.awt.Color;

import javax.swing.JOptionPane;

public class FehlerPanel {
	private JOptionPane fenster;
	private String fehlertext;
	
	public FehlerPanel(String ft) {
		this.fehlertext = ft;
		fenster = new JOptionPane();
		JOptionPane.showMessageDialog(null,
										fehlertext,
										"Warning", 
										JOptionPane.ERROR_MESSAGE);
	}
	
//	public static void main(String[] args) {
//		new FehlerPanel("Du Stinkst");
//	}
}
