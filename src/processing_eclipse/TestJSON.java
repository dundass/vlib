package processing_eclipse;

import processing.core.PApplet;
import processing.data.JSONObject;
import vcreations.pixel.PixelTransparency;
import control.JSONConsole;

public class TestJSON extends PApplet {
	JSONConsole console;
	JSONObject json;
	float float1 = .1f, float2 = .2f;
	String str = "i am a string lol";

	public static void main(String[] args) {
		PApplet.main("processing_eclipse.TestJSON");
	}
	
	public void settings() {
		size(128, 80, P2D);
	}
	
	public void setup() {
		console = new JSONConsole(this);
		console.filename("src/data/test.json");
		Object[] o = {this, new PixelTransparency(0.8f)};
		console.saveFieldsForClasses(o);
		
		
	}
	
	public void draw() {
		
	}
	
	public void keyPressed() {
		if(keyCode == ENTER) {
			//??
		}
		else if(keyCode == BACKSPACE) {
			console.removeChar();
		}
		else if(keyCode == SHIFT) {
			
		}
		else {
			console.addChar(key);
		}
	}
}
