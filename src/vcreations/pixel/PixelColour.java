package vcreations.pixel;

import processing.core.PImage;
import vlib.PixelOperation;

public class PixelColour implements PixelOperation {
	int _r, _g, _b;
	
	public PixelColour() {
		this(10);
	}
	
	public PixelColour(int _grey) {
		this(_grey, _grey, _grey);
	}
	
	public PixelColour(int _r, int _g, int _b) {
		this._r = _r;
		this._g = _g;
		this._b = _b;
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
			in.pixels[i] = makeColor(r + _r, g + _g, b + _b, a);
		}
	}
	
	public void rgb(int _r, int _g, int _b) {
		this._r = _r;
		this._g = _g;
		this._b = _b;
	}

}
