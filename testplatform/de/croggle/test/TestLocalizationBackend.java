package de.croggle.test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.croggle.backends.LocalizationBackend;

public class TestLocalizationBackend implements LocalizationBackend {

	private Locale c;

	private final Map<String, String[]> arrays;
	private final Map<String, String> strings;
	private final Map<String, Map<Quantity, String>> quantities;

	public TestLocalizationBackend() {
		this.c = Locale.getDefault();
		arrays = new HashMap<String, String[]>();
		strings = new HashMap<String, String>();
		quantities = new HashMap<String, Map<Quantity, String>>();
	}

	public void putString(String key, String val) {
		strings.put(key, val);
	}

	public void putArray(String key, String[] val) {
		arrays.put(key, val);
	}

	public void putQuantities(String key, Map<Quantity, String> val) {
		quantities.put(key, val);
	}

	@Override
	public String translate(String s) {
		return strings.get(s);
	}

	@Override
	public String translate(String s, int quantity) {
		Map<Quantity, String> stringQuantities = quantities.get(s);
		if (stringQuantities != null) {
			return stringQuantities.get(numberToQuantity(quantity));
		} else {
			return s;
		}
	}

	@Override
	public String[] getLocalizedStringList(String identifier) {
		return arrays.get(identifier);
	}

	@Override
	public void setApplicationLocale(Locale locale) {
		this.c = locale;
	}

	@Override
	public Locale getApplicationLocale() {
		return c;
	}

	@Override
	public Locale getSystemLocale() {
		return Locale.getDefault();
	}

	private Quantity numberToQuantity(int num) {
		final int maxFew = 19;
		if (num < 0) {
			return Quantity.OTHER;
		} else if (num == 0) {
			return Quantity.ZERO;
		} else if (num == 1) {
			return Quantity.ONE;
		} else if (num <= maxFew) {
			return Quantity.FEW;
		} else if (num > maxFew) {
			return Quantity.MANY;
		}

		return Quantity.OTHER;
	}

	public static enum Quantity {
		ZERO, ONE, TWO, FEW, MANY, OTHER
	}
}
