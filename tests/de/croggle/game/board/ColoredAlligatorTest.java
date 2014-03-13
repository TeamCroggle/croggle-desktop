package de.croggle.game.board;

import java.util.HashMap;

import junit.framework.TestCase;
import de.croggle.game.Color;

public class ColoredAlligatorTest extends TestCase {
	private ColoredAlligator coloredAlligator;
	private final Color color1 = new Color(0);
	private final Color color2 = new Color(1);

	protected void setUp() {
		coloredAlligator = new ColoredAlligator(false, false, color1, false);
	}

	public void testMatch() {
		assertFalse(coloredAlligator.match(null));
		assertFalse(coloredAlligator.match(new AgedAlligator(false, false)));

		final ColoredAlligator otherAlligator = new ColoredAlligator(false,
				false, color2, false);
		assertFalse(coloredAlligator.match(otherAlligator));

		otherAlligator.setColor(color1);
		assertTrue(coloredAlligator.match(otherAlligator));

		otherAlligator.addChild(new Egg(false, false, color1, false));
		assertFalse(coloredAlligator.match(otherAlligator));
	}

	public void testMatchWithRecoloring() {
		final HashMap<Color, Color> recoloring = new HashMap<Color, Color>();
		final ColoredAlligator otherAlligator = new ColoredAlligator(false,
				false, color2, false);

		assertFalse(coloredAlligator.matchWithRecoloring(null, recoloring));
		recoloring.clear();

		assertFalse(coloredAlligator.matchWithRecoloring(new AgedAlligator(
				false, false), recoloring));
		recoloring.clear();

		recoloring.put(color1, color2);
		assertFalse(coloredAlligator.matchWithRecoloring(otherAlligator,
				recoloring));
		recoloring.clear();

		coloredAlligator.addChild(new Egg(false, false, color1, false));
		otherAlligator.addChild(new Egg(false, false, color2, false));
		assertTrue(coloredAlligator.matchWithRecoloring(otherAlligator,
				recoloring));
		coloredAlligator.clearChildren();
		otherAlligator.clearChildren();
		recoloring.clear();

		otherAlligator.setColor(color1);
		assertTrue(coloredAlligator.matchWithRecoloring(otherAlligator,
				recoloring));
		recoloring.clear();

	}
}
