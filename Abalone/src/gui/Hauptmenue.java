package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hauptmenue extends JPanel{

	private static final long serialVersionUID = -3874636578097053073L;
	private LayoutManager lM;
	
	public Hauptmenue () {
		JPanel jp = new JPanel();
		lM = new BorderLayout(30,30);
		jp.setLayout(lM);
		
		Component jl = new JLabel("Abalone");
		jp.add(jl, BorderLayout.PAGE_START);
		
		JButton neuesSpiel = new JButton("Neues Spiel");
		jp.add(neuesSpiel, BorderLayout.CENTER);
		
		JButton spielLaden = new JButton ("Spiel laden");
		jp.add(spielLaden, BorderLayout.CENTER);
		
		JButton beenden = new JButton ("Beenden");
		jp.add(beenden, BorderLayout.CENTER);
		
		this.add(jp);
		this.setVisible(true);
	}
	
	private void setLayoutManager(LayoutManager lM) {
		if (lM == null)
			throw new RuntimeException("kein LayoutManager uebergeben");
		this.lM = lM;
	}
}
