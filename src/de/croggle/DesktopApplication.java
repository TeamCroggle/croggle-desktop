package de.croggle;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;
import com.badlogic.gdx.backends.lwjgl.LwjglPreferences;

public class DesktopApplication extends LwjglApplication {

	private final String preferencesDir;
	private final FileType preferencesLocation;

	public DesktopApplication(DesktopApplicationConfiguration config) {
		super(new AlligatorApp(), config);
		preferencesDir = config.preferencesDirectory;
		preferencesLocation = config.preferencesLocation;
	}

	Map<String, Preferences> preferences = new HashMap<String, Preferences>();

	@Override
	public Preferences getPreferences(String name) {
		if (preferences.containsKey(name)) {
			return preferences.get(name);
		} else {
			Preferences prefs = new LwjglPreferences(new LwjglFileHandle(
					new File(preferencesDir + "/" + name), preferencesLocation));
			preferences.put(name, prefs);
			return prefs;
		}
	}
}
