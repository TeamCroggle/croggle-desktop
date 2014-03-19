package de.croggle.game.board.operations;

import java.util.List;

import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import junit.framework.TestCase;

public class FlattenTreeTest extends TestCase {
	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);
		
		List<BoardObject> list = FlattenTree.toList(b);
		
		assertEquals(4, list.size());
		assertTrue(list.contains(b));
		assertTrue(list.contains(a));
		assertTrue(list.contains(e1));
		assertTrue(list.contains(e2));
		
		List<InternalBoardObject> internalList = FlattenTree.toList(a);
		
		assertEquals(internalList.size(), 2);
		assertTrue(internalList.contains(a));
		assertTrue(internalList.contains(e1));
		assertFalse(internalList.contains(e2));
		
		BoardObject boardObjectsArray[] = FlattenTree.toArray(b);
		
		assertEquals(4, boardObjectsArray.length); 
		assertTrue(boardObjectsArray[0].equals(b));
		assertTrue(boardObjectsArray[1].equals(a));
		assertTrue(boardObjectsArray[2].equals(e1));
		assertTrue(boardObjectsArray[3].equals(e2));
		
		InternalBoardObject internalBoardObjectsArray[] = FlattenTree.toArray(a);
		
		assertEquals(internalBoardObjectsArray.length, 2);
		assertTrue(internalBoardObjectsArray[0].equals(a));
		assertTrue(internalBoardObjectsArray[1].equals(e1));
		
		
		
	}
}
