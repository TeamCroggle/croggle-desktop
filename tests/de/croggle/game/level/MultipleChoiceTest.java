package de.croggle.game.level;

import de.croggle.AlligatorApp;
import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.Egg;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class MultipleChoiceTest extends PlatformTestCase {
	
	AlligatorApp app;


	@Override
	protected void setUp() {
		TestHelper.setupGdxFiles(this);
		app = new AlligatorApp();
	}
	
	
	public void testCase0(){
		MultipleChoiceLevel l = (MultipleChoiceLevel)LevelLoadHelper.instantiate(0, 6, app);
		assertTrue(l.validateAnswer(0));
		assertTrue(l.getAnswers().length == 3);
		assertFalse(l.validateAnswer(1));
		assertNotNull(l.getInitialBoard());
		assertNotNull(l.getHint());
		assertNotNull(l.getAnimation());
	}
	
	public void testSolved(){
		Level l = LevelLoadHelper.instantiate(0, 6, app);
		final Board board = new Board();
		final Color color = new Color(1);
		final Egg egg = new Egg(false, false, color, false);
		board.addChild(egg);
		assertFalse(l.isLevelSolved(board, 1));
		final Board board1 = new Board();
		final Color color1 = new Color(5);
		final Egg egg1 = new Egg(false, false, color1, false);
		board1.addChild(egg1);
		assertTrue(l.isLevelSolved(board1, 1));
	}

}
