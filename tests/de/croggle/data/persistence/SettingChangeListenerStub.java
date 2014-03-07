package de.croggle.data.persistence;


public class SettingChangeListenerStub implements SettingChangeListener {

	private Setting lastReceivedSetting = null;

	@Override
	public void onSettingChange(Setting setting) {
		lastReceivedSetting = setting;

	}
	
	public Setting getLastReceivedSetting() {
		return lastReceivedSetting;
	}

}
