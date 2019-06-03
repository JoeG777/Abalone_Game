package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FeldPanel extends JPanel{
	private static final String figurSchwarz="/assets/figurBlau.png";
	private static final String figurSchwarzGewaehlt="/assets/figurBlauGewaehlt.png";
	private static final String figurSchwarzWaehlbar="/assets/figurBlauWaehlbar.png";
	private static final String figurWeiss="/assets/figurRot.png";
	private static final String figurWeissGewaehlt="/assets/figurRotGewaehlt.png";
	private static final String figurWeissWaehlbar="/assets/figurRotWaehlbar.png";
	private static final String figurLeer="/assets/leeresFeld.png";
	private static final String figurLeerGewaehlt="/assets/leeresFeldGewaehlt.png";
	private static final String figurLeerWaehlbar="/assets/leeresFeldWaehlbar.png";
	
	
	
	private static Controller controller;
	private String id;
	private JButton button;
	private String imgStr;
	private String farbe;
	private Color backgroundColor;
	private boolean istAusgewaehlt = false;
	private FarbEnum figurFarbe;
	private boolean auswaehlbar;
	private Image figurSchwarzBild;
	private Image figurSchwarzGewaehltBild;
	private Image figurSchwarzWaehlbarBild;
	private Image figurWeissBild;
	private Image figurWeissGewaehltBild;
	private Image figurWeissWaehlbarBild;
	private Image figurLeerBild;
	private Image figurLeerGewaehltBild;
	private Image figurLeerWaehlbarBild;

	
	public FeldPanel(String id, Controller c, String[] daten) {
		 try {
			figurSchwarzBild = ImageIO.read(getClass().getResource(figurSchwarz));
			figurSchwarzGewaehltBild = ImageIO.read(getClass().getResource(figurSchwarzGewaehlt));
			figurSchwarzWaehlbarBild = ImageIO.read(getClass().getResource(figurSchwarzWaehlbar));
			figurWeissBild = ImageIO.read(getClass().getResource(figurWeiss));
			figurWeissGewaehltBild = ImageIO.read(getClass().getResource(figurWeissGewaehlt));
			figurWeissWaehlbarBild = ImageIO.read(getClass().getResource(figurWeissWaehlbar));
			figurLeerBild = ImageIO.read(getClass().getResource(figurLeer));
			figurLeerGewaehltBild = ImageIO.read(getClass().getResource(figurLeerGewaehlt));
			figurLeerWaehlbarBild = ImageIO.read(getClass().getResource(figurLeerWaehlbar));

			
		} catch (Exception e) {
			  new FehlerPanel("Fehler beim Laden der Bilder!");
		}

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
		 if(farbe.equals("FIGUR:null")) figurFarbe = null;
		 if(farbe.equals("FIGUR:weiss")) figurFarbe = FarbEnum.WEISS;
		 if(farbe.equals("FIGUR:schwarz"))  figurFarbe = FarbEnum.SCHWARZ;
		 
		if(this.auswaehlbar && figurFarbe == controller.getSpielerAmZugFarbe()) {
			this.istAusgewaehlt = true;
		}
		/*this.subscribedFeld = controller.getSpielfeldMitId(id);
		if(subscribedFeld.istAuswaehlbar())
			subscribedFeld.subscribe(this); */
		Image img = null;
		  try {
			  
			  if(figurFarbe == null) {
				  if(istAusgewaehlt) {
					  img = figurLeerGewaehltBild;
				  }else if(auswaehlbar && !istAusgewaehlt){
					  img = figurLeerWaehlbarBild;
				  }
				  else {
					  img = figurLeerBild;
				  }
				   
				  imgStr = figurLeer;
				 

			  } else if(figurFarbe == FarbEnum.WEISS){
				  if(istAusgewaehlt) {
					  img = figurWeissGewaehltBild;
				  }else if(auswaehlbar && !istAusgewaehlt){
					  img = figurWeissWaehlbarBild;
				  }else {
					  img = figurWeissBild;
				  }
				   imgStr = figurWeiss;
				   

			  }else {
				  if(istAusgewaehlt) {
					  img = figurSchwarzGewaehltBild;
				  }else if(auswaehlbar && !istAusgewaehlt){
					  img = figurSchwarzWaehlbarBild;
				  }else {
					  img = figurSchwarzBild;
				  }
				  imgStr = figurSchwarz;
			  }
		  } catch (Exception ex) {
			  new FehlerPanel("Fehler beim Laden der Bilder!");
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
	
	public boolean istAusgewaehlt() {
		return this.istAusgewaehlt;
	}
	
	public void resetAusgewaehlt() {
		this.istAusgewaehlt = false;
	}
	
	public Controller getController() {
		return controller;
	}
	
}
