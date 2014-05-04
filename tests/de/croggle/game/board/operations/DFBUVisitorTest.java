package de.croggle.game.board.operations;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;

public class DFBUVisitorTest extends TestCase {

	public void testSimple() {
		final ArrayList<BoardObject> result = new ArrayList<BoardObject>();
		DFBUVisitor visitor = new DFBUVisitor() {
			@Override
			protected void dispatchAgedAlligator(AgedAlligator alligator) {
				result.add(alligator);
			}

			@Override
			protected void dispatchBoard(Board board) {
				result.add(board);
			}

			@Override
			protected void dispatchColoredAlligator(ColoredAlligator alligator) {
				result.add(alligator);
			}

			@Override
			protected void dispatchEgg(Egg egg) {
				result.add(egg);
			}
		};

		Board b = new Board();
		ColoredAlligator a1 = new ColoredAlligator(true, true, null, true);
		Egg e11 = new Egg(true, true, null, true);
		Egg e12 = new Egg(true, true, null, true);
		ColoredAlligator a2 = new ColoredAlligator(true, true, null, true);
		Egg e21 = new Egg(true, true, null, true);

		b.addChild(a1);
		b.addChild(a2);

		a1.addChild(e11);
		a1.addChild(e12);

		a2.addChild(e21);

		visitor.beginTraversal(b);

		ArrayList<BoardObject> expected = new ArrayList<BoardObject>(
				Arrays.asList(e11, e12, a1, e21, a2, b));
		assertEquals(expected, result);
	}
}
