package org.jenkinsci.plugins.systemmessage.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import net.sf.json.JSONObject;

public class PlainMessageTextStrategy extends MessageTextStrategy {
	private String plainMessageText;
	private MessageLevel level;
	private UUID messageUid;

	@DataBoundConstructor
	public PlainMessageTextStrategy(String plainMessageText, 
			MessageLevel level, 
			String messageUid) {
		this.plainMessageText = plainMessageText;
		this.level = level;
		if (messageUid != null) {
			// same Uid on write
			this.messageUid = UUID.fromString(messageUid);
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
	public String getMessageText() {
		return this.getPlainMessageText();
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
		
		return true;
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
