package gui;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FeldPanel extends JPanel{
	private static final String figurSchwarz="./assets/figurSchwarzG.png";
	private static final String figurWeiss="./assets/figurWeissG.png";
	private static final String figurLeer="./assets/empty.png";
	
	private static Controller controller;
	private String id;
	private JButton button;
	private String imgStr;
	private String farbe;
	private Color backgroundColor;
	private FarbEnum figurFarbe;
	private boolean auswaehlbar;
	public FeldPanel(String id, Controller c, String[] daten) {
		  this.setSize(24, 24);
		  this.setVisible(true);
		  this.setBackground(Color.white);
		this.id = id;
		if(controller == null) {
			controller = c;
		}
		aktualisiere(daten);
	}
	
	public String getId() {
		return this.id;
	}
	public void aktualisiere(String[] daten) {
		this.farbe = daten[2];
		/*if(subscribedFeld != null)
			auswaehlbar = subscribedFeld.istAuswaehlbar(); */
		this.backgroundColor=Color.WHITE;
		if(auswaehlbar) {
			this.backgroundColor=Color.BLUE;
			
		}
		/*this.subscribedFeld = controller.getSpielfeldMitId(id);
		if(subscribedFeld.istAuswaehlbar())
			subscribedFeld.subscribe(this); */
		Image img = null;
		  try {
			  
			  if(farbe.equals("FIGUR:null")) {
				   img = ImageIO.read(getClass().getResource(figurLeer));
				   imgStr = figurLeer;
				   figurFarbe = null;
				 

			  } else if(farbe.equals("FIGUR:weiss")){
				   img = ImageIO.read(getClass().getResource(figurWeiss));
				   imgStr = figurWeiss;
				   figurFarbe = FarbEnum.WEISS;

			  }else {
				  img = ImageIO.read(getClass().getResource(figurSchwarz));
				  imgStr = figurSchwarz;
				  figurFarbe = FarbEnum.SCHWARZ;
			  }
			  
		  } catch (Exception ex) {
		  }
		  if(button != null)
			  this.remove(button);
		  button = createButton(img);
		  FeldButtonListener fbl = new FeldButtonListener();
		  button.addActionListener(fbl);
		  this.add(button);
		  this.repaint();
		  this.revalidate();
	}
	
	private JButton createButton(Image img) {
		button = new JButton();
		Color bg = Color.WHITE;
		if(this.auswaehlbar) {
			bg = Color.BLUE;
		}
		button.setBackground(bg);
		button.setBorder(null);
		button.setSize(24, 24);
		button.setIcon(new ImageIcon(img));
		return button;
	}
	
	public void setAuswaehlbar() {
		this.auswaehlbar = true;
	}
	
	public FarbEnum getFigurFarbe() {
		return this.figurFarbe;
	}
	
	public void resetAuswaehlbar() {
		this.auswaehlbar = false;
	}
	
}
