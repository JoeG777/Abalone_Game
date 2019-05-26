package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

public class FehlerPanel {
	private JOptionPane fenster;
	private String fehlertext;
	private Font coalition;
	
	public FehlerPanel(String ft) {
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		coalition = new Font("Coalition", Font.PLAIN, 15);
		
//		playSound();
		this.fehlertext = ft;
		fenster = new JOptionPane();
		fenster.setBackground(Color.DARK_GRAY);
		fenster.setForeground(Color.WHITE);
		
		JOptionPane.showMessageDialog(null,
										fehlertext,
										"Warning", 
										JOptionPane.ERROR_MESSAGE);
		
	}

	private void playSound() {
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("abalone/src/gui/assets/Bang.wav"));
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			audioInputStream.read(audio, 0, size);

			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		}catch(Exception e){ e.printStackTrace(); }

	}
}
