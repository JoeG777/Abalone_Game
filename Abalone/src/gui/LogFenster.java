package gui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Diese Klasse erschafft ein Log-Fenster.
 * 
 * @author Gruppe A4
 *
 */
public class LogFenster {
	private JDialog dialog;
	private String log;
	
	/**
	 * Der Konstruktor der Klasse Log-Fenster.
	 * 
	 * @param logObj Log-Objekt
	 */
	public LogFenster() {
		Log logObj = new Log();
		log = logObj.logLesen();
		setLog(log);
		
		dialog = new JDialog();
		dialog.setTitle("Log");
		dialog.setSize(720, 480);
		dialog.setResizable(false);
		dialog.setLayout(new BorderLayout());
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		logAusgeben(dialog, log);
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
		text.setEditable(false);
		text.setLineWrap(true);
		text.setVisible(true);
		text.append("---LOG---");
		text.append(log);
		scrollPane.setSize(720, 480);
		dialog.add(scrollPane);
	}

}
