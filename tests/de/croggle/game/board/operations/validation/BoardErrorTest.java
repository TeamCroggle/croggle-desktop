package de.croggle.game.board.operations.validation;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;

public class BoardErrorTest extends TestCase {
	public void testAbstractBoardError() {
		final BoardObject boardObject = new Egg(false, false, new Color(0),
				false);
		final AbstractBoardError boardError = new AbstractBoardError(
				boardObject) {
			@Override
			public void haveDispatched(BoardErrorDispatcher dispatcher) {
			}
		};
		assertSame(boardObject, boardError.getCause());
		try {
			new AbstractBoardError(null) {
				@Override
				public void haveDispatched(BoardErrorDispatcher dispatcher) {
				}
			};
			fail();
		} catch (NullPointerException e) {
		}
	}

	public void testEmptyBoardErrorDispatch() {
		final Board board = new Board();
		final EmptyBoardError error = new EmptyBoardError(board);
		error.haveDispatched(new BoardErrorDispatcher() {

			@Override
			public void dispatch(EmptyBoardError err) {
				assertSame(board, err.getCause());
			}

			@Override
			public void dispatch(AgedAlligatorChildlessError err) {
				fail();
			}

			@Override
			public void dispatch(ColoredAlligatorChildlessError err) {
				fail();
			}

			@Override
			public void dispatch(ObjectUncoloredError err) {
				fail();
			}
		});
	}

	public void testObjectUncoloredErrorDispatch() {
		final InternalBoardObject boardObject = new Egg(false, false,
				Color.uncolored(), false);
		final ObjectUncoloredError error = new ObjectUncoloredError(boardObject);
		error.haveDispatched(new BoardErrorDispatcher() {

			@Override
			public void dispatch(EmptyBoardError err) {
				fail();
			}

			@Override
			public void dispatch(AgedAlligatorChildlessError err) {
				fail();
			}

			@Override
			public void dispatch(ColoredAlligatorChildlessError err) {
				fail();
			}

			@Override
			public void dispatch(ObjectUncoloredError err) {
				assertSame(boardObject, err.getCause());
			}
		});
	}

	public void testColoredAlligatorChildlessErrorDispatch() {
		final ColoredAlligator colored = new ColoredAlligator(false, false,
				new Color(0), false);
		final ColoredAlligatorChildlessError error = new ColoredAlligatorChildlessError(
				colored);
		error.haveDispatched(new BoardErrorDispatcher() {

			@Override
			public void dispatch(EmptyBoardError err) {
				fail();
			}

			@Override
			public void dispatch(AgedAlligatorChildlessError err) {
				fail();
			}

			@Override
			public void dispatch(ColoredAlligatorChildlessError err) {
				assertSame(colored, err.getCause());
			}

			@Override
			public void dispatch(ObjectUncoloredError err) {
				fail();
			}
		});
	}

	public void testAgedAlligatorChildlessErrorDispatch() {
		final AgedAlligator aged = new AgedAlligator(false, false);
		final AgedAlligatorChildlessError error = new AgedAlligatorChildlessError(
				aged);
		error.haveDispatched(new BoardErrorDispatcher() {

			@Override
			public void dispatch(EmptyBoardError err) {
				fail();
			}

			@Override
			public void dispatch(AgedAlligatorChildlessError err) {
				assertSame(aged, err.getCause());
			}

			@Override
			public void dispatch(ColoredAlligatorChildlessError err) {
				fail();
			}

			@Override
			public void dispatch(ObjectUncoloredError err) {
				fail();
			}
		});
	}
}
