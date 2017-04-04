package vcreations.pixel;

import processing.core.PImage;
import vlib.PixelOperation;

public class PixelDirectionalGrowth implements PixelOperation {
	private float[] directions = new float[6];
	private float[][] presets = {
			{0.99f, 0.99f, 1.f, 1.01f, 1.01f, 1.f},
			{1.0f, 1.01f, 1.0f, 1.01f, 0.98f, 1.01f},
			{1.02f, 1.02f, 1.03f, 1.01f, 0.98f, 1.01f}
	};
	
	public PixelDirectionalGrowth() {
		for(int i = 0; i < directions.length; i++) directions[i] = (float)(.9 + Math.random() / 5.);
	}
	
	public PixelDirectionalGrowth(int preset) {
		directions = presets[preset];
	}
	
	@Override
	public void apply(PImage in) {
		int p, a, r, g, b;
		for(int x = 1; x < in.width - 1; x++) {
			for(int y = 1; y < in.height - 1; y++) {
				p = in.pixels[y * in.width + x];
				a = (p >> 24) & 0xFF;
				r = (p >> 16) & 0xFF;
				g = (p >> 8) & 0xFF;
				b = p & 0xFF;
				if(r > 0) {
					in.pixels[(y-1) * in.width + x] = (int)((float)p * directions[0]);
					in.pixels[(y+1) * in.width + x] = (int)((float)p * directions[1]);
					//bitwise shit here to preserve alpha? or at least, scale it with colour (so when black, pix is invisible)
				}
				if(g > 0) {
					in.pixels[y * in.width + (x-1)] = (int)((float)p * directions[2]);
					in.pixels[y * in.width + (x+1)] = (int)((float)p * directions[3]);
				}
				if(b > 0) {
					in.pixels[(y-1) * in.width + (x-1)] = (int)((float)p * directions[4]);
					in.pixels[(y+1) * in.width + (x+1)] = (int)((float)p * directions[5]);
				}
			}
		}
	}
	
	public void setPreset(int p) {
		directions = presets[p];
	}
	
	public void incDirection(int d, float amt) {
		directions[d] += amt;
	}

}
