package de.croggle.data.persistence;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.ProfileController;
import de.croggle.game.profile.ProfileOverflowException;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class SettingControllerTest extends PlatformTestCase {
	
	ProfileController profileController;
	SettingController settingController;

	@Override
	public void setUp() {
		AlligatorApp app = TestHelper.getApp(this);
		profileController = app.getProfileController();
		settingController = app.getSettingController();
		profileController.deleteAllProfiles();
	}
	
	@Override
	public void tearDown() throws Exception {
		profileController.deleteAllProfiles();
		super.tearDown();
	}
	
	public void testEditCurrentStatistic() {
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));
		
		try {
			profileController.createNewProfile("Max", "assets/picture1");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));
		
		Setting newSetting = new Setting(1f, 0f, true, false);
		settingController.editCurrentSetting(newSetting);
		
		assertTrue(settingController.getCurrentSetting().equals(newSetting));
	}
	
	public void testChangeSetting() {
		
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));
		
		try {
			profileController.createNewProfile("Max", "assets/picture1");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));
		
		try {
			profileController.createNewProfile("Tom", "assets/picture1");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));
		
		Setting newSetting = new Setting(7f, 1f, false, false);
		settingController.editCurrentSetting(newSetting);
		assertTrue(settingController.getCurrentSetting().equals(newSetting));
		
		settingController.changeCurrentSetting("Max");
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));
		
		settingController.changeCurrentSetting("Tom");
		assertTrue(settingController.getCurrentSetting().equals(newSetting));
		
		profileController.deleteCurrentProfile();
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));
		
	}
	
	public void testUpdateListeners() {
		SettingChangeListenerStub stub1 = new SettingChangeListenerStub();
		SettingChangeListenerStub stub2 = new SettingChangeListenerStub();
		
		assertTrue(stub1.getLastReceivedSetting() == null);
		assertTrue(stub2.getLastReceivedSetting() == null);
		
		try {
			settingController.addSettingChangeListener(stub1);
			settingController.addSettingChangeListener(stub2);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			settingController.addSettingChangeListener(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Listener must not be null"));
		}
		
		try {
			profileController.createNewProfile("Max", "assets/picture1");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		
		assertTrue(stub1.getLastReceivedSetting().equals(new Setting()));
		assertTrue(stub2.getLastReceivedSetting().equals(new Setting()));
		
		Setting newSetting = new Setting(0f, -1f, true, true);
		settingController.editCurrentSetting(newSetting);
		
		assertTrue(stub1.getLastReceivedSetting().equals(newSetting));
		assertTrue(stub2.getLastReceivedSetting().equals(newSetting));
		
		try {
			profileController.createNewProfile("Anna", "assets/picture1");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		
		assertTrue(stub1.getLastReceivedSetting().equals(new Setting()));
		assertTrue(stub2.getLastReceivedSetting().equals(new Setting()));
		
		profileController.changeCurrentProfile("Max");
		
		assertTrue(stub1.getLastReceivedSetting().equals(newSetting));
		assertTrue(stub2.getLastReceivedSetting().equals(newSetting));
	}
	
	

}
