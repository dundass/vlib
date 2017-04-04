package processing_eclipse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PFont;
import reflection.ClassConsole;
import processing_eclipse.Circles;
import processing_eclipse.Circle;

public class LiveClassAccess extends PApplet {
	private ClassConsole console;
	private PFont font;
	private int bgColour = 60;
	private Circle circle;
	
	public static void main(String[] args) {
		PApplet.main("processing_eclipse.LiveClassAccess");
	}
	
	public void settings() {
		size(1280, 800, P2D);
	}
	
	public void setup() {
		console = new ClassConsole();
		font = createFont("Courier New", 24);
		textFont(font);
		circle = new Circle();
	}
	
	public void draw() {
		background(bgColour);
		//circle.render(this);
		Circles.render(this);
		drawConsole();
		
		surface.setTitle((int)frameRate + "");
	}
	
	public void drawConsole() {
		noStroke();
		fill(0, 70);
		rect(15, 10, console.get().length() * 15, 30);
		fill(255);
		text(console.get(), 20, 30);
	}
	
	public void printThing() {
		System.out.println("thing");
	}
	
	public void printStuff(int n) {
		System.out.println("stuff " + n);
	}
	
	public void keyPressed() {
		if(keyCode == ENTER) {
			console.execute(this);
		} else if(keyCode == BACKSPACE) {
			console.removeChar();
		} else if(keyCode == ALT) {
			console.clear();
		} else if(keyCode == SHIFT) {
			//exclude from addChar()
		} else {
			console.addChar(key);
		}
	}
}
