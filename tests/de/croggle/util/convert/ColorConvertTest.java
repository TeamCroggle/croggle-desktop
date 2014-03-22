package de.croggle.util.convert;

import com.badlogic.gdx.graphics.Color;

import junit.framework.TestCase;

public class ColorConvertTest extends TestCase {
	
	public void testColorFromHex() {
		com.badlogic.gdx.graphics.Color color1 = new Color();
		com.badlogic.gdx.graphics.Color color2 = ColorConvert.fromHexString("0x00000000");
		assertTrue(color1.equals(color2));
		com.badlogic.gdx.graphics.Color color3 = new Color(0, 0, 0, 255f);
		com.badlogic.gdx.graphics.Color color4 = ColorConvert.fromHexString("000000");
		assertTrue(color3.equals(color4));
		try {
			com.badlogic.gdx.graphics.Color color5 = ColorConvert.fromHexString("this is the wrong length");
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		
	}

}
