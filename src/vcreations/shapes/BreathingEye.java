package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import vlib.VShape;

public class BreathingEye implements VShape {
	final PApplet papp;
	int c1, c2, c3;
	float rAngle, tAngle;
	
	public BreathingEye(PApplet p) {
		papp = p;
		c1 = 0; c2 = 0; c3 = 0;
	}

	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		float sinValue = 150*(float)Math.sin(t-90)+150;
	    float sinValue2 = 100*(float)Math.sin(t+160)+100;
	    
	    pg.colorMode(PConstants.RGB, 255);
	    pg.pushMatrix();
	    pg.translate(pg.width/2,pg.height/2);
	    pg.noStroke();
	    //fill(255,10);
	    //rect(0,0,1000,1000);
	    pg.rotate(t*50);
	    if(papp.mousePressed) {
	    	pg.fill(255,30,30,50);
	    	pg.ellipse(papp.mouseX,50,100,100);
	    }
	    pg.fill(sinValue,sinValue2,255-sinValue,100);
	    pg.ellipse(sinValue2+20,sinValue,10,sinValue);
	    
	    if(c1 > 0){
	    	pg.fill(300-c1,0,0,100);
	    	pg.ellipse(200-c1,200-c1,100-c1/2,100-c1/2);
	      c1 -= 2;
	    }
	    
	    if(c2 > 0){
	    	pg.fill(40,40,255-c2,100);
	    	pg.ellipse(-c2*2-500,-c2,c2/5,c2*3);
	      c2 -= 1;
	    }
	    
	    if(c3 > 0){
	    	pg.fill(40,255-c3,40,100);
	    	pg.ellipse(-c3-500,0,c3*2,5);
	      c3 -= 1;
	    }
	    
	    if(sinValue > 150){
	    	pg.fill(sinValue,sinValue2,255-sinValue,100);
	    	pg.ellipse(sinValue2+80,sinValue,(sinValue-150)*0.66f,(sinValue-150)*0.66f);
	    	pg.fill(0,1);
	    	pg.rectMode(PConstants.CORNER);
	    	pg.rect(0,0,pg.width,pg.height);
	    }
	    pg.popMatrix();
	    rAngle += t*5;
	    tAngle += t;
	}

}
