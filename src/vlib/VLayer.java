package vlib;

// the interface to implement if defining a layer class

public interface VLayer extends VObject {

	public void process(PixelProcess[] op);
	public void imprint(VLayer l);
	public void renderToLayer(VShape[] s, float t);
	default void renderToLayer(VShape s, float t) { renderToLayer(new VShape[]{s}, t); }
	
}
