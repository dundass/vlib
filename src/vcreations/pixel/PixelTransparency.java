package vcreations.pixel;

import processing.core.PImage;
import vlib.PixelOperation;

public class PixelTransparency implements PixelOperation {
	private float thresh;
	
	public PixelTransparency() {
		this(0.9f);
	}
	
	public PixelTransparency(float thresh) {
		this.thresh = thresh;
	}

	@Override
	public void apply(PImage in) {
		int p, a, r, g, b;
		for(int i = 0; i < in.pixels.length; i++) {
			p = in.pixels[i];
			a = (p >> 24) & 0xFF;
			r = (p >> 16) & 0xFF;
			g = (p >> 8) & 0xFF;
			b = p & 0xFF;
			if(r < (255 * thresh) && g < (255 * thresh) && b < (255 * thresh)) in.pixels[i] = makeColor(0, 0, 0, 0);
		}
	}
	
	public void threshold(float amt) {
		thresh = amt;
	}

}
