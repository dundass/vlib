package vcreations.shapes;

import cellularcore.CA2D;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import vlib.VShape;

public class ASCIICastles extends CA2D implements VShape {
	final PApplet papp;
	PFont font;
	int[][] rules = { {0, 0, 4, 0, 2, 0, 0, 0, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 5, 2, 0, 0, 0, 0, 3, 0, 0, 2, 0, 0, 0, 2, 0, 3, 0, 3, 4, 0, 4, 5, 0, 0, 0, 0, 0, 0, 5, 2},
			{0, 0, 0, 0, 4, 0, 0, 0, 2, 4, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 0, 0, 0, 0, 2, 2, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 0, 3, 0, 0, 0, 0, 4, 4},
			{0, 2, 1, 0, 0, 4, 0, 2, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 3, 5, 0, 1, 0, 5, 0, 0, 0, 0, 0, 0, 5, 1, 1, 0, 0, 3, 4, 1, 5, 0, 0, 3, 0}
			};
	
	public ASCIICastles(PApplet p) {
		super(6, 80);
		papp = p;
		font = papp.createFont("Courier New", 30);
		seed();
		setRuleset(rules[0]);
	}
	
	public void seed() {
		int r1 = 1 + (int)(Math.random() * 5);
		int r2 = 1 + (int)(Math.random() * 5);
		for(int x = 20; x < 60; x++) {
			setState(x, 40, r1);
			if(x % 2 == 0) setState(x, 34, r2);
			//if(Math.random() > 0.65) setState(x, 62, r1);
		}
	}

	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		if(getGenCount() < 14 && papp.frameCount % 3 == 0) update();
		if(papp.frameCount % 360 == 0) {
			setRandomStates(0);
			seed();
			setRuleset(rules[(int)(Math.random() * 3)]);
			resetGenCount();
		}
		pg.background(0);
		pg.textFont(font);
		for(int x = 0; x < getXsize(); x++) {
			for(int y = 0; y < getYsize(); y++) {
				char c = ' ';
				switch(getState(x, y)) {
				case 1:
					c = '-';
					break;
				case 2:
					c = '|';
					break;
				case 3:
					c = '|';
					break;
				case 4:
					c = '-';
					break;
				case 5:
					c = '#';
					break;
				}
				pg.fill(200);
				pg.text(c, x * pg.width / getXsize(), y * pg.height / getYsize());
				if(y > 63 && x % 2 == 1 && y % 3 == 0) {
					pg.fill(0, 170, 10);
					pg.text('*', x * pg.width / getXsize(), y * pg.height / getYsize());
				} else if(y < 63 && y > 56 && Math.random() > 0.5) {
					pg.fill(10, 30, 180);
					pg.text('~', x * pg.width / getXsize(), y * pg.height / getYsize());
				}
			}
		}
	}

}
