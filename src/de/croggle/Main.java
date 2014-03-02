package de.croggle;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.croggle.backends.BackendHelper;
import de.croggle.backends.DesktopBackendHelper;
import de.croggle.backends.LocalizationBackend;
import de.croggle.backends.desktop.DesktopLocalizationBackend;
import de.croggle.data.LocalizationHelper;

/**
 * Android backend that initializes the central ApplicationListener.
 */
public class Main extends LwjglApplication {
	private Main(LwjglApplicationConfiguration config) {
		super(new AlligatorApp(), config);
	}

	public static void main(String[] args) {
		BackendHelper backendHelper = new DesktopBackendHelper();
		backendHelper.set();
		LocalizationBackend locBack = new DesktopLocalizationBackend();
		LocalizationHelper.setBackend(locBack);
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("croggle/res/drawable-xhdpi/ic_launcher.png",
				FileType.Internal);
		config.resizable = false;
		config.title = "Croggle";
		config.useGL20 = true;
		config.width = 1024;
		config.height = 600;
		new Main(config);
	}
}
