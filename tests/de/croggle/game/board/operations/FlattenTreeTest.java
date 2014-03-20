package de.croggle.game.board.operations;

import java.util.List;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

public class FlattenTreeTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		AgedAlligator aged = new AgedAlligator(false, false);
		b.addChild(aged);

		ColoredAlligator colored1 = new ColoredAlligator(true, true, new Color(
				0), true);
		ColoredAlligator colored2 = new ColoredAlligator(false, false,
				new Color(1), false);
		aged.addChild(colored1);
		aged.addChild(colored2);
		Egg e1 = new Egg(true, true, new Color(0), true);
		colored1.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		colored1.addChild(e2);
		Egg e3 = new Egg(false, false, new Color(1), false);
		colored2.addChild(e3);

		List<BoardObject> list = FlattenTree.toList(b);
		List<InternalBoardObject> internalList = FlattenTree.toList(aged);
		BoardObject[] array = FlattenTree.toArray(b);
		BoardObject[] internalArray = FlattenTree.toArray(aged);

		assertEquals(7, list.size());
		assertTrue(list.contains(b));
		assertTrue(list.contains(aged));
		assertTrue(list.contains(colored1));
		assertTrue(list.contains(colored2));
		assertTrue(list.contains(e1));
		assertTrue(list.contains(e2));

		assertTrue(list.contains(e3));

		assertEquals(6, internalList.size());
		assertTrue(internalList.contains(aged));
		assertTrue(internalList.contains(colored1));
		assertTrue(internalList.contains(colored2));
		assertTrue(internalList.contains(e1));
		assertTrue(internalList.contains(e2));
		assertTrue(internalList.contains(e3));

		assertEquals(7, array.length);
		assertTrue(arrayContains(array, b));
		assertTrue(arrayContains(array, aged));
		assertTrue(arrayContains(array, colored1));
		assertTrue(arrayContains(array, colored2));
		assertTrue(arrayContains(array, e1));
		assertTrue(arrayContains(array, e2));
		assertTrue(arrayContains(array, e3));

		assertEquals(6, internalArray.length);
		assertTrue(arrayContains(internalArray, aged));
		assertTrue(arrayContains(internalArray, colored1));
		assertTrue(arrayContains(internalArray, colored2));
		assertTrue(arrayContains(internalArray, e1));
		assertTrue(arrayContains(internalArray, e2));
		assertTrue(arrayContains(internalArray, e3));

	}

	private <T> boolean arrayContains(T[] array, T element) {
		for (T e : array) {
			if (element == e) {
				return true;
			}
		}
		return false;
	}
}
