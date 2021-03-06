package vcreations.layers;

// the default, generic layer class

import processing.core.PApplet;
import processing.opengl.PGraphics2D;
import vlib.PixelProcess;
import vlib.VLayer;
import vlib.VShape;

public class Layer extends PGraphics2D implements VLayer {
	final PApplet papp;
	
	public Layer(PApplet p) {
		papp = p;
		setParent(p);
		setPrimary(false);
		setPath(p.dataPath(""));
		setSize(p.width, p.height);
	}

	@Override
	public void render(float t) {
		papp.image(this, 0, 0);
	}

	@Override
	public void process(PixelProcess[] op) {
		beginDraw();
		loadPixels();
		
		//aggregate the operations to reduce iteration
		
		for(int x = 1; x < this.width - 1; x++) {
			for(int y = 1; y < this.height - 1; y++) {
				for(int i = 0; i < op.length; i++) {	//sequential: process w op[0], then w op[1], etc
					pixels[y * this.width + x] = op[i].apply(this, x, y);
				}
			}
		}
		
		updatePixels();
		endDraw();
	}

	@Override
	public void imprint(VLayer l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderToLayer(VShape[] s, float t) {
		beginDraw();
		for(VShape shape : s) shape.render(this, t);
		endDraw();
	}

}
