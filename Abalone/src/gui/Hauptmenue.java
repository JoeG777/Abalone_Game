package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hauptmenue extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3874636578097053073L;
	private static Controller controller;
	private JPanel jp;
	private JLabel jl;
	private JButton neuesSpiel;
	private JButton spielLaden;
	private JButton beenden;
	private Image bild;

	public Hauptmenue (Controller controller){
		Hauptmenue.controller =controller;
		jp = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

//		jl = new JLabel("Abalone", SwingConstants.CENTER);
//		jl.setFont(new Font("Times New Roman", Font.BOLD, 36));
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		ImageIcon iI =  new ImageIcon(getClass().getResource("./assets/burn-in.gif"));
		gbc.weightx = 1.0;
		gbc.ipady = 40;
		gbc.ipadx = 40;
		gbc.insets = new Insets(0,0,0,0);
		jl = new JLabel(iI);
		jp.add(jl, gbc);

		neuesSpiel = new JButton("Neues Spiel");
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.ipady = 30;
		gbc.ipadx = 80;
		gbc.insets = new Insets(20,0,0,0);
		neuesSpiel.addActionListener(this);
		jp.add(neuesSpiel, gbc);

		spielLaden = new JButton ("Spiel laden ");
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 2;
		spielLaden.addActionListener(this);
		jp.add(spielLaden,gbc);

		beenden = new JButton ("Beenden     ");
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 3;
		beenden.addActionListener(this);
		jp.add(beenden, gbc);

		this.add(jp);
		this.setVisible(true);
	}

	/** 
	 * Implementierung der actionPerformed 
	 * @param ActionEvent 
	 */
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

}
