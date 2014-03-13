package de.croggle.data.persistence;

import de.croggle.game.board.Board;
import junit.framework.TestCase;

public class LevelProgressTest extends TestCase {

	public void testGetterAndSetter() {
		LevelProgress levelProgress = new LevelProgress(0, false, null, 0);
		levelProgress.setCurrentBoard(null);
		assertTrue(levelProgress.getCurrentBoard() == null);
		levelProgress.setLevelId(1);
		assertTrue(levelProgress.getLevelId() == 1);
		levelProgress.setUsedTime(1);
		assertTrue(levelProgress.getUsedTime() == 1);
		levelProgress.setSolved(true);
		assertTrue(levelProgress.isSolved());
	}
	
	public void testEqualsMethod() {
		LevelProgress levelProgress1 = new LevelProgress(0, false, null, 0);
		LevelProgress levelProgress2 = new LevelProgress(0, false, null, 0);
		LevelProgress levelProgress3 = new LevelProgress(1, false, null, 0);
		LevelProgress levelProgress4 = new LevelProgress(0, true, null, 0);
		LevelProgress levelProgress5 = new LevelProgress(0, false, "board", 0);
		LevelProgress levelProgress5Alt = new LevelProgress(0, false, "boardTest", 0);
		LevelProgress levelProgress6 = new LevelProgress(0, false, null, 1);
		LevelProgress levelProgress7 = null;
		int levelProgress8 = 1;
		
		assertTrue(levelProgress1.equals(levelProgress1));
		assertTrue(levelProgress1.equals(levelProgress2));
		assertFalse(levelProgress1.equals(levelProgress3));
		assertFalse(levelProgress1.equals(levelProgress4));
		assertFalse(levelProgress1.equals(levelProgress5));
		assertFalse(levelProgress1.equals(levelProgress6));
		assertFalse(levelProgress1.equals(levelProgress7));
		assertFalse(levelProgress1.equals(levelProgress8));
		assertFalse(levelProgress5.equals(levelProgress5Alt));
	}
}
