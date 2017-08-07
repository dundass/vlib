package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import vlib.VShape;

public class HaddonCourt implements VShape {
	final PApplet papp;
	int number = 20;
	int ecount = 0;
	int d = 55;
	Particle[] particles = new Particle[number];
	PImage[] imgs = new PImage[6];
	float angle = 200.f;
	float cx,cy;
	int mode = 0;
	boolean lines = false;
	
	class Particle {
		int h, w, imgNum;
		float x,y;
		float incx,incy,incg;
		float g = 1.01f;
		boolean down = true;

		Particle(int tempH,int tempW,int tempX,int tempY) {
			h = tempH;
			w = tempW;
			x = tempX;
			y = tempY;
			incg = 0.5f;
		}

		void update() {
			incx = -1 + (float)(Math.random() * 2);
			incy = -1 + (float)(Math.random() * 2);
			if(mode == 0) {
				x += incx;
				// * dist(x,y,h/2,w/2)/150;
				y += incy;
				// * dist(x,y,h/2,w/2)/150;
				incg = 0.5f;
			}
			else if(mode == 1) {
				x += incx - ((x-cx)/cx)/10;
				y += incy - ((y-cy)/cy)/10;
				incg = 0.5f;
			}
			else if(mode == 2) {
				x += incx - ((x-papp.mouseX)/400)/10;
				y += incy - ((y-papp.mouseY)/400)/10;
				incg = 0.5f;
			}
			else if(mode == 3) {
				if(down){
					incg *= g;
				}
				else if(!down) {
					incg /= g;
				}
				y += incg;
				if(y > h) {
					incg = incg * -1.0f * 0.65f;
					down = !down;
				}
				if(incg > -0.5 && !down) {
					incg = incg * -1.0f;
					down = !down;
				}
			}
			//		    fill(0,0,60);
			//		    noStroke();
			//		    ellipse(x,y,2,2);
			if(x < 0) {
				x = 0;
			}
			if(x > w) {
				x = w;
			}
			if(y < 0) {
				y = 0;
			}
			if(y > h) {
				y = h;
			}
		}
	}
	
	public HaddonCourt(PApplet pa) {
		papp = pa;
		for(int i = 0; i < number; i++) {
			particles[i] = new Particle(papp.width, papp.height, papp.width / 2, papp.height / 2);
			particles[i].imgNum = (int)(Math.random() * imgs.length);
		}
		String[] s = {"dansemacabre1", "dansemacabre2_1", "mariage", "mariage1", "weddingz", "weddingz2"};
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = papp.loadImage(s[i] + ".png");
			imgs[i] = makeTransparent(imgs[i], 235);
		}
	}
	
	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		
//		if(papp.frameCount % 200 == 0) {
//			mode++;
//			if(mode % 2 == 2) {
//				mode++;
//			}
//			mode %= 2;
//		}
		
		  pg.imageMode(PConstants.CENTER);

		  for(int i = 0; i < number; i++) {
			  particles[i].update();
			  for(int j = 0; j < number; j++){
				  if(PApplet.dist(particles[i].x, particles[i].y, particles[j].x, particles[j].y) < d && particles[i] != particles[j]) {
					  if(mode != 3) {
						  particles[i].x -= particles[i].incx;
						  particles[i].y -= particles[i].incy;
						  particles[j].x -= particles[j].incx;
						  particles[j].y -= particles[j].incy;
					  }
				  }
				  else if(PApplet.dist(particles[i].x, particles[i].y, particles[j].x, particles[j].y) > d) {
					  if(lines == true) {
						  pg.stroke(0,5);
						  pg.line(particles[i].x, particles[i].y, particles[j].x, particles[j].y);
					  }
				  }
			  }
			  // !!!!!!!!!!!!!!! image drawing here !!!!!!!!!!!!!!
			  pg.pushMatrix();
			  pg.translate(particles[i].x, particles[i].y);
			  pg.rotate((particles[i].x / (particles[i].y + 1)) -1.8f + (float)Math.random() / 8);
			  pg.image(imgs[particles[i].imgNum], 0, 0, pg.width / 20, pg.height / 7);
			  pg.popMatrix();
		  }
		  cx = pg.width/2 + PApplet.cos(PApplet.radians(angle)) * pg.width/3;
		  cy = pg.height/2 + PApplet.sin(PApplet.radians(angle)) * pg.height/3;

		  //ellipse(cx,cy,10,10);          //debug ellipse
		  angle+=0.05;
	}
	
	PImage makeTransparent(PImage img,float threshold){
	    img.loadPixels();
	    PImage buf = papp.createImage(img.width,img.height,PConstants.ARGB);
	    buf.loadPixels();
	    for(int i = 0;i < img.pixels.length;i++){
	      if(img.pixels[i] > papp.color(threshold)){
	        buf.pixels[i] = papp.color(255,0);
	      }
	      else {
	        buf.pixels[i] = img.pixels[i];
	      }
	    }
	    buf.updatePixels();
	    return buf;
	  }

}
