package vcreations.shapes;

import java.io.File;

import cellularcore.CA1D;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import vlib.VShape;
import chaoscore.LogisticMap;

public class HaddonTiles extends CA1D implements VShape {
	final PApplet papp;
	int[][] grid;
	PImage[] imgs;
	String[] imgNames;
	LogisticMap log;
	
	public HaddonTiles(PApplet p) {
		super(8, 20);
		papp = p;
		grid = new int[20][15];
		File folder = new File("src/data/haddonbits");
		File[] filelist = folder.listFiles();
		imgs = new PImage[filelist.length];
		imgNames = new String[filelist.length];
		for(int i = 0; i < filelist.length; i++) {
			imgNames[i] = filelist[i].getName();
			imgs[i] = papp.loadImage(imgNames[i]);
		}
		setRandomStates(.9f);
		setLambdaRuleset(.4f);
		log = new LogisticMap(0.4f);
	}

	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		if(getGenCount() < grid[0].length) {
			update();
			grid[getGenCount()] = getStates();
			
		}
		
		if(papp.frameCount % 300 == 0) {
			resetGenCount();
			setRandomStates(.9f);
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid[0].length; j++) {
					grid[i][j] = 0;
				}
			}
		}
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] > 0) pg.image(imgs[(int)(log.getVal() * imgs.length)], j * pg.width / grid.length, pg.height - i * pg.height / grid[0].length,
																						pg.width / grid.length, pg.height / grid[0].length);
				log.update();
			}
		}
	}

}
