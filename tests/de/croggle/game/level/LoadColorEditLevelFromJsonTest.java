package de.croggle.game.level;

import junit.framework.Assert;
import de.croggle.AlligatorApp;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class LoadColorEditLevelFromJsonTest extends PlatformTestCase {

	AlligatorApp app;

	@Override
	protected void setUp() {
		TestHelper.setupGdxFiles(this);
		app = new AlligatorApp();
	}

	public void testCase0() {
		Level l = LevelLoadHelper.instantiate(0, 0, app);
		Assert.assertTrue(l
				.getDescription()
				.equals("Erstes Tutorial Level in dem das Einfärben von Spielelementen erklärt wird."));
		Assert.assertTrue(l.hasAnimation());
		Assert.assertTrue(l.getAbortSimulationAfter() == 111);
	}

	public void testCase1() {
		Level l = LevelLoadHelper.instantiate(0, 1, app);
		Assert.assertTrue(l
				.getDescription()
				.equals("Zweites Tutoriallevel, in dem die β-Reduktion gezeigt wird. Benötigte Kenntnis des Spielers hierfür ist das Einfärben von Elementen."));
		Assert.assertTrue(l.hasAnimation());
		Assert.assertTrue(l.getAbortSimulationAfter() == 111);
	}

	public void testCase5() {
		Level l = LevelLoadHelper.instantiate(0, 5, app);
		Assert.assertFalse(l.hasAnimation());
		Assert.assertTrue(l.getAbortSimulationAfter() == 5);
	}

	public void testCase8() {
		Level l = LevelLoadHelper.instantiate(0, 8, app);
		assertFalse(l.hasAnimation());
		assertTrue(l.getAbortSimulationAfter() == 10);
		assertTrue(((EditLevel)l).getUserColor().length == 6);
		assertTrue(((EditLevel)l).getBlockedColor().length == 0);
	}
}
