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
	private JButton button;
	private String imgStr;
	private FarbEnum farbe;
	public FeldPanel(String id, Controller c) {
		  this.setSize(24, 24);
		  this.setVisible(true);
		  this.setBackground(Color.white);
		this.id = id;
		if(controller == null) {
			controller = c;
		}
		aktualisiere();
	}
	
	public void aktualisiere() {
		this.subscribedFeld = controller.getSpielfeldMitId(id);
		subscribedFeld.subscribe(this);
		Image img = null;
		this.farbe = subscribedFeld.getFigurFarbe();
		  try {
			  
			  if(farbe == null) {
				   img = ImageIO.read(getClass().getResource(figurLeer));
				   imgStr = figurLeer;
				 

			  } else if(farbe == FarbEnum.WEISS){
				   img = ImageIO.read(getClass().getResource(figurWeiss));
				   imgStr = figurWeiss;

			  }else {
				  img = ImageIO.read(getClass().getResource(figurSchwarz));
				  imgStr = figurSchwarz;
			  }
			  
		  } catch (Exception ex) {
		  }
		  if(button != null)
			  this.remove(button);
		  button = createButton(img);
		  this.add(button);
		  this.repaint();
		  this.revalidate();
		  System.out.println(this.id + " AKTUALISIERT: " + imgStr + " " + farbe);
	}
	
	private JButton createButton(Image img) {
		button = new JButton();
		Color bg = Color.WHITE;
		button.setBackground(bg);
		button.setBorder(null);
		button.setSize(24, 24);
		button.setIcon(new ImageIcon(img));
		return button;
	}
}
