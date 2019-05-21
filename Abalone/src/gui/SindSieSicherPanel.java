package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abalone.SpielException;

public class SindSieSicherPanel implements ActionListener{
	private JDialog fenster;
	private JPanel jp;
	private JPanel jp2;
	private JLabel label;
	private JButton jaButton;
	private JButton neinButton;
	private EventHandlerHauptfenster eventHandlerMenu; 
	private String abfrage;
	private Controller controller;
	private JFrame mainframe;

	
	public SindSieSicherPanel(String abfrage, Controller c, JFrame mf) {
		this.mainframe = mf;
		this.controller = c;
		this.abfrage = abfrage;
		
		fensterAnlegen();
		
		oberesPanel();
		
		unteresPanel();
	
//		fenster.validate();
		fenster.add(jp, BorderLayout.NORTH);
		fenster.add(jp2);
		
		fenster.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
		fenster.setVisible(true);
	}
	
	public void fensterAnlegen() {
		fenster = new JDialog();
		fenster.setSize(400,250);
		fenster.setLocationRelativeTo(null);
		fenster.setResizable(false);
		fenster.setModal(true);
		fenster.setLayout(new BorderLayout());
		fenster.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public void oberesPanel() {
		jp = new JPanel();
//		jp.setBackground(Color.WHITE);
		jp.setLayout(new GridBagLayout());	
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel(abfrage);
		c.insets = new Insets(30,0,20,0);
		jp.add(label, c);
	}
	
	public void unteresPanel() {
		jp2 = new JPanel();
		jp2.setLayout(new GridBagLayout());	
		jp2.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		
		jaButton = new JButton("JA  ");
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0,20,0,20);
		jp2.add(jaButton,c);
		jaButton.addActionListener(this);
		
		neinButton = new JButton("NEIN");
		jp2.add(neinButton,c);
		neinButton.addActionListener(this);
		
	}
	
	public JButton getJaButton() {
		return jaButton;
	}
	
	public JButton getNeinButton() {
		return neinButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (abfrage.equals("Wollen Sie wirklich das Spiel beenden?")) {
			if (e.getSource() == jaButton) {
				System.exit(0);
			}
			if (e.getSource() == neinButton) {
				fenster.setVisible(false);
				fenster.dispose();
			}
		}
		
		if (abfrage.equals("Wollen Sie wirklich Neu starten?")) {
			if (e.getSource() == jaButton) {
				
				fenster.setVisible(false);
				fenster.dispose();
				
				mainframe.setVisible(false);
				mainframe.dispose();
				
				controller.spielNeuStarten();
				new SpielerAnlegenFenster(controller);
			}
			if (e.getSource() == neinButton) {
				fenster.setVisible(false);
				fenster.dispose();
			}
		}
		
	}
	
}
