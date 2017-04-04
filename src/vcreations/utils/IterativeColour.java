package vcreations.utils;

public class IterativeColour {
	private int max, base, invertPeriod;
	public float sinFreqDiv, sinAmp;
	
	public IterativeColour(int baseColour, int maxColour) {
		base = baseColour;
		max = maxColour;
		invertPeriod = 3;
		sinFreqDiv = 10;
		sinAmp = 70;
	}
	
	public int get(int i) {
		int nxt = base + (int)(sinAmp * Math.sin((double)i / sinFreqDiv));
		if(nxt > max) nxt %= max;
		else if(nxt < 0) nxt = max + nxt;
		for(int n = 0; n < Math.abs(i) / invertPeriod; n++) nxt = max - nxt;	//do as many inverts as have passed since i=0
		return nxt;
	}
	
	public int getBase() {
		return base;
	}
	
	public void setBase(int b) {
		base = b;
	}
	
	public void setInvertPeriod(int p) {
		invertPeriod = p;
	}
	
	public void setSinFreqDiv(float fdiv) {
		sinFreqDiv = fdiv;
	}
	
	public void setSinAmp(float a) {
		sinAmp = a;
	}
	
}
