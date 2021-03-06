package de.croggle.game.level;

import junit.framework.Assert;
import de.croggle.AlligatorApp;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class LevelLoadHelperTest extends PlatformTestCase {
	AlligatorApp app;

	@Override
	protected void setUp() {
		TestHelper.setupGdxFiles(this);
		TestHelper.setupCroggleBackends(this);
		app = new AlligatorApp();
	}

	public void testCase0() {
		Level l = LevelLoadHelper.instantiate(0, 0, app);
		Assert.assertTrue(l.getLevelIndex() == 0);
	}

	public void testCase1() {
		Level l = LevelLoadHelper.instantiate(0, 1, app);
		Assert.assertTrue(l.getLevelIndex() == 1);
	}

	public void testCase2() {
		Level l = LevelLoadHelper.instantiate(0, 2, app);
		Assert.assertTrue(l.getLevelIndex() == 2);
	}

	public void testCase3() {
		Level l = LevelLoadHelper.instantiate(0, 3, app);
		Assert.assertTrue(l.getLevelIndex() == 3);
	}

	public void testCase4() {
		Level l = LevelLoadHelper.instantiate(0, 4, app);
		Assert.assertTrue(l.getLevelIndex() == 4);
	}

	public void testCase5() {
		Level l = LevelLoadHelper.instantiate(0, 5, app);
		Assert.assertTrue(l.getLevelIndex() == 5);
	}

	public void testCase6() {
		Level l = LevelLoadHelper.instantiate(0, 6, app);
		Assert.assertTrue(l.getLevelIndex() == 6);
	}

	public void testCase7() {
		Level l = LevelLoadHelper.instantiate(0, 7, app);
		Assert.assertTrue(l.getLevelIndex() == 7);
	}

	public void testCase8() {
		Level l = LevelLoadHelper.instantiate(0, 8, app);
		Assert.assertTrue(l.getLevelIndex() == 8);
	}

	public void testCase9() {
		Level l = LevelLoadHelper.instantiate(0, 9, app);
		Assert.assertTrue(l.getLevelIndex() == 9);
	}

	public void testCase10() {
		Level l = LevelLoadHelper.instantiate(0, 10, app);
		Assert.assertTrue(l.getLevelIndex() == 10);
	}

	public void testCase11() {
		Level l = LevelLoadHelper.instantiate(0, 11, app);
		Assert.assertTrue(l.getLevelIndex() == 11);
	}

	public void testCase20() {
		Level l = LevelLoadHelper.instantiate(1, 0, app);
		Assert.assertTrue(l.getLevelIndex() == 0);
	}

	public void testCase21() {
		Level l = LevelLoadHelper.instantiate(1, 1, app);
		Assert.assertTrue(l.getLevelIndex() == 1);
	}

	public void testCase22() {
		Level l = LevelLoadHelper.instantiate(1, 2, app);
		l.setSolvedTrue();
		assertTrue(l.getLevelIndex() == 2);
		assertFalse(l.hasAnimation());
		assertFalse(l.getUnlocked());
		assertTrue(l.isSolveable(2));
		assertTrue(l.isSolved());
		assertFalse(l.getShowObjectBar());
		assertTrue(l.getLevelId() == 102);
	}
}
