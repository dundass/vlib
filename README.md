# vlib
a mini-library built on Processing to provide a structure for compositing and pixel-level operations for live visuals

consists of a collection of Java Interfaces and one Class that work with PApplet and PGraphics to build a hierarchy of visual objects

# structure
Interfaces:

* VObject - the Interface from which visual objects such as shapes and layers inherit. every VObject can be render()ed

* VShape extends VObject - an Interface that shapes implement before they are drawn to Layers

* VLayer extends VObject - the Interface that defines the behaviour of Layer, including a renderToLayer(VShape[] s, float t) method for drawing shapes and process(PixelProcess[] op) which allows pixel-level manipulations

* PixelProcess - pixel operations (eg fade, distort) can be defined that implement this Interface before being applied to a Layer

Classes:

* Layer extends PGraphics implements VLayer - a generic layer that inherits all of the capabilities of PGraphics (therefore can be used with all of the standard Processing drawing functions) and allows 'VShape's to be rendered onto it & 'PixelProcess'es to be applied

* AnythingYouWant - make your own classes that implement VShape or PixelProcess, put them in your main program, render() them inside draw(), visual magicks

# use
the objective when creating this was to build a formal framework for fitting reuseable shapes and pixel operations onto multiple layers (compositing)

the idea is to create classes that implement the methods specified in both the VShape interface (for each geometric or image-based shape) and PixelProcess (for pixel-level operations that apply the same process to every pixel in a PGraphics)

these classes will then be drawn or applied to the Layer class (or a custom layer that inherits from VLayer, but you'll have to write your own implementation of the process(PixelProcess[] op) method!)

VObjects have a render(float t) method as well as a no-argument render(). this lets you send the current time in milliseconds (or num of frames) to objects eg shapes for time-varying animations. you can also feed in a separate time variable in your main program which allows reversing, skipping, looping etc of animations