package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import abalone.SpielException;

public class Hauptmenue extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3874636578097053073L;
	private static Controller controller;
	JPanel jp;
	JLabel jl;
	JButton jbl;
	JButton neuesSpiel;
	JButton spielLaden;
	JButton beenden;
	
	//	private LayoutManager lM;

	public Hauptmenue (Controller controller){
		Hauptmenue.controller = controller;
		jp = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		jl = new JLabel("Abalone", SwingConstants.CENTER);
		jl.setFont(new Font("Times New Roman", Font.BOLD, 36));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.ipady = 40;
		gbc.ipadx = 40;
		gbc.insets = new Insets(70,0,0,0);
		jp.add(jl, gbc);

		neuesSpiel = new JButton("Neues Spiel");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.ipady = 30;
		gbc.ipadx = 80;
		gbc.insets = new Insets(40,0,0,0);
		neuesSpiel.addActionListener(this);
		jp.add(neuesSpiel, gbc);

		spielLaden = new JButton ("Spiel laden");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 2;
		spielLaden.addActionListener(this);
		jp.add(spielLaden,gbc);

		beenden = new JButton ("Beenden");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 3;
		beenden.addActionListener(this);
		jp.add(beenden, gbc);

		this.add(jp);
		this.setVisible(true);
	}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == neuesSpiel) {
		SpielerAnlegenFenster saf = new SpielerAnlegenFenster(controller);
		this.dispose();
	}else if(e.getSource() == spielLaden){
		
	}else if(e.getSource() == beenden) {
		System.exit(0);
	}
	
}

//	private void setLayoutManager(LayoutManager lM) {
//		if (lM == null)
//			throw new RuntimeException("kein LayoutManager uebergeben");
//		this.lM = lM;
//	}
}
