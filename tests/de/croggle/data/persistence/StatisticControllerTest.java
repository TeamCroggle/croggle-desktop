package de.croggle.data.persistence;

import de.croggle.AlligatorApp;
import de.croggle.game.profile.ProfileController;
import de.croggle.game.profile.ProfileOverflowException;
import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class StatisticControllerTest extends PlatformTestCase {

	ProfileController profileController;
	StatisticController statisticController;

	@Override
	public void setUp() {
		AlligatorApp app = TestHelper.getApp(this);
		profileController = app.getProfileController();
		statisticController = app.getStatisticController();
		profileController.deleteAllProfiles();
	}

	@Override
	protected void tearDown() throws Exception {
		profileController.deleteAllProfiles();
		super.tearDown();
	}

	public void testEditCurrentStatistic() {
		try {
			profileController.createNewProfile("Max", "assets/picture1");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}

		assertTrue(statisticController.getCurrentStatistic().equals(
				new Statistic()));

		Statistic newStatistic = new Statistic(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		statisticController.editCurrentStatistic(newStatistic);

		assertTrue(statisticController.getCurrentStatistic().equals(
				newStatistic));
	}

	public void testChangeCurrentStatistic() {
		String name1 = "Max";
		String picturePath1 = "assets/picture1";
		try {
			profileController.createNewProfile(name1, picturePath1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}

		Statistic newStatistic = new Statistic(1, 2, 3, 4, 5,  7, 8, 9);
		statisticController.editCurrentStatistic(newStatistic);

		assertTrue(statisticController.getCurrentStatistic().equals(
				newStatistic));

		String name2 = "Tom";
		String picturePath2 = "assets/picture2";
		try {
			profileController.createNewProfile(name2, picturePath2);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}
		assertTrue(statisticController.getCurrentStatistic().equals(
				new Statistic()));
		statisticController.changeCurrentStatistic(name1);
		assertTrue(statisticController.getCurrentStatistic().equals(
				newStatistic));
		assertTrue(statisticController.getStatistic(name2).equals(
				new Statistic()));

	}

	public void testProcessStatisticDelta() {
		String name1 = "Max";
		String picturePath1 = "assets/picture1";
		try {
			profileController.createNewProfile(name1, picturePath1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProfileOverflowException e) {
			e.printStackTrace();
		}

		Statistic statisticsDelta = new Statistic(1, 2, 3, 4, 5, 6, 7, 8);
		statisticController.processDelta(statisticsDelta);

		assertTrue(statisticController.getCurrentStatistic().equals(
				statisticsDelta));

		Statistic result = new Statistic(2, 4, 6, 8, 10, 12, 14, 16);
		statisticController.processDelta(statisticsDelta);

		assertTrue(statisticController.getCurrentStatistic().equals(result));

	}
	

}
