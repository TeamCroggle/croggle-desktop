package de.croggle.backends;

import de.croggle.backends.desktop.DesktopContentValues;
import de.croggle.backends.desktop.DesktopDatabaseHelper;
import de.croggle.backends.sqlite.ContentValues;
import de.croggle.backends.sqlite.DatabaseHelper;

/**
 * A class to help with additional capabilities of the different backends,
 * without directly referencing them (using reflection). By doing so, the helper
 * can be part of every platform's build without pulling in dependencies to the
 * different backends.
 * 
 * The class's methods degrade gracefully, meaning that they silently ignore if
 * a certain functionality is currently unavailable.
 */
public class DesktopBackendHelper extends BackendHelper {
	public DesktopBackendHelper() {
	}

	public static String getResourceDirPath() {
		return "./croggle/res/";
	}

	@Override
	protected boolean wakelockAcquire() {
		return false;
	}

	@Override
	protected boolean wakelockRelease() {
		return false;
	}

	@Override
	protected DatabaseHelper instantiateDatabaseHelper() {
		return new DesktopDatabaseHelper();
	}

	@Override
	protected ContentValues instantiateContentValues() {
		return new DesktopContentValues();
	}

	@Override
	protected String assetDirPath() {
		return "./croggle/assets/";
	}
}
