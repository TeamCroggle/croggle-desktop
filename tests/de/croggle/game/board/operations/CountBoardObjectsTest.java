package de.croggle.game.board.operations;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

public class CountBoardObjectsTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a1 = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a1);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a1.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		assertEquals(4, CountBoardObjects.count(b));
	}
}
