package de.croggle;

import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class AlligatorAppTest extends PlatformTestCase {
	
	AlligatorApp app;

	protected void setUp() throws Exception {
		app = TestHelper.getApp(this);
	}
	
	public void testSoundController(){
		assertNotNull(app.getSoundController());
	}
	
	public void testLevelPackagesController(){
		assertNotNull(app.getLevelPackagesController());
	}
	
	public void testAssetsManager(){
		assertNotNull(app.getAssetManager());
	}
	
	public void testVoidMethods(){
		app.pause();
		app.resize(0, 0);
		assertNotNull(app);
	}

}
