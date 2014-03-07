package de.croggle.game.profile;


public class ProfileChangeListenerStub implements ProfileChangeListener {

	private Profile lastReceivedProfile = null;

	@Override
	public void onProfileChange(Profile profile) {
		lastReceivedProfile = profile;

	}
	
	public Profile getLastReceivedProfile() {
		return lastReceivedProfile;
	}

}
