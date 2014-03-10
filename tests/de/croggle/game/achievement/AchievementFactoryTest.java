package de.croggle.game.achievement;

import de.croggle.data.LocalizationHelper;
import de.croggle.test.TestLocalizationBackend;
import junit.framework.TestCase;

public class AchievementFactoryTest extends TestCase {

	public void testInit() {
		TestLocalizationBackend backend = new TestLocalizationBackend();
		LocalizationHelper.setBackend(backend);
		backend.putString("achievement_alligators_placed",
				" Alligators placed .");
		backend.putString("achievement_alligators_placed_final",
				"Alligators placed .");
		backend.putString("achievement_alligators_eaten", " alligators eaten .");
		backend.putString("achievement_alligators_eaten_final", " eaten .");
		backend.putString("achievement_alligators_eaten_per_level",
				" Alligators: one level .");
		backend.putString("achievement_alligators_eaten_per_level_final",
				"Alligators placed: one level .");
		backend.putString("achievement_alligators_placed_per_level",
				"  Allgators placed per Level .");
		backend.putString("achievement_alligators_placed_per_level_final",
				"Alligators placed per Level .");
		backend.putString("achievement_hints_used_per_level",
				"fewer than one hints used .");
		backend.putString("achievement_level_completed", " level completed .");
		backend.putString("achievement_minutes_played", " minutes played .");
		backend.putString("achievement_hours_played", " hours played .");
		backend.putString("achievement_time_final", "played .");
		
		AchievementFactory achievementFactory = new AchievementFactory();
		assertNull(achievementFactory.createAchievement(90));
		for ( int i = 0; i < 7; i++) {
			assertTrue(achievementFactory.createAchievement(i).getId() == i);
		}
	}
}
