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

public class SpielerAnlegenFenster implements ActionListener{

	private JFrame fenster;
	private JLabel schwarz;
	private JLabel weiss;
	private JPanel jp;
	//	private JPanel jp2;
	private JRadioButton ki1;
	private JRadioButton ki2;
	private JTextField tf1;
	private JTextField tf2;
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

		fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		ki1 = new JRadioButton("KI");
		c.gridx = 1;
		c.gridy = 2;
		c.ipady = 55;
		jp.add(ki1,c);
		ki1.addActionListener(this);



		weiss = new JLabel("Weiss:");
		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 0;
		jp.add(weiss,c);

		tf2 = new JTextField(15);
		c.gridx = 2;
		c.gridy = 3;
		jp.add(tf2,c);

		ki2 = new JRadioButton("KI2");
		c.gridx = 1;
		c.gridy = 4;
		c.ipady = 55;	
		jp.add(ki2,c);
		ki2.addActionListener(this);


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
		los.addActionListener(this);



		fenster.add(jp);
		fenster.setVisible(true);
		//		fenster.validate();
	}


	public static void main(String[] args) {
		SpielerAnlegenFenster fenster = new SpielerAnlegenFenster();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == los) {
			
				if (eingabenRichtig()) {
					fenster.setVisible(false);
					fenster.dispose();
				}
		}

		if (ki1.isSelected()) {
			tf1.setText(null);
			tf1.setEnabled(false);
		} else {
			tf1.setEnabled(true);
		}
		if (ki2.isSelected()) {
			tf2.setText(null);
			tf2.setEnabled(false);
		} else {
			tf2.setEnabled(true);

		}
	}

	private boolean eingabenRichtig() {

		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()~%!-]");

		if (ki1.isSelected() && ki2.isSelected()) {
			return true;
		}
		
		if (ki1.isSelected()) {
			if (tf2.getText().length() < 2 || tf2.getText().length() > 20) {
				return false;
			} else if (regex.matcher(tf2.getText()).find()) {
				return false;
			} else {
				return true;
			}
		}
		
		if (ki2.isSelected()) {
			if (tf1.getText().length() < 2 || tf1.getText().length() > 20) {
				return false;
			} else if (regex.matcher(tf1.getText()).find()) {
				return false;
			} else {
				return true;
			}
		}
		
		if (tf1.getText().length() >= 2 && tf1.getText().length() <= 20 && tf2.getText().length() >= 2 &&
				tf2.getText().length() <= 20 && !tf1.getText().equals(tf2.getText()) && 
				!regex.matcher(tf1.getText()).find() && !regex.matcher(tf2.getText()).find()) {
			return true;
		}
		return false;
	}
}
