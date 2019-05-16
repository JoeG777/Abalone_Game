package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class LogFenster {
	private JFrame frame;
	private String log;
	
	public LogFenster(String log) {
		frame = new JFrame();
		this.setLog(log);
		frame.setSize(720, 480);
		frame.setLayout(new GridLayout(25,1));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.logErstellen(frame, log);
	}
	
	public String getLog() {
		return log;
	}
	
	private void setLog(String log) {
		this.log = log;
	}
	
	private void logErstellen(JFrame frame, String log) {
		String[] zeilen = log.split("\n");
		
		JLabel titel = new JLabel("---LOG---");
		frame.add(titel);
		
		for (String zeile : zeilen) {
			JLabel jl = new JLabel(zeile);
			frame.add(jl);
		}
		
	}

}
