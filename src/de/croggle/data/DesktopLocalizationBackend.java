package de.croggle.data;

import java.util.Locale;

public class DesktopLocalizationBackend implements LocalizationBackend {

	@Override
	public String translate(String s) {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public String translate(String s, int multiplicity) {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public String[] getLocalizedStringList(String identifier) {
		// TODO Auto-generated method stub
		return new String[] { identifier };
	}

	@Override
	public void setApplicationLocale(Locale locale) {
		// TODO Auto-generated method stub

	}

	@Override
	public Locale getApplicationLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getSystemLocale() {
		return Locale.getDefault();
	}

}
