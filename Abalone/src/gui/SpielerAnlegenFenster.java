package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import abalone.SpielException;

/**
 * <h1>SpielerAnlegenFenster</h1>
 * Menue zum Anlegen der Spieler oder KIs.
 * Startet bei korrekten Eingaben das Hauptfenster.
 * 
 */
public class SpielerAnlegenFenster implements ActionListener{

	private JFrame fenster;
	private JLabel head;
	private JLabel schwarz;
	private JLabel weiss;
	private JPanel jp;
	private JRadioButton ki1;
	private JRadioButton ki2;
	private JRadioButton ki1_durchziehen;
	private JRadioButton ki2_durchziehen;
	private JTextField tf1;
	private JTextField tf2;
	private JButton los;
	private Image bild;
	private String name1;
	private String name2;
	private int anzahlSpieler = 0;
	private static Controller controller;
	private Font coalition;
	private ImageIcon ueberschrift;

	/**
	 * Konstruktor, der das SpielerAnlegenFenster mit seinen Komponenten
	 * auf dem Bildschirm anzeigt.
	 * 
	 * @param controller Controller, der die Kommunikation zwischen Spiel und GUI
	 * koordniert
	 */
	public SpielerAnlegenFenster(Controller controller) {
		SpielerAnlegenFenster.controller = controller;
		
		try {
			bild = ImageIO.read(getClass().getResource("/assets/Los Gehts.png"));
		} catch (IOException e) {
			new FehlerPanel("Fehler beim Laden des Bildes!");
		}
		
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 15);
		
		ueberschrift =  new ImageIcon(getClass().getResource("/assets/burn-inSpieleranlegen.gif"));
		
		fenster = new JFrame("Abalone");
		fenster.setUndecorated(true);
		fenster.setSize(800,500);
		fenster.setLocationRelativeTo(null);
		fenster.setResizable(false);
		fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		jp = new JPanel();
		jp.setBackground(Color.DARK_GRAY);
		jp.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		head = new JLabel(ueberschrift);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,20,0);
		jp.add(head,c);

		weiss = new JLabel("Rot:   ");
		weiss.setFont(coalition);
		weiss.setForeground(Color.WHITE);
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		jp.add(weiss,c);

		tf1 = new JTextField(15);
		tf1.setFont(coalition);
		tf1.setBackground(new Color(20,68,170));
		tf1.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 1;
		jp.add(tf1,c);
		
		ki1 = new JRadioButton("KI");
		ki1.setFont(coalition);
		ki1.setForeground(Color.WHITE);
		ki1.setBackground(Color.DARK_GRAY);
		c.gridx = 1;
		c.gridy = 2;
		c.ipady = 25;
		jp.add(ki1,c);
		ki1.addActionListener(this);
		
		ki1_durchziehen = new JRadioButton("durchziehen");
		ki1_durchziehen.setFont(coalition);
		c.gridx = 2;
		c.gridy = 2;
		ki1_durchziehen.setBackground(Color.DARK_GRAY);
		ki1_durchziehen.setForeground(Color.WHITE);
		ki1_durchziehen.setEnabled(false);
		jp.add(ki1_durchziehen, c);
		ki1_durchziehen.addActionListener(this);

		schwarz = new JLabel("Blau: ");
		schwarz.setFont(coalition);
		schwarz.setForeground(Color.WHITE);
		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 0;
		jp.add(schwarz,c);

		tf2 = new JTextField(15);
		tf2.setFont(coalition);
		tf2.setBackground(new Color(20,68,170));
		tf2.setForeground(Color.white);
		c.gridx = 2;
		c.gridy = 3;
		jp.add(tf2,c);

		ki2 = new JRadioButton("KI");
		ki2.setFont(coalition);
		c.gridx = 1;
		c.gridy = 4;
		c.ipady = 25;	
		ki2.setForeground(Color.WHITE);
		ki2.setBackground(Color.DARK_GRAY);
		jp.add(ki2,c);
		ki2.addActionListener(this);
		
		ki2_durchziehen = new JRadioButton("durchziehen");
		ki2_durchziehen.setFont(coalition);
		c.gridx = 2;
		c.gridy = 4;
		ki2_durchziehen.setBackground(Color.DARK_GRAY);	
		ki2_durchziehen.setForeground(Color.WHITE);
		ki2_durchziehen.setEnabled(false);
		jp.add(ki2_durchziehen, c);
		ki2_durchziehen.addActionListener(this);

		los = new JButton();
		los.setIcon(new ImageIcon(bild));
		los.setPreferredSize(new Dimension(227,72));
		c.gridwidth = 10;
		c.gridx = 1;
		c.gridy = 5;	
		c.gridwidth = 2;
		c.ipady = 0;
		c.insets = new Insets(20,0,0,0);
		los.setBackground(Color.DARK_GRAY);
		jp.add(los,c);
		los.addActionListener(this);

		fenster.add(jp);
		fenster.setVisible(true);
	}

	/**
	 * Behandelt die verschiedenen Events des SpielerAnlegenFensters.
	 * Diese sind die auswaehlbaren KI Buttons und durchziehen Buttons und ein 
	 * LOS-Button um das Hauptfenster zu initialisieren.
	 * 
	 * @param e das zu behandelnde Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == los) {
			try {
				if(vorbereiten()) {
					controller.spielerAnlegen(name1,name2,anzahlSpieler);
					if(name1.endsWith("durchziehend") && name1.startsWith("KI")) {						
						String[] zug = {"", ""};
						controller.getBedienerInterface().ziehe(zug);
					}
					fenster.setVisible(false);
					fenster.dispose();
					controller.hauptFensterStarten();
				}
				if(controller.nurDurchziehendeKIs()) {
					controller.getGameFrame().getKiOptionenPanel().getKiDurchziehend().setEnabled(true);
					controller.getGameFrame().getKiOptionenPanel().getKiDurchziehend().doClick();
				}
			} catch (SpielException e1) {
				new FehlerPanel(e1.getMessage());
			}
			
		}

		if (ki1.isSelected()) {
			tf1.setText(null);
			tf1.setBackground(Color.DARK_GRAY);
			tf1.setEnabled(false);
			ki1_durchziehen.setEnabled(true);
		} else {
			tf1.setEnabled(true);
			tf1.setBackground(new Color(20,68,170));
			ki1_durchziehen.setSelected(false);
			ki1_durchziehen.setEnabled(false);
		}
		if (ki2.isSelected()) {
			tf2.setText(null);
			tf2.setBackground(Color.DARK_GRAY);
			tf2.setEnabled(false);
			ki2_durchziehen.setEnabled(true);
		} else {
			tf2.setEnabled(true);
			tf2.setBackground(new Color(20,68,170));
			ki2_durchziehen.setSelected(false);
			ki2_durchziehen.setEnabled(false);
		}
	}
	
	/**
	 * Bearbeitet die Eingaben im SpielerAnlegen-Fenster und gibt zurueck, ob
	 * diese Schritte erfolgreich waren.
	 * 
	 * @return true/false, je nachdem ob Vorbereitung erfolgreich
	 */
	private boolean vorbereiten() {
		if (ki1.isSelected() && ki2.isSelected()) {
			anzahlSpieler = 0;
			if (ki1_durchziehen.isSelected())
				name1 = "KI_1durchziehend";
			else
				name1 = "KI_1";
			if (ki2_durchziehen.isSelected())
				name2 = "KI_2durchziehend";
			else
				name2 = "KI_2";
			
		} else if (ki1.isSelected() || ki2.isSelected()) {
			anzahlSpieler = 1;
			if (ki1.isSelected()) {
				name2 = tf2.getText();
				if(name2.length() > 1 && name2.substring(0,2).equals("KI")) {
					new FehlerPanel("Spielername darf nicht mit \"KI\" beginnen!");
					return false;
				}
				if(ki1_durchziehen.isSelected())
					name1 = "KI_1durchziehend";
				else
					name1 = "KI_1";
			} else {
				name1 = tf1.getText();
				if(name1.length() > 1 && name1.substring(0,2).equals("KI")) {
					new FehlerPanel("Spielername darf nicht mit \"KI\" beginnen!");
					return false;
				}
				if(ki2_durchziehen.isSelected())
					name2 = "KI_2durchziehend";
				else
					name2 = "KI_2";
			}
		} else {
			name1 = tf1.getText();
			if(name1.length() > 1 && name1.substring(0,2).equals("KI")) {
				new FehlerPanel("Spielername darf nicht mit \"KI\" beginnen!");
				return false;
			}
			name2 = tf2.getText();
			if(name2.length() > 1 && name2.substring(0,2).equals("KI")) {
				new FehlerPanel("Spielername darf nicht mit \"KI\" beginnen!");
				return false;
			}
			anzahlSpieler = 2;
		}
		return true;
	}

}
