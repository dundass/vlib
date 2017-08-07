package vcreations.pixel;

import processing.core.PImage;
import vlib.PixelProcess;

public class PixelGain implements PixelProcess {
	public float gain;
	public boolean bitwise;
	
	public PixelGain() {
		this(1.0f, true);
	}
	
	public PixelGain(float gain) {
		this(gain, true);
	}
	
	public PixelGain(float gain, boolean bitwise) {
		this.gain = gain;
		this.bitwise = bitwise;
	}

	@Override
	public int apply(PImage in, int x, int y) {
		int p = in.pixels[y * in.width + x], r, g, b, a;
		if(!bitwise) {
			return (int)(p * gain);
		} else {
			a = (p >> 24) & 0xFF;
			r = (p >> 16) & 0xFF;
			g = (p >> 8) & 0xFF;
			b = p & 0xFF;
			p = makeColor((int)(r * gain), (int)(g * gain), (int)(b * gain), a);
		}
		return p;
	}

}
