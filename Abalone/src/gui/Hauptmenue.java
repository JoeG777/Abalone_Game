package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Hauptmenue extends JPanel{

	private static final long serialVersionUID = -3874636578097053073L;
	private LayoutManager lM;

	public Hauptmenue () {
		JPanel jp = new JPanel(new GridBagLayout());
		//		lM = new BorderLayout(30,30);
		GridBagConstraints gbc = new GridBagConstraints();
		//		jp.setLayout(lM);

		JLabel jl = new JLabel("Abalone", SwingConstants.CENTER);
		jl.setFont(new Font("Times New Roman", Font.BOLD, 36));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.ipady = 40;
		gbc.ipadx = 40;
		gbc.insets = new Insets(70,0,0,0);
		jp.add(jl, gbc);

		JButton neuesSpiel = new JButton("Neues Spiel");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.ipady = 30;
		gbc.ipadx = 80;
		gbc.insets = new Insets(40,0,0,0);
		jp.add(neuesSpiel, gbc);

		JButton spielLaden = new JButton ("Spiel laden");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 2;
		jp.add(spielLaden,gbc);

		JButton beenden = new JButton ("Beenden");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 3;
		jp.add(beenden, gbc);

		this.add(jp);
		this.setVisible(true);
	}

	private void setLayoutManager(LayoutManager lM) {
		if (lM == null)
			throw new RuntimeException("kein LayoutManager uebergeben");
		this.lM = lM;
	}
}
