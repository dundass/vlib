package vcreations.pixel;

import processing.core.PImage;
import vlib.PixelProcess;

public class PixelTransparency implements PixelProcess {
	private float thresh;
	
	public PixelTransparency() {
		this(0.9f);
	}
	
	public PixelTransparency(float thresh) {
		this.thresh = thresh;
	}
	
	@Override
	public int apply(PImage in, int x, int y) {
		int p, a, r, g, b;
			p = in.pixels[y * in.width + x];
			a = (p >> 24) & 0xFF;
			r = (p >> 16) & 0xFF;
			g = (p >> 8) & 0xFF;
			b = p & 0xFF;
			if(r < (255 * thresh) && g < (255 * thresh) && b < (255 * thresh)) return makeColor(0, 0, 0, 0);
		return in.pixels[y * in.width + x];
	}
	
	public void threshold(float amt) {
		thresh = amt;
	}

}
