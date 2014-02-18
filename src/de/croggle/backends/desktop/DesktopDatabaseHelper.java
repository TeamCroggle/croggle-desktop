package de.croggle.backends.desktop;

import de.croggle.backends.sqlite.Database;
import de.croggle.backends.sqlite.DatabaseHelper;

public class DesktopDatabaseHelper implements DatabaseHelper {

	@Override
	public Database getWritableDatabase() {
		// TODO Auto-generated method stub
		return new DesktopDatabase();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
