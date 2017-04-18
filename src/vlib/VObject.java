package vlib;

public interface VObject {
	
	default void render() {	render(0); }
	public void render(float t);
	
}
