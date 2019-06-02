package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Diese Klasse erschafft ein Log-Fenster.
 * 
 *
 */
public class LogFenster {
	private JDialog dialog;
	private String log;
	private Font coalition, coalition2;
	
	/**
	 * Der Konstruktor der Klasse Log-Fenster.
	 * 
	 * @param logObj Log-Objekt
	 */
	public LogFenster() {
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 12);
		coalition2 = new Font("Coalition", Font.PLAIN, 15);
		
		BufferedReader br = null;
		String datei = ""; 
		String zeile = ""; 
		try {
			br = new BufferedReader(
				new InputStreamReader(new FileInputStream("log.txt"), "utf-8"));
			while ((zeile = br.readLine()) != null) {
				datei = datei + "\n" + zeile;
			}
			br.close();
		} catch (IOException e) {
			logErstellen();
			datei = "Neue Logdatei erstellt, da keine vorhanden war. Deshalb"
					+ "steht hier noch nichts."; 
		}

				
		setLog(datei);
		
		dialog = new JDialog();
		logAusgeben(dialog, log);
		dialog.setBackground(Color.DARK_GRAY);
		dialog.setForeground(Color.WHITE);
		dialog.setFont(coalition2);
		dialog.setTitle("Log");
		dialog.setSize(740, 480);
		
		dialog.setResizable(false);
		dialog.setLayout(new BorderLayout());
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	


	
	/**
	 * Erstellt eine Log-Datei und loescht die alte.
	 */
	private void logErstellen() {
		File file = new File("log.txt");
		
		if (file.exists())
			file.delete();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			new FehlerPanel("Fehler beim Erstellen der Logdatei!");
		}
	}
	/**
	 * Gibt die Log-Informationen als String zurueck
	 * 
	 * @return String, der die Log-Informationen enthaelt
	 */
	public String getLog() {
		return log;
	}
	
	/**
	 * Setzt den Log.
	 * 
	 * @param log String, der die Log-Informationen enthaelt
	 */
	private void setLog(String log) {
		this.log = log;
	}
	
	/**
	 * Befuellt das uebergebene Fenster mit Labels, anhand eines
	 * uebergebenen Strings.
	 * 
	 * @param Log-Fenster als Jdialog
	 * @param log String, der die Log-Informationen enthaelt
	 */
	private void logAusgeben(JDialog dialog, String log) {
		JTextArea text = new JTextArea(720, 480);
		JScrollPane scrollPane = new JScrollPane(text);
		text.setBackground(Color.DARK_GRAY);
		text.setForeground(Color.WHITE);
		text.setFont(coalition);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setVisible(true);
		text.append("---LOG---");
		text.append(log);
		scrollPane.setSize(720, 480);
		scrollPane.setViewportView(text);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		dialog.add(scrollPane);
	}

}
