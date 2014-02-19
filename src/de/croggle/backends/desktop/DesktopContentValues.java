package de.croggle.backends.desktop;

import java.util.HashMap;

import de.croggle.backends.sqlite.ContentValues;

public class DesktopContentValues implements ContentValues {

	final HashMap<String, Object> values;

	public DesktopContentValues() {
		values = new HashMap<String, Object>();
	}

	@Override
	public void put(String key, float value) {
		values.put(key, value);
	}

	@Override
	public void put(String key, String value) {
		values.put(key, value);
	}

	@Override
	public void put(String key, int value) {
		values.put(key, value);
	}

	@Override
	public void put(String key, boolean value) {
		values.put(key, value);
	}
}
