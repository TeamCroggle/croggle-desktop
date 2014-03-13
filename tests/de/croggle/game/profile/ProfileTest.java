package de.croggle.game.profile;

import junit.framework.TestCase;

public class ProfileTest extends TestCase {

	public void testGetterAndSetter() {
		Profile profile = new Profile("test01", "test02");
		profile.setName("test01Changed");
		profile.setPicturePath("test02Changed");
		assertEquals("test01Changed", profile.getName());
		assertEquals("test02Changed", profile.getPicturePath());
	}
	
	public void testEqualsMethod() {
		Profile profile1 = new Profile("1", "2");
		Profile profile2 = new Profile("1", "2");
		Profile profile3 = new Profile("3", "4");
		Profile profile4 = new Profile("1", "4");
		int profile5 = 5;
		Profile profile6 = null;
		Profile profile7 = new Profile(null, null);
		Profile profile8 = new Profile("1", null);
		Profile profile9 = new Profile(null, "2");
		
		assertTrue(profile1.equals(profile1));
		assertTrue(profile1.equals(profile2));
		assertFalse(profile1.equals(profile3));
		assertFalse(profile1.equals(profile4));
		assertFalse(profile1.equals(profile5));
		assertFalse(profile1.equals(profile6));
		assertFalse(profile1.equals(profile7));
		assertFalse(profile1.equals(profile8));
		assertFalse(profile1.equals(profile9));
		assertFalse(profile7.equals(profile8));
		assertFalse(profile7.equals(profile9));
		assertFalse(profile8.equals(profile9));
		assertFalse(profile8.equals(profile1));
		assertFalse(profile8.equals(profile7));
	}
	
	public void testOverflowException() {
		ProfileOverflowException poe = new ProfileOverflowException("test");
	}
	
}
