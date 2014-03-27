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

	
	public void testController(){
		LevelPackagesController controller = new LevelPackagesController(app);
		assertTrue(controller.getPackageSize(0) == 12);
		LevelController lvlController = controller.getLevelController(0);
		assertTrue(lvlController.getPackageSize() == 12);
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
	
	public void testAnimation(){
		LevelPackage l = new LevelPackage(5, "package", "description",
			"emblemPath", true, "animation", "design");
		assertEquals(l.getAnimation(), "animation");
		
		LevelPackage l2 = new LevelPackage(6, "package", "description", "emblemPath", false, " ", "design");
		assertNull(l2.getAnimation());
	}
}
