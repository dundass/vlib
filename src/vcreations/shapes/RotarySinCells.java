package vcreations.shapes;

import interpolation.Easing;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import vlib.VShape;

public class RotarySinCells implements VShape {
	final PApplet papp;
	private int num;
	private float cosFactor, cosFactorTar;
	private float[] times;
	private boolean secondRuleOn;

	public RotarySinCells(PApplet p, int _num) {
		papp = p;
		num = _num;
		times = new float[num];
		reset();
	}

	void reset() {
		cosFactor = 1;  cosFactorTar = 1;
		secondRuleOn = false;
		reSeed();
	}

	void incCosFactor() {
		cosFactorTar++;
	}
	void decCosFactor() {
		cosFactorTar--;
	}

	void toggleCosFactor() {
		if(cosFactorTar == 1) cosFactorTar = 200;    //or higher eg 400? or variable?
		else cosFactorTar = 1;
	}

	void enableSecondRule() {
		secondRuleOn = true;
	}
	void disableSecondRule() {
		secondRuleOn = false;
	}

	void reSeed() {
		for(int i = 0; i < num; i++)  times[i] = (float)Math.random();
	}

	//	  void checkLaunch() {
	//	    for(int i = 0; i < 8; i++) {
	//	      for(int j = 0; j < 6; j++) {
	//	        if(launch.getButtonState(i, j)) {
	//	          if(j == 0 && i >= 4) {
	//	            incCosFactor();
	//	          }
	//	          else if(j == 0 && i < 4 && i > 0) {
	//	            decCosFactor();
	//	          }
	//	          else if(j == 0 && i == 0) {
	//	            toggleCosFactor();
	//	            launch.setButtonState(i, j, false);
	//	          }
	//	          else if(j == 1) {
	//	            reSeed();
	//	          }
	//	        }
	//	      }
	//	    }
	//	  }


	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {

		pg.colorMode(PConstants.RGB, 255);
		pg.rectMode(PConstants.CORNER);

		cosFactor = Easing.ease(cosFactor, cosFactorTar, 0.02f);

		for(int i = 0; i < num; i++){
			if(i - 1 >= 0 && i + 1 < num){
				if(Math.sin(times[i - 1]) > 0.5 && Math.sin(times[i + 1]) < 0.3){
					times[i] += Math.sin(times[i - 1]) * Math.sin(times[i + 1]) * 0.05;
				}
				else if(Math.sin(times[i - 1]) < 0 && Math.sin(times[i + 1]) > 0.3 && secondRuleOn){
					times[i] -= Math.sin(times[i - 1]) * Math.sin(times[i + 1]) * 0.05;
				}
			}
			pg.pushMatrix();
			//translate(width / 2 + width / 4 * sin( (float)frameCount / 1000), height / 2 + height / 4 * cos( (float)frameCount / 1000));
			pg.translate(pg.width / 2, pg.height / 2);
			pg.rotate(i * 2 * (float)Math.PI / num + (t / 2000));
			//translate(i * width / num,height / 2);
			//image(mouse, 0, 230 - 200 * sin(times[i]), 2, 2);
			pg.strokeWeight(1.5f);
			pg.stroke((float)Math.sin(times[i]) * 255, 0, 255 - (float)Math.sin(times[i]) * 255, 50);
			if(i + 1 < num)  pg.line(cosFactor * (float)Math.cos(times[i]), 230 - 200 * (float)Math.sin(times[i]), cosFactor * (float)Math.cos(times[i + 1]), 230 - 200 * (float)Math.sin(times[i + 1]));
			pg.popMatrix();
		}
	}
}