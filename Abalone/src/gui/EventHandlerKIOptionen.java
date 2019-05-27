package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import abalone.SpielException;

public class EventHandlerKIOptionen implements ActionListener{
	private KIOptionenPanel kiPanel;
	private Controller c; 
	
	/**
	 * Schafft einen EventHandler für ein KiPanel eines Hauptfensters.
	 * @param c der Controller des Hauptfensters des Ki-Panels.
	 * @param kiPanel das KI-Panel, dessen Events koordiniert werden sollen.
	 */
	public EventHandlerKIOptionen(Controller c, KIOptionenPanel kiPanel) {
		this.kiPanel = kiPanel;
		this.c = c; 
	}
	/**
	 * Behandelt die Events eines KI-Panels.
	 * Diese sind:
	 * KI, die am Zug ist, ziehen lassen.
	 * KI, die am Zug ist, durchziehen lassen.
	 * @param e das zu behandelnde Event.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == kiPanel.getKiWeiter()) {
			String[] kiZug = {"", ""};

			try {
				c.zieheKI(kiZug);
			} catch(SpielException e1) {
				
			}
			
			c.aktualisiereAlles();
		}
		
		if(e.getSource() == kiPanel.getKiDurchziehend()) {
			String[] kiZug = {"DURCHZIEHEN", ""};
			
			try {
				c.zieheKI(kiZug);
			} catch(SpielException e1) {
				
			}
			c.aktualisiereAlles();

			
		}



		
	}

}
