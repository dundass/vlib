package control;

import interfaces.KeyButton;
import interfaces.KeyIncrementer;
import processing.core.PConstants;

// main keyboard acts as normal (to siphon chars to reflection console),
// holding ctrl accesses numerical pickers (1-9, for vij shapes/noises), 9 sliders (Q-O + A-L), and 7 toggles (Z-M)

// route() method to be filled in by subclasses to route buttons/sliders to visuals
// overriding pipe() can allow standard keyboard use to be piped to any destination -> default is ClassConsole

public class KeysVisualMap implements PConstants {
	private boolean ctrl = false;
	private KeyButton[] buttons;
	private KeyIncrementer[] sliders;
	
	public KeysVisualMap() {
		char[] butts = {'z', 'x', 'c', 'v', 'b', 'n', 'm'};
		char[][] slides = { {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o'},
							{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'} };
		buttons = new KeyButton[butts.length];
		sliders = new KeyIncrementer[slides[0].length];
		for(int i = 0; i < buttons.length; i++) buttons[i] = new KeyButton(butts[i]);
		for(int i = 0; i < sliders.length; i++) sliders[i] = new KeyIncrementer(slides[0][i], slides[1][i]);
	}
	
	public void pressed(char c) {
		if((int)c == 65535) ctrl = true;
		if(ctrl) {
			for(KeyButton b : buttons) b.push(c);
			for(KeyIncrementer inc : sliders) inc.push(c);
		}
		else {
			//class console -> ?
		}
	}
	
	public void released(char c) {
		if((int)c == 65535) ctrl = false;
		for(KeyButton b : buttons) b.release();
		for(KeyIncrementer inc : sliders) inc.release();
	}
	
	//public abstract void route();
	
	public char pipe(char c) {
		return c;
	}
	
	public KeyButton getButton(char c) {
		for(KeyButton b : buttons) {
			if(b.key == c) return b;
		}
		return new KeyButton('.');
	}
	
	public KeyIncrementer getSlider(char c) {
		for(KeyIncrementer i : sliders) {
			if(i.up == c || i.down == c) return i;
		}
		return new KeyIncrementer('.', ',');
	}
}
