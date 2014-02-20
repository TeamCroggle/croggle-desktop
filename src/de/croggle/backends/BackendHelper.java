package de.croggle.backends;

import com.badlogic.gdx.Gdx;

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
public class BackendHelper {
	private BackendHelper() {
	}

	public static boolean acquireWakeLock() {
		return false;
	}

	public static boolean releaseWakeLock() {
		return false;
	}

	public static Object getContext() {
		return Gdx.app;
	}

	public static DatabaseHelper getNewDatabaseHelper() {
		return new DesktopDatabaseHelper();
	}

	public static ContentValues getNewContentValues() {
		return new DesktopContentValues();
	}

	public static String getAssetDirPath() {
		return "./croggle/assets/";
	}

	public static String getResourceDirPath() {
		return "./croggle/res/";
	}
}
