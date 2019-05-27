package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Fenster um den Benutzer nochmals zu Fragen ob er beenden/neu starten will.
 */
public class SindSieSicherPanel implements ActionListener{
	private JDialog fenster;
	private JPanel jp;
	private JPanel jp2;
	private JLabel label;
	private JButton jaButton;
	private JButton neinButton;
	private String abfrage;
	private Controller controller;
	private JFrame mainframe;
	private Font coalition;
	private Image ja, nein;

	/**
	 * Erzeugt das SindSieSicherPanel zur Benutzerabfrage.
	 * @param abfrage Abfrage welche ausgewaelt wurde (spiel beenden/ spiel neu starten).
	 * @param c Controller, der die Kommunikation zwischen Spiel und GUI
	 * koordniert.
	 * @param mf das JFrame des Hauptfensters, welches nach Sicherheits-Abfrage geschlossen werden kann.
	 */
	public SindSieSicherPanel(String abfrage, Controller c, JFrame mf) {
		this.mainframe = mf;
		this.controller = c;
		this.abfrage = abfrage;
		
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		try {
			ja = ImageIO.read(getClass().getResource("./assets/JABUTTON.png"));
			nein = ImageIO.read(getClass().getResource("./assets/NeinButton.png"));

		} catch (IOException e) {
			new FehlerPanel("Fehler beim Laden der Bilder!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 20);
		
		fensterAnlegen();
		
		oberesPanel();
		
		unteresPanel();
	
		fenster.add(jp, BorderLayout.NORTH);
		fenster.add(jp2);
		
		fenster.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
		fenster.setVisible(true);
	}
	
	/**
	 * legt das Fenster also das JFrame an und erstellt darin ein GridBagLayout.
	 */
	public void fensterAnlegen() {
		fenster = new JDialog();
		fenster.setSize(400,250);
		fenster.setLocationRelativeTo(null);
		fenster.setResizable(false);
		fenster.setModal(true);
		fenster.setLayout(new BorderLayout());
		fenster.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	/**
	 * Erzeugt das obere Panel des Fensters, in dem die Abfrage steht.
	 */
	public void oberesPanel() {
		jp = new JPanel();
		jp.setBackground(Color.DARK_GRAY);
		jp.setLayout(new GridBagLayout());	
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel(abfrage);
		label.setBackground(Color.DARK_GRAY);
		label.setForeground(Color.WHITE);
		label.setFont(coalition);
		c.insets = new Insets(30,0,20,0);
		jp.add(label, c);
	}
	
	/**
	 * Erzeugt das untere Panel des Fensters, in welchem der Ja- und Nein Button ist.
	 */
	public void unteresPanel() {
		jp2 = new JPanel();
		jp2.setLayout(new GridBagLayout());	
		jp2.setBackground(Color.DARK_GRAY);
		GridBagConstraints c = new GridBagConstraints();
		
		jaButton = new JButton();
		jaButton.setPreferredSize(new Dimension(100,50));

		jaButton.setIcon(new ImageIcon(ja));
		c.insets = new Insets(0,20,0,20);
		jp2.add(jaButton,c);
		jaButton.addActionListener(this);
		
		neinButton = new JButton();
		neinButton.setPreferredSize(new Dimension(100,50));
		neinButton.setIcon(new ImageIcon(nein));
		jp2.add(neinButton,c);
		neinButton.addActionListener(this);
		
	}
	
//	public JButton getJaButton() {
//		return jaButton;
//	}
//	
//	public JButton getNeinButton() {
//		return neinButton;
//	}

	/**
	 * Behandelt die Events des SicherheitsPanel.
	 * Also den Ja-Button und den Nein-Button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (abfrage.equals("Spiel beenden?")) {
			if (e.getSource() == jaButton) {
				System.exit(0);
			}
			if (e.getSource() == neinButton) {
				fenster.setVisible(false);
				fenster.dispose();
			}
		}
		
		if (abfrage.equals("Spiel Neustarten?")) {
			if (e.getSource() == jaButton) {
				
				fenster.setVisible(false);
				fenster.dispose();
				
				mainframe.setVisible(false);
				mainframe.dispose();
				
				new SpielerAnlegenFenster(controller);
			}
			if (e.getSource() == neinButton) {
				fenster.setVisible(false);
				fenster.dispose();
			}
		}
		
	}
	
}
