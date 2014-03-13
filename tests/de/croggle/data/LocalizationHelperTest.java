package de.croggle.data;

import static de.croggle.data.LocalizationHelper._;

import java.util.Locale;

import de.croggle.test.PlatformTestCase;
import de.croggle.test.TestHelper;

public class LocalizationHelperTest extends PlatformTestCase {

	@Override
	public void setUp() {
		TestHelper.setupCroggleBackends(this);
		LocalizationHelper.setApplicationLocale(LocalizationHelper
				.getSystemLocale());
	}

	public void testAppName() {
		String expected = "Croggle";
		String actual = _("app_name");

		assertEquals(expected, actual);
	}

	public void testPluralMissing() {
		String key = "app_name";
		String actual = _(key, 3);

		assertEquals(key, actual);
	}

	public void testSetGetLocale() {
		Locale l1 = Locale.GERMANY;
		Locale l2 = Locale.UK;

		LocalizationHelper.setApplicationLocale(l1);
		LocalizationHelper.setApplicationLocale(l2);
		assertEquals(l2, LocalizationHelper.getApplicationLocale());
	}

	public void testSystemLocale() {
		Locale sys = LocalizationHelper.getSystemLocale();
		// system locale should be default
		assertEquals(sys, LocalizationHelper.getApplicationLocale());
	}

	public void testGetLocalizedString() {
		assertNotNull(LocalizationHelper.localizedList("test"));
	}
	
	public void testExceptions() {
		LocalizationHelper.setBackend(null);
		try {
			
			LocalizationHelper.getLocalizedString("test");
			fail();
		}
		catch(RuntimeException e){
			assertTrue(true);
		}
		try {
			LocalizationHelper.getLocalizedString("test", 1);
			fail();
		}
		catch (RuntimeException e) {
			assertTrue(true);
		}
		try {
			LocalizationHelper.localizedList("test");
			fail();
		}
		catch(RuntimeException e) {
			assertTrue(true);
		}
		try {
			LocalizationHelper.getApplicationLocale();
			fail();
		}
		catch (RuntimeException e) {
			assertTrue(true);
		}
		try {
			LocalizationHelper.setApplicationLocale(null);
			fail();
		}
		catch(RuntimeException e) {
			assertTrue(true);
		}
		try {
			LocalizationHelper.getSystemLocale();
			fail();
		}
		catch(RuntimeException e) {
			assertTrue(true);
		}
	}
}
