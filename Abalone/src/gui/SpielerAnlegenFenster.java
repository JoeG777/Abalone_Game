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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import abalone.SpielException;

public class SpielerAnlegenFenster implements ActionListener{

	private JFrame fenster;
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
	

	public SpielerAnlegenFenster(Controller controller) {
		SpielerAnlegenFenster.controller = controller;
		try {
			bild = ImageIO.read(getClass().getResource("./assets/los.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		fenster = new JFrame("Abalone");
		fenster.setSize(800,500);
		fenster.setLocationRelativeTo(null);
		fenster.setResizable(false);

		fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jp = new JPanel();
		jp.setBackground(Color.WHITE);
		jp.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		schwarz = new JLabel("Schwarz: ");
		c.gridx = 1;
		c.gridy = 0;
		jp.add(schwarz,c);

		tf1 = new JTextField(15);
		c.gridx = 2;
		c.gridy = 0;
		jp.add(tf1,c);

		ki1 = new JRadioButton("KI");
		c.gridx = 1;
		c.gridy = 2;
		c.ipady = 55;
		ki1.setBackground(Color.WHITE);
		jp.add(ki1,c);
		ki1.addActionListener(this);
		
		ki1_durchziehen = new JRadioButton("durchziehen");
		c.gridx = 2;
		c.gridy = 2;
		ki1_durchziehen.setBackground(Color.WHITE);
		ki1_durchziehen.setEnabled(false);
		jp.add(ki1_durchziehen, c);
		ki1_durchziehen.addActionListener(this);

		weiss = new JLabel("Weiss:");
		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 0;
		jp.add(weiss,c);

		tf2 = new JTextField(15);
		c.gridx = 2;
		c.gridy = 3;
		jp.add(tf2,c);

		ki2 = new JRadioButton("KI");
		c.gridx = 1;
		c.gridy = 4;
		c.ipady = 55;	
		ki2.setBackground(Color.WHITE);
		jp.add(ki2,c);
		ki2.addActionListener(this);
		
		ki2_durchziehen = new JRadioButton("durchziehen");
		c.gridx = 2;
		c.gridy = 4;
		ki2_durchziehen.setBackground(Color.WHITE);
		ki2_durchziehen.setEnabled(false);
		jp.add(ki2_durchziehen, c);
		ki2_durchziehen.addActionListener(this);


		los = new JButton();
		los.setIcon(new ImageIcon(bild));
		los.setPreferredSize(new Dimension(217,60));
		c.gridwidth = 10;
		c.gridx = 1;
		c.gridy = 5;	
		c.gridwidth = 2;
		c.ipady = 0;
		c.insets = new Insets(40,0,0,0);
		los.setBackground(Color.WHITE);
		jp.add(los,c);
		los.addActionListener(this);

		fenster.add(jp);
		fenster.setVisible(true);
		//		fenster.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == los) {


			try {
				vorbereiten();
				controller.spielerAnlegen(name1,name2,anzahlSpieler);
				fenster.setVisible(false);
				fenster.dispose();
				controller.hauptFensterStarten();
			} catch (SpielException e1) {
				new FehlerPanel(e1.getMessage());

			}
		}

		if (ki1.isSelected()) {
			tf1.setText(null);
			tf1.setEnabled(false);
			ki1_durchziehen.setEnabled(true);
		} else {
			tf1.setEnabled(true);
			ki1_durchziehen.setSelected(false);
			ki1_durchziehen.setEnabled(false);
		}
		if (ki2.isSelected()) {
			tf2.setText(null);
			tf2.setEnabled(false);
			ki2_durchziehen.setEnabled(true);
		} else {
			tf2.setEnabled(true);
			ki2_durchziehen.setSelected(false);
			ki2_durchziehen.setEnabled(false);
		}
	}

	private void vorbereiten() {

		if (ki1.isSelected() && ki2.isSelected()) {
			anzahlSpieler = 0;
			if (ki1_durchziehen.isSelected()) {
				name1 = "KI(durchziehend)";
			} else {
				name1 = "KI";
			}
			if (ki2_durchziehen.isSelected()) {
				name2 = "KI(durchziehend)";
			} else {
				name2 = "KI";
			}

		} else if (ki1.isSelected() || ki2.isSelected()) {
			anzahlSpieler = 1;
			if (ki1.isSelected()) {
				name2 = tf2.getText();
				if(ki1_durchziehen.isSelected()) {
					name1 = "KI(durchziehend)";
				} else {
					name1 = "KI";
				}
			} else {
				name1 = tf1.getText();
				if(ki2_durchziehen.isSelected()) {
					name2 = "KI(durchziehend)";
				} else {
					name2 = "KI";
				}
			}
		} else {
			name1 = tf1.getText();
			name2 = tf2.getText();
			anzahlSpieler = 2;
		}
	}
}
