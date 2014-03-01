package de.croggle.test;

import de.croggle.backends.DesktopBackendHelper;
import de.croggle.backends.sqlite.DatabaseHelper;

public class TestBackendHelper extends DesktopBackendHelper {

	@Override
	protected DatabaseHelper instantiateDatabaseHelper() {
		return new TestDatabaseHelper();
	}
}
