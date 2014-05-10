package de.croggle.game.board.operations;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.Egg;
import de.croggle.game.event.BoardEventMessenger;

public class RemoveLonelyAgedAlligatorsTest extends TestCase {

	public void testRemove() {
		removeTestWithMessenger(new BoardEventMessenger());
		removeTestWithMessenger(null);
	}

	private void removeTestWithMessenger(BoardEventMessenger messenger) {
		final Board board = new Board();
		final AgedAlligator aged1 = new AgedAlligator(false, false);
		final AgedAlligator aged2 = new AgedAlligator(false, false);
		final AgedAlligator aged3 = new AgedAlligator(false, false);
		board.addChild(aged1);
		board.addChild(aged2);
		board.addChild(aged3);
		final Egg egg = new Egg(false, false, new Color(0), false);
		aged1.addChild(egg);
		aged3.addChild(new Egg(false, false, new Color(0), false));
		aged3.addChild(new Egg(false, false, new Color(0), false));

		RemoveLonelyAgedAlligators.remove(board, messenger);

		assertSame(egg, board.getChildAtPosition(0));
		assertSame(aged3, board.getChildAtPosition(1));
		assertEquals(-1, board.getChildPosition(aged2));
	}
}
