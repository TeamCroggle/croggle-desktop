package de.croggle.test;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.backends.headless.mock.input.MockInput;
import com.badlogic.gdx.files.FileHandle;

import de.croggle.AlligatorApp;
import de.croggle.backends.DesktopBackendHelper;
import de.croggle.backends.desktop.DesktopLocalizationBackend;
import de.croggle.data.LocalizationHelper;

public class TestHelper {
	private static AlligatorApp app = null;
	private static DesktopLocalizationBackend localizationBackend = null;
	private static DesktopBackendHelper backendHelper = null;

	private TestHelper() {
	}

	private ApplicationType getEnvironmentType() {
		try {
			Class.forName("R");
			return ApplicationType.Android;
		} catch (ClassNotFoundException e) {
			return ApplicationType.Desktop;
		}
	}

	public static void setupAll(PlatformTestCase test) {
		setupCroggleBackends(test);
		setupGdx(test);
	}

	public static void setupGdx(PlatformTestCase test) {
		setupGdxAudio();
		setupGdxGraphics();
		setupGdxInput();
		setupGdxApp();
	}

	public static void setupGdxApp() {
		boolean wasNull = false;
		if (app == null) {
			AlligatorApp.HEADLESS = true;
			app = new AlligatorApp();
			wasNull = true;
		}
		if (Gdx.app == null) {
			new HeadlessApplication(app); // will automatically register in
											// Gdx.app
		}
		if (wasNull) {
			app.create();
		}
	}

	public static void setupGdxFiles(PlatformTestCase test) {
		if (Gdx.files == null) {
			Gdx.files = new HeadlessFiles();
		}
	}

	public static void setupGdxInput() {
		if (Gdx.input == null) {
			Gdx.input = new MockInput();
		}
	}

	public static void setupGdxAudio() {
		if (Gdx.audio == null) {
			Gdx.audio = new MockAudio();
		}
	}

	public static void setupGdxGraphics() {
		if (Gdx.graphics == null) {
			Gdx.graphics = new MockGraphics();
		}
	}

	public static AlligatorApp getApp(PlatformTestCase test) {
		if (app == null) {
			setupAll(test);
		}

		return app;
	}

	public static void setupCroggleBackends(PlatformTestCase test) {
		if (localizationBackend == null) {
			localizationBackend = new DesktopLocalizationBackend();
		}
		LocalizationHelper.setBackend(localizationBackend);

		if (backendHelper == null) {
			backendHelper = new TestBackendHelper();
		}
		backendHelper.set();
	}

	public static void deleteDatabase(PlatformTestCase test, String name) {
		if (Gdx.files == null) {
			setupGdx(test);
		}
		FileHandle db = Gdx.files.local(name);
		if (db.exists()) {
			db.delete();
		}
	}
}
