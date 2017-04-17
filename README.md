# vlib
a mini-library built on Processing to formalise compositing and pixel-level operations for live visuals

consists of a collection of Java Interfaces and one Class that work with PApplet and PGraphics to build a hierarchy of visual objects

# structure
Interfaces from top to bottom:

* VObject - the Interface from which visual objects such as shapes and layers inherit. contains a single method stub render(float t)

* VShape extends VObject - an Interface that shapes implement before they are drawn to Layers

* PixelProcess - pixel operations (eg fade, distort) can be defined that implement this Interface before being applied to a Layer

* VLayer extends VObject - the Interface that defines the behaviour of Layer, including a render(VShape[] s, float t) method for drawing shapes and process(PixelProcess[] p) which allows pixel-level manipulations

class:

* Layer extends PGraphics implements VLayer - a generic layer that inherits all of the capabilities of PGraphics (therefore can be used with all of the standard Processing drawing functions) and allows 'VShape's to be rendered onto it & 'PixelProcess'es to be applied