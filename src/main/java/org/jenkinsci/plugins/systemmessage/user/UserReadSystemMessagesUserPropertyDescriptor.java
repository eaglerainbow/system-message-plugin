package org.jenkinsci.plugins.systemmessage.user;

import hudson.Extension;
import hudson.model.*;

@Extension
public class UserReadSystemMessagesUserPropertyDescriptor extends UserPropertyDescriptor  {

	public UserReadSystemMessagesUserPropertyDescriptor() {
		super(UserReadSystemMessagesUserProperty.class);
	}

	@Override
	public UserProperty newInstance(User user) {
		UserReadSystemMessagesUserProperty usrsmup = new UserReadSystemMessagesUserProperty(
				user
			);
		
		return usrsmup;
	}

	@Override
	public String getDisplayName() {
		return "User Property storing already read system messages of a user";
	}


}
