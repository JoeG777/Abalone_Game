package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * <h1>FeldButtonListener</h1>
 * Verwaltet die Action-Listener von Feld-Buttons.
 *
 */
public class FeldButtonListener implements ActionListener{

	/**
	 * Verwaltet die ActionEvents fuer Felder. 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		FeldPanel feld = (FeldPanel)((JButton) e.getSource()).getParent();
		Spielzug.toggleString(feld.getController().getSpielerAmZugFarbe(), feld.getId());
		Spielzug.setzeMoeglicheAuswahl();
	}

}
