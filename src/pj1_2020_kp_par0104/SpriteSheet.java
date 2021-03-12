package pj1_2020_kp_par0104;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	} 
	public BufferedImage grabImage(int col, int row, int width, int height) {
		return sheet.getSubimage((row*25)-25, (col*25)-25, width, height);
	}
}
