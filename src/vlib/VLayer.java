package vlib;

public interface VLayer extends VObject {

	public void process(PixelProcess[] op);
	public void imprint(VLayer l);
	public void renderToLayer(VShape[] s, float t);
	
}
