package vcreations.pixel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import processing.core.PImage;
import vlib.PixelOperation;

public class PixelBrightStreams implements PixelOperation {
	
	@Override
	public void apply(PImage in) {
		/*final List<Integer> l = IntStream.of(in.pixels).boxed().collect(Collectors.toList());
		//System.out.println(l.get(0));
		in.pixels = l.parallelStream().mapToInt(n -> n << 1).toArray();
		//(int)(n * 1.1)*/
		
		int r, g, b, a;
		for(int i = 0; i < in.pixels.length; i++) {
			a = (in.pixels[i] >> 24) & 0xFF;
			r = (in.pixels[i] >> 16) & 0xFF;
			g = (in.pixels[i] >> 8) & 0xFF;
			b = in.pixels[i] & 0xFF;
			r += ((g / 40) + (b / 60));
			g += ((b / 30) + (r / 70));
			b += ((r / 50) + (g / 60));
			in.pixels[i] = makeColor(r, g, b, a);
		}
	}

}
