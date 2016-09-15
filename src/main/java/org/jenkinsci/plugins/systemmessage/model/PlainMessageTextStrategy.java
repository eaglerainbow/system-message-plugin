package org.jenkinsci.plugins.systemmessage.model;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

public class PlainMessageTextStrategy extends MessageTextStrategy {
	private String plainMessageText;

	@DataBoundConstructor
	public PlainMessageTextStrategy(String plainMessageText) {
		this.plainMessageText = plainMessageText;
	}
	
	public String getPlainMessageText() {
		return plainMessageText;
	}

	public void setPlainMessageText(String plainMessageText) {
		this.plainMessageText = plainMessageText;
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

	@Extension(ordinal = 9999)
	public static class DescriptorImpl extends MessageTextStrategyDescriptor {
		
		@Override
		public String getDisplayName() {
			return Messages.MessageTextStrategy_PLAIN_TEXT();
		}


	}
	
}
