package gui;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FeldPanel extends JPanel{
	private String id;
	public FeldPanel(String id, int figur) {
		this.id = id;
		JButton button = new JButton();
		  try {
		    Image img = ImageIO.read(getClass().getResource("./assets/figurSchwarzG.png"));
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
	}
}
