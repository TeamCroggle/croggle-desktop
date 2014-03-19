package de.croggle.data.persistence;

import junit.framework.TestCase;

public class StatisticTest extends TestCase {

	public void testGetter() {
		Statistic statistic = new Statistic(7, 7, 7, 7, 7, 7, 7, 7, 7, 7);
		assertTrue(statistic.getAlligatorsEaten() == 7);
		assertTrue(statistic.getAlligatorsPlaced() == 7);
		assertTrue(statistic.getEggsHatched() == 7);
		assertTrue(statistic.getEggsPlaced() == 7);
		assertTrue(statistic.getLevelsComplete() == 7);
		assertTrue(statistic.getPackagesComplete() == 7);
		assertTrue(statistic.getPlaytime() == 7);
		assertTrue(statistic.getRecolorings() == 7);
		assertTrue(statistic.getResetsUsed() == 7);
		assertTrue(statistic.getUsedHints() == 7);
	}
	
	public void testEqualsMethod() {
		Statistic statistic1 = new Statistic();
		Statistic statistic2 = new Statistic();
		Statistic statNull = null;
		Statistic statistic3 = new Statistic(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		Statistic statistic4 = new Statistic(1, 1, 1, 1, 1, 1, 1, 1, 1, 2);
		Statistic statistic5 = new Statistic(1, 1, 1, 1, 1, 1, 1, 1, 2, 2);
		Statistic statistic6 = new Statistic(1, 1, 1, 1, 1, 1, 1, 2, 2, 2);
		Statistic statistic7 = new Statistic(1, 1, 1, 1, 1, 1, 2, 2, 2, 2);
		Statistic statistic8 = new Statistic(1, 1, 1, 1, 1, 2, 2, 2, 2, 2);
		Statistic statistic9 = new Statistic(1, 1, 1, 1, 2, 2, 2, 2, 2, 2);
		Statistic statisticA = new Statistic(1, 1, 1, 2, 2, 2, 2, 2, 2, 2);
		Statistic statisticB = new Statistic(1, 1, 2, 2, 2, 2, 2, 2, 2, 2);
		Statistic statisticC = new Statistic(1, 2, 2, 2, 2, 2, 2, 2, 2, 2);
		Statistic statisticD = new Statistic(2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
		Statistic statistic01 = new Statistic(2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
		Statistic statistic02 = new Statistic(2, 1, 2, 2, 2, 2, 2, 2, 2, 2);
		Statistic statistic03 = new Statistic(2, 2, 1, 2, 2, 2, 2, 2, 2, 2);
		Statistic statistic04 = new Statistic(2, 2, 2, 1, 2, 2, 2, 2, 2, 2);
		Statistic statistic05 = new Statistic(2, 2, 2, 2, 1, 2, 2, 2, 2, 2);
		Statistic statistic06 = new Statistic(2, 2, 2, 2, 2, 1, 2, 2, 2, 2);
		Statistic statistic07 = new Statistic(2, 2, 2, 2, 2, 2, 1, 2, 2, 2);
		Statistic statistic08 = new Statistic(2, 2, 2, 2, 2, 2, 2, 1, 2, 2);
		Statistic statistic09 = new Statistic(2, 2, 2, 2, 2, 2, 2, 2, 1, 2);
		Statistic statistic0A = new Statistic(2, 2, 2, 2, 2, 2, 2, 2, 2, 1);
		int fakeStat = 1;
		
		assertTrue(statistic1.equals(statistic1));
		assertTrue(statistic1.equals(statistic2));
		assertFalse(statistic1.equals(statNull));
		assertFalse(statistic1.equals(statistic3));
		assertFalse(statistic3.equals(statistic4));
		assertFalse(statistic3.equals(statistic5));
		assertFalse(statistic3.equals(statistic6));
		assertFalse(statistic3.equals(statistic7));
		assertFalse(statistic3.equals(statistic8));
		assertFalse(statistic3.equals(statistic9));
		assertFalse(statistic3.equals(statisticA));
		assertFalse(statistic3.equals(statisticB));
		assertFalse(statistic3.equals(statisticC));
		assertFalse(statistic3.equals(statisticD));
		assertFalse(statistic3.equals(fakeStat));
		assertFalse(statisticD.equals(statisticC));
		assertFalse(statisticD.equals(statisticB));
		assertFalse(statisticD.equals(statisticA));
		assertFalse(statisticD.equals(statistic9));
		assertFalse(statisticD.equals(statistic8));
		assertFalse(statisticD.equals(statistic7));
		assertFalse(statisticD.equals(statistic6));
		assertFalse(statisticD.equals(statistic5));
		assertFalse(statisticD.equals(statistic4));
		assertFalse(statisticD.equals(statistic3));
		assertTrue(statistic01.equals(statisticD));
		assertFalse(statistic02.equals(statisticD));
		assertFalse(statistic03.equals(statisticD));
		assertFalse(statistic04.equals(statisticD));
		assertFalse(statistic05.equals(statisticD));
		assertFalse(statistic06.equals(statisticD));
		assertFalse(statistic07.equals(statisticD));
		assertFalse(statistic08.equals(statisticD));
		assertFalse(statistic09.equals(statisticD));
		assertFalse(statistic0A.equals(statisticD));
		
	}
}
