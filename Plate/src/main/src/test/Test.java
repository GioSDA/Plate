package main.src.test;

import static org.junit.Assert.*;

import main.src.plate.PlateInterpreter;

public class Test {
	@org.junit.Test
	public void test() {
		PlateInterpreter p = new PlateInterpreter();
		
		////////NUMBER EVAL TESTS////////
		
		Double a1 = (Double) p.eval("ᐁ");
		assertEquals(Double.valueOf(1.0d), a1);
		
		Double a2 = (Double) p.eval("ᑍ");
		assertEquals(Double.valueOf(75.0d), a2);
		
		Double a3 = (Double) p.eval("ᑂᐉ");
		assertEquals(Double.valueOf(6509.0d), a3);
		
		Double a4 = (Double) p.eval("ᐇ᐀");
		assertEquals(Double.valueOf(700.0d), a4);
		
		Double a5 = (Double) p.eval("᐀᐀ᐋ᐀᐀᐀ᐄ");
		assertEquals(Double.valueOf(1000000004.0d), a5);
		
		////////SINGLE FUNCTION TESTS////////
		
		Double b1 = (Double) p.eval("nᐁ");
		assertEquals(Double.valueOf(-1.0d), b1);
		
		Double b2 = (Double) p.eval("+ᑍ,ᑒ");
		assertEquals(Double.valueOf(155.0d), b2);
		
		Double b3 = (Double) p.eval("/ᐐ,ᐃ");
		assertEquals(Double.valueOf(5.0d), b3);
		
		Double b4 = (Double) p.eval("*ᐸ,ᑑ");
		assertEquals(Double.valueOf(4345.0d), b4);
		
		Double b5 = (Double) p.eval("|ᑋ");
		assertEquals(Double.valueOf(73.0d), b5);
		
		////////MULTI FUNCTION TESTS////////
		
		Double c1 = (Double) p.eval("|nᐑ");
		assertEquals(Double.valueOf(16.0d), c1);
		
		Double c2 = (Double) p.eval("/+ᐆ,ᐆ,+ᐁ,ᐁ");
		assertEquals(Double.valueOf(6.0d), c2);
		
		Double c3 = (Double) p.eval("n*+ᐉ,ᐉ,-ᐆ,ᐃ");
		assertEquals(Double.valueOf(-54.0d), c3);
		
		Double c4 = (Double) p.eval("/nᐳ,ᐋ");
		assertEquals(Double.valueOf(-5.0d), c4);
		
		Double c5 = (Double) p.eval("|-ᑖ,ᑥ");
		assertEquals(Double.valueOf(15.0d), c5);
		
	}

}
