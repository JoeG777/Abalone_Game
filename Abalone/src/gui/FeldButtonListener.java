package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FeldButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		FeldPanel feld = (FeldPanel)((JButton) e.getSource()).getParent();
		Spielzug.toggleString(Spieler.getSpielerAmZugFarbe(), feld.getId());
		Spielzug.getMoeglicheZuege();
		
	}

}
