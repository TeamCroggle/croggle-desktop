package de.croggle.backends.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopApplicationConfiguration extends
		LwjglApplicationConfiguration {
	public String preferencesDirectory = ".prefs/";
	public FileType preferencesLocation = FileType.External;
}
