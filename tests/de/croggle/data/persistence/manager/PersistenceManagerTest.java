package de.croggle.data.persistence.manager;

import java.util.ArrayList;
import java.util.List;

import de.croggle.AlligatorApp;
import de.croggle.backends.sqlite.SQLException;
import de.croggle.data.persistence.LevelProgress;
import de.croggle.data.persistence.Setting;
import de.croggle.data.persistence.Statistic;
import de.croggle.game.achievement.Achievement;
import de.croggle.game.achievement.AchievementController;
import de.croggle.game.achievement.AlligatorsEatenAchievement;
import de.croggle.game.achievement.TimeAchievement;
import de.croggle.game.profile.Profile;
import de.croggle.game.profile.ProfileOverflowException;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;
import de.croggle.util.SparseArray;

public class PersistenceManagerTest extends PlatformTestCase {

	PersistenceManager persistenceManager;
	AchievementController achievementController;

	@Override
	public void setUp() {
		AlligatorApp app = TestHelper.getApp(this);
		persistenceManager = app.getPersistenceManager();
		achievementController = app.getAchievementController();
	}

	@Override
	public void tearDown() {
		persistenceManager.clearTables();
	}

	public void testAddProfile() {
		Profile profile = new Profile("Hans", "assets/path1");
		Setting setting = new Setting(1, 2, true, false);
		Statistic statistic = new Statistic(1, 1, 2, 3, 5, 8, 21, 34);
		profile.setSetting(setting);
		profile.setStatistic(statistic);

		assertFalse(persistenceManager.isNameUsed("Hans"));
		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.isNameUsed("Hans"));

		assertTrue(profile.equals(persistenceManager.getProfile("Hans")));
		assertTrue(profile.getSetting().equals(
				persistenceManager.getSetting("Hans")));
		assertTrue(profile.getStatistic().equals(
				persistenceManager.getStatistic("Hans")));
	}

	public void testEditProfile() {
		Profile profile1 = new Profile("Lea", "assets/path3");
		Setting setting = new Setting(1, 2, true, false);
		Statistic statistic = new Statistic(1, 1, 2, 3, 5, 8, 13, 21);
		profile1.setSetting(setting);
		profile1.setStatistic(statistic);

		persistenceManager.addProfile(profile1);

		Profile profile2 = new Profile("Anne", "assets/path56");

		persistenceManager.editProfile("Lea", profile2);

		assertFalse(persistenceManager.isNameUsed("Lea"));

		assertTrue(persistenceManager.getProfile("Lea") == null);
		assertTrue(persistenceManager.getSetting("Lea") == null);
		assertTrue(persistenceManager.getStatistic("Lea") == null);

		assertTrue(persistenceManager.getProfile("Anne").equals(profile2));
		assertTrue(persistenceManager.getSetting("Anne").equals(setting));
		assertTrue(persistenceManager.getStatistic("Anne").equals(statistic));

		assertTrue(persistenceManager.isNameUsed("Anne"));

	}
	
	public void testProblematicStrings() {
		try {
			String[] problematicStrings = {" ' ", " '' ", " \" "};
			String picturePath = " \" ' ";
			for (int i = 0; i < problematicStrings.length; i++) {
				String problematicString = problematicStrings[i];
				
				Profile profile = new Profile(problematicString, picturePath);
				persistenceManager.addProfile(profile);
				
				assertTrue(persistenceManager.getProfile(problematicString).equals(profile));
				assertTrue(persistenceManager.getStatistic(problematicString).equals(new Statistic()));
				assertTrue(persistenceManager.getSetting(problematicString).equals(new Setting()));
				
				LevelProgress levelProgress = new LevelProgress(0, false, problematicString, 0);
				persistenceManager.saveLevelProgress(problematicString, levelProgress);
				assertTrue(persistenceManager.getLevelProgress(problematicString, 0).equals(levelProgress));
				
				Achievement achievement = new TimeAchievement();
				achievement.setId(-1);
				achievement.setIndex(100);
				
				List<Achievement> achievements = new ArrayList<Achievement>();
				achievements.add(achievement);
				
				persistenceManager.saveUnlockedAchievements(problematicString, achievements);
				assertTrue(persistenceManager.getAllUnlockedAchievements(problematicString).get(-1) == achievement.getIndex());
;				
				}
		} catch (SQLException sqle) {
			fail();
		}
		
	}

	public void testGetAllProfiles() {
		Profile profile1 = new Profile("Max", "assets/path1");
		Profile profile2 = new Profile("Tim", "assets/path2");
		Profile profile3 = new Profile("Tom", "assets/path3");

		List<Profile> profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 0);

		persistenceManager.addProfile(profile1);
		persistenceManager.addProfile(profile2);

		profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 2);

		persistenceManager.addProfile(profile3);

		profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 3);

		persistenceManager.deleteProfile("Tom");

		profileList = persistenceManager.getAllProfiles();
		assertTrue(profileList.size() == 2);

		for (Profile profile : profileList) {
			assertTrue(profile.equals(profile1) || profile.equals(profile2));
		}
	}

	public void testAddDuplicate() {
		Profile profile1 = new Profile("Tim", "assets/path1");
		Profile profile2 = new Profile("Tim", "assets/path2");
		persistenceManager.addProfile(profile1);
		try {
			persistenceManager.addProfile(profile2);
			fail("Missing exception");
		} catch (IllegalArgumentException e) {
			assertTrue(persistenceManager.getProfile("Tim").equals(profile1));
		}

	}

	public void testDeleteProfile() {
		Profile profile = new Profile("Tim", "test");

		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.isNameUsed("Tim"));

		persistenceManager.deleteProfile("Tim");
		assertFalse(persistenceManager.isNameUsed("Tim"));

		assertTrue(persistenceManager.getProfile("Tim") == null);
		assertTrue(persistenceManager.getSetting("Tim") == null);
		assertTrue(persistenceManager.getStatistic("Tim") == null);

	}

	public void testEditStatistic() {
		Profile profile = new Profile("Tom", "test");
		Statistic statistic1 = new Statistic(1, 2, 3,4, 5, 6, 7, 8);
		Statistic statistic2 = new Statistic(7, 7, 7, 7, 7, 7, 7, 7);
		profile.setStatistic(statistic1);

		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.getStatistic("Tom").equals(statistic1));

		persistenceManager.editStatistic("Tom", statistic2);
		assertTrue(persistenceManager.getStatistic("Tom").equals(statistic2));
	}
	
	public void testGetStatistic() {
		Profile profile = new Profile("Anne", "test");
		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.getStatistic("Anne").equals(new Statistic()));
		
		persistenceManager.saveLevelProgress("Anne", new LevelProgress(1, true, "board1", 20));
		persistenceManager.saveLevelProgress("Anne", new LevelProgress(2, true, "board2", 30));
		persistenceManager.saveLevelProgress("Anne", new LevelProgress(3, true, "board3", 1));
		
		Statistic statistic = persistenceManager.getStatistic("Anne");
		assertTrue(statistic.getLevelsComplete() == 3);
		
		persistenceManager.saveLevelProgress("Anne", new LevelProgress(4, false, "board4", 1));
		
		statistic = persistenceManager.getStatistic("Anne");
		assertTrue(statistic.getLevelsComplete() == 3);
		
		persistenceManager.saveLevelProgress("Anne", new LevelProgress(4, true, "board5", 1));
		
		statistic = persistenceManager.getStatistic("Anne");
		assertTrue(statistic.getLevelsComplete() == 4);
	
		assertTrue(statistic.getPackagesComplete() == 0);
		persistenceManager.saveLevelProgress("Anne", new LevelProgress(11, true, "board1", 20));
		
		statistic = persistenceManager.getStatistic("Anne");
		assertTrue(statistic.getPackagesComplete() == 1);

	}

	public void testEditSetting() {
		Profile profile = new Profile("Tom", "test");
		Setting setting1 = new Setting(1, 2, false, true);
		Setting setting2 = new Setting(5, 6, true, false);
		profile.setSetting(setting1);

		persistenceManager.addProfile(profile);
		assertTrue(persistenceManager.getSetting("Tom").equals(setting1));

		persistenceManager.editSetting("Tom", setting2);
		assertTrue(persistenceManager.getSetting("Tom").equals(setting2));
	}

	public void testEditLevelProgress() {
		Profile profile = new Profile("Anne", "test");
		LevelProgress levelProgress1 = new LevelProgress(1, false, "board1", 20);
		LevelProgress levelProgress2 = new LevelProgress(1, true, "board2", 30);
		persistenceManager.addProfile(profile);

		persistenceManager.saveLevelProgress("Anne", levelProgress1);
		assertTrue(persistenceManager.getLevelProgress("Anne", 1).equals(
				levelProgress1));

		persistenceManager.saveLevelProgress("Anne", levelProgress2);
		assertTrue(persistenceManager.getLevelProgress("Anne", 1).equals(
				levelProgress2));
	}

	public void testInsertAndSaveUnlockedAchievements() {

		Profile profile = new Profile("Tim", "test");
		persistenceManager.addProfile(profile);

		SparseArray<Integer> sia = persistenceManager
				.getAllUnlockedAchievements("Tim");
		assertTrue(sia.size() == achievementController
				.getAvailableAchievements().size());

		
		List<Achievement> achievements = new ArrayList<Achievement>();
		Achievement achievement1 = new TimeAchievement();
		achievement1.setId(1);
		achievement1.setIndex(2);
		
		Achievement achievement2 = new TimeAchievement();
		achievement2.setId(2);
		achievement2.setIndex(5);
		
		Achievement achievement3 = new TimeAchievement();
		achievement3.setId(3);
		achievement3.setIndex(10);
		
		achievements.add(achievement1);
		achievements.add(achievement2);
		achievements.add(achievement3);
		
		persistenceManager.updateUnlockedAchievements("Tim", achievements);
		sia = persistenceManager.getAllUnlockedAchievements("Tim");
		
		assertTrue(sia.get(1) == 2);
		assertTrue(sia.get(2) == 5);
		assertTrue(sia.get(3) == 10);
		List<Achievement> testlist = new ArrayList<Achievement>();
		persistenceManager.saveUnlockedAchievements("tim", testlist);

	}
	
	public void testSaveAdditionalAchievements() {
		Profile profile = new Profile("Tom", "test");
		persistenceManager.addProfile(profile);

		SparseArray<Integer> sia1 = persistenceManager
				.getAllUnlockedAchievements("Tom");
		assertTrue(sia1.size() == achievementController
				.getAvailableAchievements().size());
		
		List<Achievement> achievements = new ArrayList<Achievement>();
		Achievement achievement1 = new TimeAchievement();
		achievement1.setId(-1);
		achievement1.setIndex(0);
		
		Achievement achievement2 = new TimeAchievement();
		achievement2.setId(-2);
		achievement2.setIndex(-20);
		
		Achievement achievement3 = new TimeAchievement();
		achievement3.setId(-3);
		achievement3.setIndex(100000);
		
		achievements.add(achievement1);
		achievements.add(achievement2);
		achievements.add(achievement3);
		
		persistenceManager.saveUnlockedAchievements("Tom", achievements);
		SparseArray<Integer> sia2 = persistenceManager
				.getAllUnlockedAchievements("Tom");
		
		assertTrue(sia1.size() + 3 == sia2.size());
		
		assertTrue(sia2.get(-1) == achievement1.getIndex());
		assertTrue(sia2.get(-2) == achievement2.getIndex());
		assertTrue(sia2.get(-3) == achievement3.getIndex());
		
	}

	public void testEditUnlockedAchievements() {
		Profile profile = new Profile("Tom", "test");
		persistenceManager.addProfile(profile);

		Achievement achievement1 = new TimeAchievement();
		achievement1.setId(1);
		achievement1.setIndex(3);

		Achievement achievement2 = new TimeAchievement();
		achievement2.setId(2);
		achievement2.setIndex(2);

		Achievement achievement3 = new TimeAchievement();
		achievement3.setId(3);
		achievement3.setIndex(4);

		List<Achievement> achievements = new ArrayList<Achievement>();

		achievements.add(achievement1);
		achievements.add(achievement2);
		achievements.add(achievement3);

		persistenceManager.updateUnlockedAchievements("Tom", achievements);
		SparseArray<Integer> sa = persistenceManager
				.getAllUnlockedAchievements("Tom");

		assertTrue(sa.get(1) == achievement1.getIndex());
		assertTrue(sa.get(2) == achievement2.getIndex());
		assertTrue(sa.get(3) == achievement3.getIndex());
	}
	
	public void testException() {
		try {
			persistenceManager.addProfile(null);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
