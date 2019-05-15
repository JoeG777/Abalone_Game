package gui;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FeldPanel extends JPanel{
	private String id;
	private int figur;
	public FeldPanel(String id, int figur) {
		this.id = id;
		this.figur = figur;
		JButton button = new JButton();
		  try {
			  Image img = null;
			  if(figur == 1) {
				   img = ImageIO.read(getClass().getResource("./assets/figurSchwarzG.png"));
				 

			  } else if(figur == 2){
				   img = ImageIO.read(getClass().getResource("./assets/figurWeissG.png"));
				   

			  }else {
				  img = ImageIO.read(getClass().getResource("./assets/empty.png"));
			  }
			  button.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
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
