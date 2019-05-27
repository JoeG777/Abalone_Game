package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

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
				new FehlerPanel("Ziehen der KI fehlgeschlagen!");
				return;
			}
			
			c.aktualisiereAlles();
		}
		
		if(e.getSource() == kiPanel.getKiDurchziehend()) {
			String[] kiZug = {"DURCHZIEHEN", ""};
			
			try {
				c.zieheKI(kiZug);
			} catch(SpielException e1) {
				new FehlerPanel("Ziehen der KI fehlgeschlagen!");
				return;
			}
			c.aktualisiereAlles();
			
			if(c.nurDurchziehendeKIs()) {
				startKIvsKI(e, kiZug);
			}	
	}
	}
	
	private void startKIvsKI(ActionEvent event, String[] kiZug) {
		if(c.getBedienerInterface().hatGewonnen(c.getSpielerAmZug())) {
			return;
		}
		else {
			try {
				c.getBedienerInterface().ziehe(kiZug);
			} catch (SpielException e) {
				new FehlerPanel("KI-Ziehen fehlgeschlagen!");
				return;
			}
			c.aktualisiereAlles();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				new FehlerPanel("Verzögerung der KI fehlgeschlagen!");
				return;
			}
		
		actionPerformed(event);
		
	}
	}
//	private void startKIvsKI(String[] kiZug) {
//		while(!c.getBedienerInterface().hatGewonnen(c.getSpielerAmZug())) {
//			try {
//				c.getBedienerInterface().ziehe(kiZug);
//			} catch (SpielException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			c.aktualisiereAlles();
//			
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						System.out.println("angekommen");
//					}
//				}
//			}).start();
//			
//			
//		}
//	}
//	private void startKIvsKI(String[] kiZug) {
//		while(!c.getBedienerInterface().hatGewonnen(c.getSpielerAmZug())) {
//		SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
//
//			@Override
//			protected Boolean doInBackground() throws Exception {
//				c.getBedienerInterface().ziehe(kiZug);
//				return true;
//			}
//
//			@Override
//			protected void done() {
//				c.aktualisiereAlles();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		};
//		worker.execute();
//		
//		while(!worker.isDone()) {
//			
//		}
//	}
//	}

}
