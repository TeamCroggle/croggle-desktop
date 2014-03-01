package de.croggle;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import de.croggle.backends.BackendHelper;
import de.croggle.backends.DesktopBackendHelper;
import de.croggle.backends.LocalizationBackend;
import de.croggle.backends.desktop.DesktopLocalizationBackend;
import de.croggle.data.LocalizationHelper;

/**
 * Android backend that initializes the central ApplicationListener.
 */
public class Main extends LwjglApplication {
	private Main() {
		super(new AlligatorApp(), "Croggle", 1024, 600, true);
	}

	public static void main(String... args) {
		BackendHelper backendHelper = new DesktopBackendHelper();
		backendHelper.set();
		LocalizationBackend locBack = new DesktopLocalizationBackend();
		LocalizationHelper.setBackend(locBack);
		new Main();
	}
}
