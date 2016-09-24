package org.jenkinsci.plugins.systemmessage.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jenkinsci.plugins.systemmessage.user.UserReadSystemMessagesUserProperty;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import net.sf.json.JSONObject;

import java.util.logging.Logger;

public class PlainMessageTextStrategy extends MessageTextStrategy {
	private String plainMessageText;
	private MessageLevel level;
	private UUID messageUid;

	private static final transient Logger LOGGER = Logger.getLogger(PlainMessageTextStrategy.class.toString());
		
	@DataBoundConstructor
	public PlainMessageTextStrategy(String plainMessageText, 
			MessageLevel level, 
			String messageUid) {
		this.plainMessageText = plainMessageText;
		this.level = level;
		if (messageUid != null) {
			if ("".equals(messageUid)) {
				/* This case may happen, if the plugin was just installed freshly,
				 * i.e. there also is no config file in place before.
				 * Therefore, there is no UUID provided before.
				 * On pressing the "save" button at /configure, we will 
				 * get back a UUID with an empty string
				 */
				this.messageUid = UUID.randomUUID();
			} else {
				// same Uid on write
				this.messageUid = UUID.fromString(messageUid);
			}
		} else {
			// if no messageUid was provided, we need to generate a new one
			// typical usecase: Strategy was not configured before and now is configured the first time
			this.messageUid = UUID.randomUUID();
		}
	}
	
	public String getPlainMessageText() {
		return plainMessageText;
	}

	public void setPlainMessageText(String plainMessageText) {
		this.plainMessageText = plainMessageText;
	}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(MessageLevel level) {
		this.level = level;
	}
	
	public String getMessageUid() {
		return messageUid.toString();
	}
	
	@Override
	public boolean isDisplayable() {
		String text = this.getPlainMessageText();
		
		if (text == null) {
			return false;
		}
					
		if ("".equals(text.trim())) {
			return false;
		}
		
		// check that the user has not read the message(s) yet
		boolean isRead = this.isMessageRead();
		if (isRead) {
			LOGGER.finer(String.format("Not showing system message panel, as message %s already is marked as read", this.messageUid.toString()));
		}
		return !isRead;
	}

	private boolean isMessageRead() {
		UserReadSystemMessagesUserProperty ursmur = UserReadSystemMessagesUserProperty.getInstanceOfCurrentUser();
		if (ursmur == null) {
			return false;
		}
		
		return ursmur.isMessageRead(this.messageUid.toString());
	}

	@Override
	public MessageLevel getPanelMessageLevel() {
		// as we only have one single message with one level,
		// the message level of the panel equals to the level
		// of the single message => trivial
		return this.getLevel();
	}

	@Override
	public void updateOnConfigurationChange(MessageTextStrategy mtsBefore) {
		// draw a new UUID
		this.messageUid = UUID.randomUUID();
		/* NB: That holds true, even if mtsBefore is not of type PlainMessageTextStrategy
		 * but yet something different...
		 */
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlainMessageTextStrategy)) {
			return false;
		}
		
		PlainMessageTextStrategy other = (PlainMessageTextStrategy) obj;
		if (!this.level.equals(other.level))
			return false;
		
		if (!this.plainMessageText.equals(other.plainMessageText))
			return false;
		
		if (!this.messageUid.equals(other.messageUid))
			return false;
		
		return true;
	}
	
	@Override
	public Set<String> getMessageUidsOnHideButton() {
		HashSet<String> myMessageUid = new HashSet<String>();
		myMessageUid.add(this.messageUid.toString());
		
		return myMessageUid;
	}



	@Extension(ordinal = 9999)
	public static class DescriptorImpl extends MessageTextStrategyDescriptor {
		
		@Override
		public String getDisplayName() {
			return Messages.MessageTextStrategy_PLAIN_TEXT();
		}

		@Override
		public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
			// TODO Auto-generated method stub
			return super.configure(req, json);
		}
	}

}
