package de.croggle;

import com.badlogic.gdx.Files.FileType;

import de.croggle.backends.BackendHelper;
import de.croggle.backends.DesktopBackendHelper;
import de.croggle.backends.LocalizationBackend;
import de.croggle.backends.desktop.DesktopApplication;
import de.croggle.backends.desktop.DesktopApplicationConfiguration;
import de.croggle.backends.desktop.DesktopLocalizationBackend;
import de.croggle.data.LocalizationHelper;

/**
 * Android backend that initializes the central ApplicationListener.
 */
public class Main {

	public static void main(String[] args) {
		BackendHelper backendHelper = new DesktopBackendHelper();
		backendHelper.set();
		LocalizationBackend locBack = new DesktopLocalizationBackend();
		LocalizationHelper.setBackend(locBack);
		DesktopApplicationConfiguration config = new DesktopApplicationConfiguration();
		config.addIcon("croggle/res/drawable-xhdpi/ic_launcher.png",
				FileType.Internal);
		config.resizable = false;
		config.title = "Croggle";
		config.useGL20 = true;
		config.width = 1024;
		config.height = 600;
		config.preferencesDirectory = ".config";
		config.preferencesLocation = FileType.Local;
		new DesktopApplication(config);
	}
}
