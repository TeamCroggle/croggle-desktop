package de.croggle.game.board.operations.validation;

import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.operations.Boundedness;
import junit.framework.TestCase;

public class BoundednessTest extends TestCase {

	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);
		
		assertFalse(Boundedness.isFree(e1));
		assertTrue(Boundedness.isBound(e1));
		assertFalse(Boundedness.isBound(e2));
		assertTrue(Boundedness.isFree(e2));
	}
}
