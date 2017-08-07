package processing_eclipse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import processing.core.PApplet;

public class StreamsTest extends PApplet {
	List<Integer> px, pxx;

	public static void main(String[] args) {
		PApplet.main("processing_eclipse.StreamsTest");
	}
	
	public void settings() {
		size(1280, 800, P2D);
	}
	
	public void setup() {
		background(0);
		px = new ArrayList<Integer>();
		for(int i = 0; i < (width * height); i++) {
			px.add(0);
		}
	}
	
	public void draw() {
		pxx = px.parallelStream().map(x -> x + 255).collect(Collectors.toList());
		for(int i = 0; i < pxx.size(); i++) {
			stroke(pxx.get(i));
			point(i % width, i / width);
		}
		
		surface.setTitle((int)frameRate + "");
	}
}
