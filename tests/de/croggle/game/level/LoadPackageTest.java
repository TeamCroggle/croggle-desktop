package de.croggle.game.level;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import de.croggle.AlligatorApp;
import de.croggle.test.TestHelper;

public class LoadPackageTest extends TestCase {

	AlligatorApp app;

	@Override
	protected void setUp() {
		TestHelper.setupAll();
		app = TestHelper.getApp();
	}

	public void testLoading() {
		LevelPackagesController controller = new LevelPackagesController(app);

		List<LevelPackage> list = controller.getLevelPackages();
		Assert.assertTrue(list.size() == 2);
	}

	public void testLoadedValues() {
		LevelPackagesController controller = new LevelPackagesController(app);

		List<LevelPackage> list = controller.getLevelPackages();
		LevelPackage one = list.get(0);
		Assert.assertEquals(one.getDescription(),
				"Levelpaket zum Erlernen von Croggel.");
	}
}
