package de.croggle.game.profile;

import de.croggle.AlligatorApp;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.SettingController;
import de.croggle.data.persistence.Statistic;
import de.croggle.data.persistence.StatisticController;
import de.croggle.game.achievement.AchievementController;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class ProfileControllerTest extends PlatformTestCase {

	ProfileController profileController;
	SettingController settingController;
	StatisticController statisticController;
	AchievementController achievementController;

	@Override
	public void setUp() {
		AlligatorApp app = TestHelper.getApp(this);
		profileController = app.getProfileController();
		settingController = app.getSettingController();
		statisticController = app.getStatisticController();
		achievementController = app.getAchievementController();

		profileController.deleteAllProfiles();
	}

	@Override
	public void tearDown() {
		profileController.deleteAllProfiles();
	}

	public void testCreateProfile() {
		try {
			String name = "Max";
			String picturePath = "assets/picture1";

			assertTrue(profileController.getCurrentProfile() == null);
			assertTrue(statisticController.getCurrentStatistic() == null);

			profileController.createNewProfile(name, picturePath);

			assertTrue(profileController.getCurrentProfileName().equals(name)
					&& profileController.getCurrentProfile().getPicturePath()
							.equals(picturePath));
			assertTrue(statisticController.getCurrentStatistic() != null);

		} catch (IllegalArgumentException e) {
			fail();
		} catch (ProfileOverflowException e) {
			fail();
		}
	}

	public void testProfileOverflowException() {
		try {
			for (int i = 0; i <= ProfileController.MAX_PROFILE_NUMBER; i++) {
				profileController.createNewProfile("" + i, "assets/picture1");
			}
			fail();
		} catch (IllegalArgumentException e) {
			fail();
		} catch (ProfileOverflowException e) {
			assertTrue(true);
		}
	}

	public void testAddInvalidProfile() {
		String name = "Max";
		String picturePath1 = "assets/picture1";
		String picturePath2 = "assets/picture2";
		try {
			profileController.createNewProfile(name, picturePath1);
			profileController.createNewProfile(name, picturePath2);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (ProfileOverflowException e) {
			fail();
		}

		try {
			profileController.createNewProfile("", picturePath1);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (ProfileOverflowException e) {
			fail();
		}
	}

	public void testEditCurrentProfile() {
		String oldName = "Tim";
		String oldPicturePath = "assets/picture1";
		try {
			profileController.createNewProfile(oldName, oldPicturePath);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		assertTrue(profileController.getCurrentProfileName().equals(oldName)
				&& profileController.getCurrentProfile().getPicturePath()
						.equals(oldPicturePath));

		Setting setting = new Setting(0.1f, 1f, false, true);
		Statistic statistic = new Statistic(-2, -2, -2, -2, -2, -2, -2, -2, -2,
				-2);

		settingController.editCurrentSetting(setting);
		statisticController.editCurrentStatistic(statistic);

		settingController.editCurrentSetting(setting);
		statisticController.editCurrentStatistic(statistic);

		assertTrue(settingController.getCurrentSetting().equals(setting));
		assertTrue(statisticController.getCurrentStatistic().equals(statistic));

		String newName = "Hans";
		String newPicturePath = "assets/picture7";

		profileController.editCurrentProfile(newName, newPicturePath);

		assertTrue(profileController.getCurrentProfileName().equals(newName)
				&& profileController.getCurrentProfile().getPicturePath()
						.equals(newPicturePath));
		assertTrue(settingController.getCurrentSetting().equals(setting));
		assertTrue(statisticController.getCurrentStatistic().equals(statistic));

		try {
			profileController.createNewProfile(oldName, oldPicturePath);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		assertTrue(profileController.getCurrentProfileName().equals(oldName)
				&& profileController.getCurrentProfile().getPicturePath()
						.equals(oldPicturePath));

	}

	public void testDeleteCurrentProfile() {
		String name = "Anne";
		String picturePath = "assets/picture1";
		try {
			profileController.createNewProfile(name, picturePath);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		assertTrue(profileController.getCurrentProfileName().equals(name)
				&& profileController.getCurrentProfile().getPicturePath()
						.equals(picturePath));

		Setting setting = new Setting(0.1f, 1f, true, false);
		Statistic statistic = new Statistic(-2, -2, -2, -2, -2, -2, -2, -2, -2,
				-2);

		settingController.editCurrentSetting(setting);
		statisticController.editCurrentStatistic(statistic);

		settingController.editCurrentSetting(setting);
		statisticController.editCurrentStatistic(statistic);

		assertTrue(settingController.getCurrentSetting().equals(setting));
		assertTrue(statisticController.getCurrentStatistic().equals(statistic));

		profileController.deleteCurrentProfile();

		assertTrue(profileController.getCurrentProfile() == null);
		assertTrue(statisticController.getCurrentStatistic() == null);
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));

	}

	public void testChangeCurrentProfile() {
		String name = "Max";
		String picturePath = "assets/picture1";
		try {
			profileController.createNewProfile(name, picturePath);
		} catch (IllegalArgumentException e) {
			fail();
		} catch (ProfileOverflowException e) {
			fail();
		}

		assertTrue(profileController.getCurrentProfileName().equals(name)
				&& profileController.getCurrentProfile().getPicturePath()
						.equals(picturePath));
		assertTrue(statisticController.getCurrentStatistic().equals(
				new Statistic()));
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));

		Setting setting = new Setting(0.5f, 0.6f, true, true);
		Statistic statistic = new Statistic(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		settingController.editCurrentSetting(setting);
		statisticController.editCurrentStatistic(statistic);

		assertTrue(settingController.getCurrentSetting().equals(setting));
		assertTrue(statisticController.getCurrentStatistic().equals(statistic));

		name = "Anna";
		picturePath = "assets/picture2";
		try {
			profileController.createNewProfile(name, picturePath);
		} catch (IllegalArgumentException e) {
			fail();
		} catch (ProfileOverflowException e) {
			fail();
		}

		assertTrue(profileController.getCurrentProfileName().equals(name)
				&& profileController.getCurrentProfile().getPicturePath()
						.equals(picturePath));
		assertTrue(statisticController.getCurrentStatistic().equals(
				new Statistic()));
		assertTrue(settingController.getCurrentSetting().equals(new Setting()));

		profileController.changeCurrentProfile("Max");

		assertTrue(settingController.getCurrentSetting().equals(setting));
		assertTrue(statisticController.getCurrentStatistic().equals(statistic));

		assertTrue(profileController.getCurrentProfileName().equals("Max")
				&& profileController.getCurrentProfile().getPicturePath()
						.equals("assets/picture1"));

	}

}
