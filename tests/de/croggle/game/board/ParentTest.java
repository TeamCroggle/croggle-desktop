package de.croggle.game.board;

import java.util.HashMap;

import junit.framework.Assert;
import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.operations.BoardObjectVisitor;

public class ParentTest extends TestCase {
	private Parent parent;

	protected void setUp() {
		parent = getNewParent();
	}

	public void testAddChildAndClear() {
		final InternalBoardObject child = getNewChild();
		parent.addChild(child);
		Assert.assertEquals(1, parent.getChildCount());
		Assert.assertEquals(0, parent.getChildPosition(child));
		Assert.assertTrue(parent.getFirstChild() == child);
		Assert.assertTrue(child.getParent() == parent);
		Assert.assertTrue(parent.isLastChild(child));

		int i = 0;
		for (InternalBoardObject currentChild : parent) {
			if (i == 0) {
				Assert.assertTrue(currentChild == child);
				i++;
			} else {
				fail();
			}
		}
		parent.addChild(getNewChild());
		assertFalse(parent.isLastChild(child));
		assertFalse(parent.addChild(child));
		parent.clearChildren();
		assertTrue(parent.getChildCount() == 0);
		assertFalse(parent.isLastChild(child));
	}

	public void testInsertChild() {
		final InternalBoardObject child1 = getNewChild();
		final InternalBoardObject child2 = getNewChild();
		final InternalBoardObject child3 = getNewChild();
		parent.insertChild(child3, 0);
		parent.insertChild(child1, 0);
		parent.insertChild(child2, 1);
		Assert.assertTrue(child1.getParent() == parent);
		Assert.assertTrue(child2.getParent() == parent);
		Assert.assertTrue(child3.getParent() == parent);
		Assert.assertEquals(parent.getChildCount(), 3);
		Assert.assertEquals(parent.getChildPosition(child1), 0);
		Assert.assertEquals(parent.getChildPosition(child2), 1);
		Assert.assertEquals(parent.getChildPosition(child3), 2);
		Assert.assertFalse(parent.isLastChild(child1));
		Assert.assertFalse(parent.isLastChild(child2));
		Assert.assertTrue(parent.isLastChild(child3));
		int i = 0;
		for (InternalBoardObject currentChild : parent) {
			switch (i) {
			case 0:
				Assert.assertTrue(currentChild == child1);
				break;
			case 1:
				Assert.assertTrue(currentChild == child2);
				break;
			case 2:
				Assert.assertTrue(currentChild == child3);
				break;
			}
			i++;
		}
		Assert.assertTrue(parent.getChildAfter(child1) == child2);
		Assert.assertTrue(parent.getChildAfter(child2) == child3);
		Assert.assertTrue(parent.getChildAfter(child3) == null);

		assertFalse(parent.insertChild(child3, 0));
	}

	public void testRemoveChild() {
		final InternalBoardObject child1 = getNewChild();
		final InternalBoardObject child2 = getNewChild();
		final InternalBoardObject child3 = getNewChild();
		parent.addChild(child1);
		parent.addChild(child2);
		parent.addChild(child3);
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(1, parent.getChildPosition(child2));
		Assert.assertEquals(2, parent.getChildPosition(child3));

		parent.removeChild(child1);
		Assert.assertEquals(-1, parent.getChildPosition(child1));
		Assert.assertEquals(0, parent.getChildPosition(child2));
		Assert.assertEquals(1, parent.getChildPosition(child3));
		parent.removeChild(child2);
		Assert.assertEquals(-1, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(0, parent.getChildPosition(child3));
		parent.removeChild(child3);
		Assert.assertEquals(-1, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(-1, parent.getChildPosition(child3));

	}

	public void testReplaceChild() {
		final InternalBoardObject child1 = getNewChild();
		final InternalBoardObject child2 = getNewChild();
		final InternalBoardObject child3 = getNewChild();
		parent.addChild(child1);
		parent.addChild(child2);
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(1, parent.getChildPosition(child2));
		Assert.assertEquals(-1, parent.getChildPosition(child3));

		Assert.assertFalse(parent.replaceChild(child3, child1));
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(1, parent.getChildPosition(child2));
		Assert.assertEquals(-1, parent.getChildPosition(child3));

		Assert.assertTrue(parent.replaceChild(child2, child3));
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(1, parent.getChildPosition(child3));

		Assert.assertFalse(parent.replaceChild(child1, child3));
		Assert.assertEquals(0, parent.getChildPosition(child1));
		Assert.assertEquals(-1, parent.getChildPosition(child2));
		Assert.assertEquals(1, parent.getChildPosition(child3));
	}

	public void testGetFirstChildExeption() {
		try {
			parent.getFirstChild();
			fail("Exception should have been thrown.");
		} catch (NoSuchChildException e) {
		}
	}

	public void testGetChildAfter() {
		final InternalBoardObject parentlessChild = getNewChild();
		final InternalBoardObject firstChild = getNewChild();
		final InternalBoardObject secondChild = getNewChild();

		parent.addChild(firstChild);
		parent.addChild(secondChild);

		assertSame(secondChild, parent.getChildAfter(firstChild));
		assertNull(parent.getChildAfter(secondChild));
		assertNull(parent.getChildAfter(parentlessChild));
	}

	public void testMatch() {
		assertFalse(parent.match(null));

		final Parent otherParent = new AgedAlligator(false, false);
		assertFalse(parent.match(otherParent));

		final Parent parentWithChild = getNewParent();
		parentWithChild.addChild(getNewChild());
		assertFalse(parent.match(parentWithChild));

		parent.addChild(getNewChild());
		assertTrue(parent.match(parentWithChild));
	}

	public void testMatchWithRecoloring() {
		assertFalse(parent.matchWithRecoloring(null,
				new HashMap<Color, Color>()));

		final Parent otherParent = new AgedAlligator(false, false);
		assertFalse(parent.matchWithRecoloring(otherParent,
				new HashMap<Color, Color>()));

		final Color color1 = new Color(0);
		final Color color2 = new Color(1);
		final Parent parentWithChild = getNewParent();
		parentWithChild.addChild(new Egg(false, false, color1, false));
		assertFalse(parent.matchWithRecoloring(parentWithChild,
				new HashMap<Color, Color>()));

		final Egg egg = new Egg(false, false, color1, false);
		parent.addChild(egg);
		assertTrue(parent.matchWithRecoloring(parentWithChild,
				new HashMap<Color, Color>()));

		egg.setColor(color2);
		assertFalse(parent.matchWithRecoloring(parentWithChild,
				new HashMap<Color, Color>()));
		final HashMap<Color, Color> recoloring = new HashMap<Color, Color>();
		recoloring.put(color1, color2);
		assertTrue(parent.matchWithRecoloring(parentWithChild, recoloring));
	}

	private InternalBoardObject getNewChild() {
		return new Egg(false, false, new Color(1), false);
	}

	private Parent getNewParent() {
		return new Parent() {
			@Override
			public BoardObject copy() {
				return null;
			}

			@Override
			public void accept(BoardObjectVisitor visitor) {
			}
		};
	}
}
