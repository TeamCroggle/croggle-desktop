package de.croggle.data.persistence;

import junit.framework.TestCase;

public class SettingTest extends TestCase {

	Setting setting;

	public void testGetterAndSetter() {
		setting = new Setting();
		setting.setColorblindEnabled(false);
		assertFalse(setting.isColorblindEnabled());
		setting.setVolumeEffects(0.5f);
		assertEquals(0.5f, setting.getVolumeEffects(), 0.000001);
		setting.setVolumeMusic(0.5f);
		assertEquals(0.5f, setting.getVolumeMusic(), 0.0000001);
		setting.setZoomEnabled(false);
		assertFalse(setting.isZoomEnabled());
	}

	public void testEqualsMethod() {
		Setting setting1 = new Setting();
		Setting setting2 = new Setting();
		Setting setting3 = new Setting(0.6f, 0.6f, false, false);
		Setting setting4 = new Setting(0.4f, 0.4f, true, true);
		Setting setting5 = new Setting(0.6f, 0.6f, false, true);
		Setting setting6 = new Setting(0.6f, 0.6f, true, false);
		Setting setting7 = new Setting(0.6f, 0.4f, false, false);
		Setting settingTest = new Setting(0.4f, 0.6f, false, false);
		Setting setting8 = null;
		int setting9 = 9;

		assertTrue(setting1.equals(setting1));
		assertTrue(setting1.equals(setting2));
		assertFalse(setting1.equals(setting3));
		assertFalse(setting1.equals(setting9));
		assertFalse(setting1.equals(setting8));
		assertFalse(setting3.equals(setting4));
		assertFalse(setting3.equals(setting6));
		assertFalse(setting3.equals(setting5));
		assertFalse(setting3.equals(setting7));
		assertFalse(setting3.equals(settingTest));
	}
}
