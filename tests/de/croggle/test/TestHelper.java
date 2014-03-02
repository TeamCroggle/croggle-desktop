package de.croggle.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.backends.headless.mock.input.MockInput;

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

	public static void setupAll() {
		setupCroggleBackends();
		setupGdx();
	}

	public static void setupGdx() {
		setupCroggleBackends();
		if (app == null) {
			AlligatorApp.HEADLESS = true;
			app = new AlligatorApp();
		}
		if (Gdx.app == null) {
			new HeadlessApplication(app); // will automatically register in
											// Gdx.app
		}
		setupGdxAudio();
		setupGdxGraphics();
		setupGdxInput();

		app.create();
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

	public static AlligatorApp getApp() {
		if (app == null) {
			setupAll();
		}

		return app;
	}

	public static void setupCroggleBackends() {
		if (localizationBackend == null) {
			localizationBackend = new DesktopLocalizationBackend();
			LocalizationHelper.setBackend(localizationBackend);
		}

		if (backendHelper == null) {
			backendHelper = new TestBackendHelper();
			backendHelper.set();
		}
	}
}
