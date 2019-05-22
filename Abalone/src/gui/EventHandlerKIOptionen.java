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
		if(e.getSource() == kiPanel.getKi1Weiter()) {
			if(c.getBedienerInterface().getSpielerAmZug().equals("KI_1")) {
				String[] kiZug = {"",""};
				Spielzug.setZug(kiZug);
				try {
					c.ziehe();
				} catch (SpielException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c.aktualisiereAlles();
			}
		}
		if(e.getSource() == kiPanel.getKi1Durchziehend()) {
			if(c.getBedienerInterface().getSpielerAmZug().equals("KI_1")) {
				String[] kiZug = {"DURCHZIEHEN"};
				Spielzug.setZug(kiZug);
				
				try {
					c.ziehe();
				} catch (SpielException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				c.aktualisiereAlles();
				kiPanel.aktiviereDurchziehendKI1();
			}
			
		}
		if(e.getSource() == kiPanel.getKi2Weiter()) {
			if(c.getBedienerInterface().getSpielerAmZug().equals("KI_2")) {
				String[] kiZug = {""};
				Spielzug.setZug(kiZug);
				try {
					c.ziehe();
				} catch (SpielException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		
		if(e.getSource() == kiPanel.getKi2Durchziehend()) {
			if(c.getBedienerInterface().getSpielerAmZug().equals("KI_2")) {
				String[] kiZug = {"DURCHZIEHEN"};
				Spielzug.setZug(kiZug);

					try {
						c.ziehe();
					} catch (SpielException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	

				kiPanel.aktiviereDurchziehendKI2();
				c.aktualisiereAlles();
			}
		}


		
	}

}
