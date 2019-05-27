package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FeldPanel extends JPanel{
	private static final String figurSchwarz="./assets/figurBlau.png";
	private static final String figurSchwarzGewaehlt="./assets/figurBlauGewaehlt.png";
	private static final String figurSchwarzWaehlbar="./assets/figurBlauWaehlbar.png";
	private static final String figurWeiss="./assets/figurRot.png";
	private static final String figurWeissGewaehlt="./assets/figurRotGewaehlt.png";
	private static final String figurWeissWaehlbar="./assets/figurRotWaehlbar.png";
	private static final String figurLeer="./assets/leeresFeld.png";
	private static final String figurLeerGewaehlt="./assets/leeresFeldGewaehlt.png";
	private static final String figurLeerWaehlbar="./assets/leeresFeldWaehlbar.png";
	
	
	
	private static Controller controller;
	private String id;
	private JButton button;
	private String imgStr;
	private String farbe;
	private Color backgroundColor;
	private boolean istAusgewaehlt = false;
	private FarbEnum figurFarbe;
	private boolean auswaehlbar;
	public FeldPanel(String id, Controller c, String[] daten) {
		  this.setSize(24, 24);
		  this.setVisible(true);
		  this.setBackground(Color.DARK_GRAY);
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
		this.backgroundColor=Color.DARK_GRAY;
		if(auswaehlbar) {
			this.backgroundColor=Color.DARK_GRAY;
			
		}
		/*this.subscribedFeld = controller.getSpielfeldMitId(id);
		if(subscribedFeld.istAuswaehlbar())
			subscribedFeld.subscribe(this); */
		Image img = null;
		  try {
			  
			  if(farbe.equals("FIGUR:null")) {
				  if(istAusgewaehlt) {
					  img = ImageIO.read(getClass().getResource(figurLeerGewaehlt));
				  }else if(auswaehlbar){
					  img = ImageIO.read(getClass().getResource(figurLeerWaehlbar));
				  }
				  else {
					  img = ImageIO.read(getClass().getResource(figurLeer));
				  }
				   
				   imgStr = figurLeer;
				   figurFarbe = null;
				 

			  } else if(farbe.equals("FIGUR:weiss")){
				  if(istAusgewaehlt) {
					  img = ImageIO.read(getClass().getResource(figurWeissGewaehlt));
				  }else if(auswaehlbar){
					  img = ImageIO.read(getClass().getResource(figurWeissWaehlbar));
				  }else {
					  img = ImageIO.read(getClass().getResource(figurWeiss));
				  }
				   imgStr = figurWeiss;
				   figurFarbe = FarbEnum.WEISS;

			  }else {
				  if(istAusgewaehlt) {
					  img = ImageIO.read(getClass().getResource(figurSchwarzGewaehlt));
				  }else if(auswaehlbar){
					  img = ImageIO.read(getClass().getResource(figurSchwarzWaehlbar));
				  }else {
					  img = ImageIO.read(getClass().getResource(figurSchwarz));
				  }
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
		  button.setPreferredSize(new Dimension(44,44));
		  this.add(button);
		  button.setEnabled(true);
		  String spielerName = controller.getSpielerAmZug();
		  if(spielerName.substring(0,2).equals("KI")) 
			button.setEnabled(false);
		  this.repaint();
		  this.revalidate();
	}
	
	private JButton createButton(Image img) {
		button = new JButton();
		Color bg = Color.DARK_GRAY;
		button.setBackground(bg);
		button.setBorder(null);
		button.setSize(24, 24);
		button.setIcon(new ImageIcon(img));
		button.setDisabledIcon(new ImageIcon(img));
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
	
	public void toggleAusgewaehlt() {
		istAusgewaehlt = !istAusgewaehlt;
		if(istAusgewaehlt) {
			auswaehlbar = false;
		}
	}
	
	public void resetAusgewaehlt() {
		this.istAusgewaehlt = false;
	}
	
	public Controller getController() {
		return controller;
	}
	
}
