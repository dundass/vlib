package vlib;

// the interface from which all visible objects inherit

public interface VObject {
	
	default void render() {	render(0); }
	public void render(float t);
	
}
