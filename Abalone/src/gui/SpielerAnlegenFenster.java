package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SpielerAnlegenFenster {

	private JFrame fenster;
	private JLabel schwarz;
	private JLabel weiss;
	private JPanel jp;
//	private JPanel jp2;
	private JRadioButton KI;
	private JTextField tf1;
	private JButton los;
	private Image bild;
	
	public SpielerAnlegenFenster() {
		
		try {
			bild = ImageIO.read(getClass().getResource("./assets/los.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fenster = new JFrame("Abalone");
//		fenster.setLayout(new BorderLayout());
		fenster.setSize(800,500);
		fenster.setLocationRelativeTo(null);
		fenster.setResizable(false);

		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp = new JPanel();
//		jp2 = new JPanel();
		jp.setSize(800, 500);
		jp.setLayout(new GridBagLayout());
//		jp2.setSize(800, 500);
//		jp2.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		schwarz = new JLabel("Schwarz: ");
		c.gridx = 1;
		c.gridy = 0;
		jp.add(schwarz,c);
		
		tf1 = new JTextField(15);
		c.gridx = 2;
		c.gridy = 0;
		jp.add(tf1,c);
		
		KI = new JRadioButton("KI");
		c.gridx = 1;
		c.gridy = 2;
		c.ipady = 55;
		jp.add(KI,c);

		weiss = new JLabel("Weiss:");
		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 0;
		jp.add(weiss,c);
		
		tf1 = new JTextField(15);
		c.gridx = 2;
		c.gridy = 3;
		jp.add(tf1,c);

		KI = new JRadioButton("KI");
		c.gridx = 1;
		c.gridy = 4;
		c.ipady = 55;	
		jp.add(KI,c);
		
		los = new JButton();
		los.setIcon(new ImageIcon(bild));
		los.setPreferredSize(new Dimension(217,60));
		c.gridwidth = 10;
		c.gridx = 1;
		c.gridy = 5;	
		c.gridwidth = 2;
		c.ipady = 0;
		c.insets = new Insets(40,0,0,0);
		jp.add(los,c);
		
		
		fenster.add(jp);
		fenster.setVisible(true);
//		fenster.validate();
	}
	
	
	public static void main(String[] args) {
		SpielerAnlegenFenster fenster = new SpielerAnlegenFenster();
	}
}
