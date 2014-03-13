package de.croggle.game.board;

import junit.framework.TestCase;

public class ExceptionsTest extends TestCase {
	private final static String message = "Message";

	public void testAlligatorOverflowException() {
		try {
			throw new AlligatorOverflowException();
		} catch (AlligatorOverflowException e) {
		}
		try {
			throw new AlligatorOverflowException(message);
		} catch (AlligatorOverflowException e) {
			assertEquals(message, e.getMessage());
		}
	}

	public void testIllegalBoardException() {
		try {
			throw new IllegalBoardException();
		} catch (IllegalBoardException e) {
		}
		try {
			throw new IllegalBoardException(message);
		} catch (IllegalBoardException e) {
			assertEquals(message, e.getMessage());
		}
	}

	public void testNoSuchChildException() {
		try {
			throw new NoSuchChildException();
		} catch (NoSuchChildException e) {
		}
		try {
			throw new NoSuchChildException(message);
		} catch (NoSuchChildException e) {
			assertEquals(message, e.getMessage());
		}

	}
}
