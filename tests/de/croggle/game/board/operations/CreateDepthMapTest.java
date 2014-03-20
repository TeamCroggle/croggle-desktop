package de.croggle.game.board.operations;

import java.util.Map;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

public class CreateDepthMapTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		AgedAlligator a1 = new AgedAlligator(false, false);
		ColoredAlligator a2 = new ColoredAlligator(true, true, new Color(0),
				true);
		ColoredAlligator a3 = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a1);
		a1.addChild(a2);
		a1.addChild(a3);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a2.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);
		Egg e3 = new Egg(true, true, new Color(2), true);
		a3.addChild(e3);

		Map<BoardObject, Integer> map = CreateDepthMap.create(b);
		assertEquals(0, (int) map.get(b));
		assertEquals(1, (int) map.get(a1));
		assertEquals(2, (int) map.get(a2));
		assertEquals(2, (int) map.get(a3));
		assertEquals(3, (int) map.get(e1));
		assertEquals(1, (int) map.get(e2));
		assertEquals(3, (int) map.get(e3));
	}
}
