package de.croggle.game.board.operations;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

public class BoundednessTest extends TestCase {
	public void testInitialization() {
		new Boundedness();
	}

	public void testBound() {
		final ColoredAlligator coloredAlligator = new ColoredAlligator(false,
				false, new Color(0), false);
		final Egg egg = new Egg(false, false, new Color(0), false);
		coloredAlligator.addChild(egg);
		assertTrue(Boundedness.isBound(egg));
		assertFalse(Boundedness.isFree(egg));
	}

	public void testFree() {
		final ColoredAlligator coloredAlligator = new ColoredAlligator(false,
				false, new Color(0), false);
		final Egg egg = new Egg(false, false, new Color(1), false);
		coloredAlligator.addChild(egg);
		assertTrue(Boundedness.isFree(egg));
		assertFalse(Boundedness.isBound(egg));
	}
}
