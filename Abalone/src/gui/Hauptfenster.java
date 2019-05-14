package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hauptfenster {
	private JFrame mainframe;
	private JPanel mainpanel;
	
	
	public Hauptfenster() {
		mainframe = new JFrame();
		
		mainframe.setSize(1280,1024);
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
		
		
		
	}
	
	
	public static void main(String[] args) {
		Hauptfenster hf = new Hauptfenster();
	}
}
