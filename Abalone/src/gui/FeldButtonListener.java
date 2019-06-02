package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * Verwaltet die Action-Listener von Feld-Buttons.
 *
 */
public class FeldButtonListener implements ActionListener{

	@Override
	/**
	 * Verwaltet die ActionEvents für Felder. 
	 */
	public void actionPerformed(ActionEvent e) {
		FeldPanel feld = (FeldPanel)((JButton) e.getSource()).getParent();
		Spielzug.toggleString(feld.getController().getSpielerAmZugFarbe(), feld.getId());
		Spielzug.setzeMoeglicheAuswahl();
		//Spielzug.getMoeglicheZuege();
		
	}

}
