package de.croggle;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import de.croggle.data.DesktopLocalizationBackend;
import de.croggle.data.LocalizationBackend;
import de.croggle.data.LocalizationHelper;

/**
 * Android backend that initializes the central ApplicationListener.
 */
public class Main extends LwjglApplication {
	private Main() {
		super(new AlligatorApp(), "Croggle", 1024, 600, true);
	}

	public static void main(String... args) {
		LocalizationBackend locBack = new DesktopLocalizationBackend();
		LocalizationHelper.setBackend(locBack);
		new Main();
	}
}
