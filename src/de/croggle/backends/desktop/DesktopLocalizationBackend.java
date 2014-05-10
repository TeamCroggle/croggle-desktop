package de.croggle.backends.desktop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;

import de.croggle.backends.DesktopBackendHelper;
import de.croggle.backends.LocalizationBackend;
import de.croggle.util.StringUtils;

public class DesktopLocalizationBackend implements LocalizationBackend {

	HashMap<String, String> strings = null;

	private Locale appLocale;

	public DesktopLocalizationBackend() {
		appLocale = getSystemLocale();
	}

	private void initialize() {
		String stringsPath = getBestMatchingStringsFile();
		if (stringsPath == null) {
			return;
		}

		DocumentBuilder reader;
		Document stringsFile;
		try {
			reader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			stringsFile = reader.parse(new File(stringsPath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		} catch (SAXException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		NodeList stringElements = stringsFile.getElementsByTagName("string");
		strings = new HashMap<String, String>();
		for (int i = 0; i < stringElements.getLength(); i++) {
			Node n = stringElements.item(i);
			NamedNodeMap attrs = n.getAttributes();
			strings.put(
					attrs.getNamedItem("name").getTextContent(),
					StringUtils.unescape(n.getTextContent().replaceAll("\\n",
							"")));
		}
	}

	private String getBestMatchingStringsFile() {
		if (appLocale == null) {
			appLocale = getSystemLocale();
		}
		File valueFolder = new File(DesktopBackendHelper.getResourceDirPath()
				+ "values-" + appLocale.getLanguage());
		if (!valueFolder.exists() || !valueFolder.isDirectory()) {
			valueFolder = new File(DesktopBackendHelper.getResourceDirPath()
					+ "values");
			if (!valueFolder.exists() || !valueFolder.isDirectory()) {
				Gdx.app.log("DesktopLocalizationBackend",
						"Unable to find any values");
				return null;
			}
		}

		File strings = new File(valueFolder.getPath() + File.separator
				+ "strings.xml");
		if (!strings.exists() || strings.isDirectory()) {
			Gdx.app.log("DesktopLocalizationBackend",
					"No usable strings.xml found by " + strings.getPath());
			return null;
		}
		return strings.getPath();
	}

	@Override
	public String translate(String s) {
		if (strings == null) {
			initialize();
			if (strings == null) {
				return s;
			}
		}
		String result = strings.get(s);
		if (result == null) {
			return s;
		} else {
			return result;
		}
	}

	@Override
	public String translate(String s, int multiplicity) {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public String[] getLocalizedStringList(String identifier) {
		// TODO Auto-generated method stub
		return new String[] { identifier };
	}

	@Override
	public void setApplicationLocale(Locale locale) {
		appLocale = locale;
	}

	@Override
	public Locale getApplicationLocale() {
		return appLocale;
	}

	@Override
	public Locale getSystemLocale() {
		return Locale.getDefault();
	}

}
