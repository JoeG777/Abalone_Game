package gui;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FeldPanel extends JPanel{
	private static final String figurSchwarz="./assets/figurSchwarzG.png";
	private static final String figurWeiss="./assets/figurWeissG.png";
	private static final String figurLeer="./assets/empty.png";
	
	private static Controller controller;
	private String id;
	private Spielfeld subscribedFeld;
	public FeldPanel(String id, Controller c) {
		this.id = id;
		if(controller == null) {
			controller = c;
		}
		this.subscribedFeld = controller.getSpielfeldMitId(id);
		subscribedFeld.subscribe(this);
		aktualisiere();
	}
	
	public void aktualisiere() {
		JButton button = new JButton();
		  try {
			  Image img = null;
			  if(subscribedFeld.getFigurFarbe() == null) {
				   img = ImageIO.read(getClass().getResource(figurLeer));
				 

			  } else if(subscribedFeld.getFigurFarbe() == FarbEnum.WEISS){
				   img = ImageIO.read(getClass().getResource(figurWeiss));
				   

			  }else {
				  img = ImageIO.read(getClass().getResource(figurSchwarz));
			  }
			  button.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		  }
		  Color bg = Color.WHITE;
		  button.setBackground(bg);
		  button.setBorder(null);
		  button.setSize(24, 24);
		  this.add(button);
		  this.setSize(24, 24);
		  this.setVisible(true);
		  this.setBackground(Color.white);
		
	}
}
