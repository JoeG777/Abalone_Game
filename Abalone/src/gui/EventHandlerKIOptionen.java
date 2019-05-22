package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandlerKIOptionen implements ActionListener{
	private KIOptionenPanel kiPanel;
	
	public EventHandlerKIOptionen(KIOptionenPanel kiPanel) {
		this.kiPanel = kiPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == kiPanel.getKi1Weiter()) {
			
		}
		if(e.getSource() == kiPanel.getKi1Durchziehend()) {
			
		}
		if(e.getSource() == kiPanel.getKi2Weiter()) {
			
		}
		if(e.getSource() == kiPanel.getKi2Durchziehend()) {
			
		}


		
	}

}
