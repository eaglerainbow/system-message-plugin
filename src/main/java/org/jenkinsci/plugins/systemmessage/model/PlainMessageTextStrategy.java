package org.jenkinsci.plugins.systemmessage.model;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

public class PlainMessageTextStrategy extends MessageTextStrategy {
	private String plainMessageText;
	private MessageLevel level;

	@DataBoundConstructor
	public PlainMessageTextStrategy(String plainMessageText, MessageLevel level) {
		this.plainMessageText = plainMessageText;
		this.level = level;
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

	@Extension(ordinal = 9999)
	public static class DescriptorImpl extends MessageTextStrategyDescriptor {
		
		@Override
		public String getDisplayName() {
			return Messages.MessageTextStrategy_PLAIN_TEXT();
		}


	}

	
}
