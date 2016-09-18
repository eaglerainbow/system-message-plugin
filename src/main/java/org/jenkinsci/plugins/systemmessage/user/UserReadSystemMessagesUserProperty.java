package org.jenkinsci.plugins.systemmessage.user;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;
import jenkins.model.*;

/**
 * (invisible) storage of the system messages which the user has already read
 * @author eaglerainbow
 */
public class UserReadSystemMessagesUserProperty extends UserProperty {
	public final static transient Set<String> EMPTY_MESSAGE_SET = new HashSet<String>();
	
	private HashSet<String> readMessages;

	@DataBoundConstructor
	public UserReadSystemMessagesUserProperty(Set<String> readMessages) {
		this.readMessages = new HashSet<String>(readMessages);
	}
	
	/**
	 * retrieve the entire set of all message unique identifiers
	 * the user has already marked as read
	 * @return the set of unique id of messages the user has marked as read before
	 */
	public Set<String> getReadMessages() {
		return readMessages;
	}

	/**
	 * checks a unique id of a system message, whether the user has already read it or not
	 * @param messageUid the unique id of the message, which shall be checked
	 * @return <code>true</code> if the message was read, <code>false</code> otherwise
	 */
	public boolean isMessageRead(String messageUid) {
		return this.readMessages.contains(messageUid);
	}

	/**
	 * marks a given message (identified by its unique id)
	 * as being read by the user.
	 * @param messageUid the unique id of the message which shall be marked as being read
	 */
	public void markMessageRead(String messageUid) {
		this.readMessages.add(messageUid);
	}
	
	/**
	 * clean up a given message (identified by its unique id)
	 * after the message's validity has expired (and therefore the data about
	 * having it read or not is not applicable anymore) 
	 * @param messageUid the unique id of the message which was just deleted before.
	 */
	public void cleanupMessageRead(String messageUid) {
		this.readMessages.remove(messageUid);
	}
	
	@Extension
	public static class DescriptorImpl extends UserPropertyDescriptor {

		@Override
		public UserProperty newInstance(User user) {
			UserReadSystemMessagesUserProperty usrsmup = new UserReadSystemMessagesUserProperty(
					EMPTY_MESSAGE_SET
				);
			usrsmup.user = user;
			
			return usrsmup;
		}

		@Override
		public String getDisplayName() {
			return "User Property storing already read System Messages of a user";
		}
		
	}
	
	/**
	 * returns the instance of the user currently logged on
	 * @return the instance of UserReadSysteMessagesUserProperty of the user which is currently logged on 
	 */
	public static UserReadSystemMessagesUserProperty getInstanceOfCurrentUser() {
		User me = User.current();
		
		if (me == null) {
			// no user is logged on yet / anonymous user or security is not enabled in general
			return null;
		}
		
		UserReadSystemMessagesUserProperty ursmup = me.getProperty(UserReadSystemMessagesUserProperty.class);
		if (ursmup == null) {
			// User Property does not exist yet for the user
			ursmup = new UserReadSystemMessagesUserProperty(EMPTY_MESSAGE_SET);
			try {
				me.addProperty(ursmup);
				me.save();
			} catch (IOException e) {
				return null;
			}
		}
		
		return ursmup;
	}
}
