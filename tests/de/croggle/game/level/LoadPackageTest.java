package de.croggle.game.level;

import java.util.List;

import junit.framework.Assert;
import de.croggle.AlligatorApp;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class LoadPackageTest extends PlatformTestCase {

	AlligatorApp app;

	@Override
	protected void setUp() {
		app = TestHelper.getApp(this);
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
				"Levelpaket zum Erlernen von Croggle.");
	}
	
	public void testCotroller(){
		LevelPackagesController controller = new LevelPackagesController(app);
		assertTrue(controller.getPackageSize(0) == 12);
	}
	
	public void testGetter() {
		LevelPackagesController controller = new LevelPackagesController(app);
		List<LevelPackage> list = controller.getLevelPackages();
		LevelPackage packageOne = list.get(0);
		assertNull(packageOne.getAnimation());
		assertNotNull(packageOne.getDescription());
		assertNotNull(packageOne.getDesign());
		assertNotNull(packageOne.getEmblemPath());
		assertNotNull(packageOne.getName());
		assertNotNull(packageOne.getLevelPackageId());
		LevelPackage test02 = new LevelPackage(1, "test", "test description",
				"not interesting", false, null,
				" test");
		assertNull(test02.getAnimation());
		
	}
}
