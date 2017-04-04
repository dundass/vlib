package vcreations.pixel;

import processing.core.PGraphics;
import vlib.PixelProcess;

public class PixelColor implements PixelProcess {
	int r, g, b, a;
	
	public PixelColor(int gy) {	this(gy, gy, gy, 0); }
	
	public PixelColor(int gy, int a) { this(gy, gy, gy, a); }
	
	public PixelColor(int r, int g, int b) { this(r, g, b, 0); }
	
	public PixelColor(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	@Override
	public int apply(PGraphics in, int x, int y) {
		int p, a, r, g, b;
		p = in.pixels[y * in.width + x];
		a = (p >> 24) & 0xFF;
		r = (p >> 16) & 0xFF;
		g = (p >> 8) & 0xFF;
		b = p & 0xFF;
		return makeColor(r + this.r, g + this.g, b + this.b, a + this.a);
	}

}
