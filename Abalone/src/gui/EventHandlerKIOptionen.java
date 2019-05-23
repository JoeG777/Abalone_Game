package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import abalone.SpielException;

public class EventHandlerKIOptionen implements ActionListener{
	private KIOptionenPanel kiPanel;
	private Controller c; 
	
	public EventHandlerKIOptionen(Controller c, KIOptionenPanel kiPanel) {
		this.kiPanel = kiPanel;
		this.c = c; 
	}
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
			Spielzug.setZug(kiZug);
			
			try {
				c.zieheKI(kiZug);
			} catch(SpielException e1) {
				
			}
			c.aktualisiereAlles();

			
		}



		
	}

}
