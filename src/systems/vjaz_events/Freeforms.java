package systems.vjaz_events;

import chaoscore.OctaveTable;
import processing.core.PApplet;
import time.Timer;
import vcreations.layers.Layer;
import vcreations.pixel.PixelGain;
import vcreations.shapes.Freeform;
import vcreations.shapes.ScanFoldPoints;
import vcreations.utils.IterativeColour;
import vlib.PixelProcess;
import vlib.VShape;

// brief - something that'll just fit the vibe of the night, which is dancy but the room will be dark and I'd like to make it feel immersive and not mental but welcoming visually
// 		   something that will fit with both the live music and deejaying - which will be afrobeat, techno, house, no wave - stuff like that

// idea - population of forms that begin small, numerous, and distributed in style. 
//		  over time, they move (70/30 smooth/sudden) and once they get too close, they interact, with another 70/30 (?) relating to exchange/merging of characteristics
//		  if merge, the two sum their sizes until the final one takes up most of screen (won't be too bright due to thin lines)
//		  if num == 1, fire off a timer to allow it to remain for a few minutes, slowly morphing, until it cuts to black and repeats w diff parameters
//		  use OctaveCurve to morph each shape AND their movement AND some global parameters -> map most movement to the same curve generator

// use PixelGain to alternate between 0.9f and occasionally 1.0f gain to smudge across screen, then chaotically fall to black

public class Freeforms extends PApplet {
	
	Layer layer;
	PixelGain gain;
	Freeform[] forms;
	ScanFoldPoints scan;
	
	OctaveTable curve;
	Timer trailTimer;

	public static void main(String[] args) {
		PApplet.main("systems.vjaz_events.Freeforms");
	}
	
	public void settings() {
		//size(1280, 800, P2D);
		fullScreen(P2D);
	}
	
	public void setup() {
		background(0);
		
		layer = new Layer(this);
		gain = new PixelGain(.9f);
		forms = new Freeform[10];
		for(int i = 0; i < forms.length; i++) forms[i] = new Freeform(this);
		scan = new ScanFoldPoints(this);
		
		curve = new OctaveTable(10, 3);
		trailTimer = new Timer(100000);
	}
	
	public void draw() {
		
		checkDistances();
		
//		if(trailTimer.elapsed()) {
//			if(gain.bitwise) {
//				gain.bitwise = false;
//				trailTimer.setInterval(4000);
//			} else {
//				gain.bitwise = true;
//				trailTimer.setInterval(100000);
//			}
//		}
		
//		layer.process(new PixelProcess[]{gain});
		layer.beginDraw();
		layer.background(0);
		layer.endDraw();
		layer.renderToLayer(scan, System.currentTimeMillis());
		layer.render();
		
		//saveFrame("frame-######.png");
		
		surface.setTitle((int)frameRate + "");
	}
	
	public void checkDistances() {
		float d = 10000;
		for(Freeform f1 : forms) {
			for(Freeform f2 : forms) {
				d = dist(f1.getLoc().x, f1.getLoc().y, f2.getLoc().x, f2.getLoc().y);
				if(d < 20 && f1 != f2) combine(f1, f2);
			}
		}
	}
	
	public void combine(Freeform f1, Freeform f2) {
		//do some stat sharing
		//save them to f1
		//hide f2
		float sz = f1.getSize() + f2.getSize();
		f1.setSize(sz);
		float sym = (f1.getSymmetry() + f2.getSymmetry()) / 2.f;
		f1.setSymmetry(sym);
		f2.setVisible(false);
	}
}
