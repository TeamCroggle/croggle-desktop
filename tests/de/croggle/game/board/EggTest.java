package de.croggle.game.board;

import java.util.HashMap;

import junit.framework.TestCase;
import de.croggle.game.Color;

public class EggTest extends TestCase {
	private Egg egg;
	private final Color color1 = new Color(0);
	private final Color color2 = new Color(1);

	protected void setUp() {
		egg = new Egg(false, false, color1, false);
	}

	public void testMatch() {
		assertFalse(egg.match(null));
		assertFalse(egg.match(new AgedAlligator(false, false)));
		assertFalse(egg.match(new Egg(false, false, color2, false)));
		assertTrue(egg.match(new Egg(false, false, color1, false)));
	}

	public void testMatchWithRecoloring() {
		assertFalse(egg.matchWithRecoloring(null, new HashMap<Color, Color>()));
		assertFalse(egg.matchWithRecoloring(new AgedAlligator(false, false),
				new HashMap<Color, Color>()));

		final Egg otherEgg = new Egg(false, false, color1, false);
		assertTrue(egg.matchWithRecoloring(otherEgg,
				new HashMap<Color, Color>()));

		otherEgg.setColor(color2);
		assertFalse(egg.matchWithRecoloring(otherEgg,
				new HashMap<Color, Color>()));
		final HashMap<Color, Color> recoloring = new HashMap<Color, Color>();
		recoloring.put(color2, color1);
		assertTrue(egg.matchWithRecoloring(otherEgg, recoloring));
	}
}
