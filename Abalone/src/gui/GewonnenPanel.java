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


public class GewonnenPanel implements ActionListener{
	private JFrame fenster;
	private JPanel jp;
	private String gewinner;
	private JLabel label;
	private JLabel label2;
	private Image bild;
	private Font coalition;
	private JButton beenden;
	private Controller controller;
	private Hauptfenster mainFrame;
	
	public GewonnenPanel(String name, Controller controller, Hauptfenster mf) {
		this.controller = controller;
		this.gewinner = name;
		this.mainFrame = mf;

		try {
			bild = ImageIO.read(getClass().getResource("./assets/Hauptmenu.png"));
		} catch (IOException e) {
			new FehlerPanel("Fehler beim Laden der Bilder!");
		}
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 60);
		
		fenster = new JFrame("Abalone");
		fenster.setSize(800,500);
		fenster.setResizable(false);
		fenster.setLocationRelativeTo(null);
		fenster.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		jp = new JPanel();
		jp.setBackground(Color.DARK_GRAY);
		jp.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		label2 = new JLabel("Gewinner:");
		label2.setBackground(Color.DARK_GRAY);
		label2.setForeground(Color.WHITE);
		label2.setFont(coalition);
		jp.add(label2,c);
		
		label = new JLabel(gewinner);
		label.setBackground(Color.DARK_GRAY);
		label.setForeground(Color.WHITE);
		label.setFont(coalition);
		c.gridy = 1;
		c.insets = new Insets(60,0,0,0);
		jp.add(label,c);
		
		beenden = new JButton();
		beenden.setIcon(new ImageIcon(bild));
		beenden.setPreferredSize(new Dimension(227,72));
		c.gridy = 2;	
		beenden.setBackground(Color.DARK_GRAY);
		jp.add(beenden,c);
		beenden.addActionListener(this);
		
		fenster.add(jp);
		fenster.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == beenden) {
			mainFrame.shutdown();
			new Hauptmenue(controller);
			fenster.dispose();
		}
	}
}
