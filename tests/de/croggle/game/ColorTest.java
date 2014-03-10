package de.croggle.game;

import junit.framework.TestCase;

public class ColorTest extends TestCase {
	
	public void testMaxColorsEqualsColorStringLength() {
		assertEquals(Color.MAX_COLORS, Color.getRepresentations().length);
	}
	
	public void testInvalidColor() throws IllegalArgumentException {
		try {
			Color color = new Color(-1);
			fail("expected illegal argument exception");
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	public void testGetter() {
		Color color = new Color(1);
		assertNotNull(color.getRepresentation(color));
	}
	
	public void testequals() {
		Color color1 = new Color(1);
		Color color2 = new Color(2);
		Color color3 = null;
		assertTrue(color1.equals(color1));
		assertFalse(color1.equals(color2));
		assertFalse(color1.equals(color3));
	}
}
