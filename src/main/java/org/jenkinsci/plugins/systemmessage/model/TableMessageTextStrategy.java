package org.jenkinsci.plugins.systemmessage.model;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

/**
 * defines that the text in the system message panel shall be
 * based on a set of items which shall be rendered in a table
 * @author eaglerainbow
 *
 */
public class TableMessageTextStrategy extends MessageTextStrategy {
	
	@DataBoundConstructor
	public TableMessageTextStrategy() {
		
	}
	
	@Override
	public String getMessageText() {
		throw new Error("Not implemented yet");
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

	@Override
	public MessageLevel getPanelMessageLevel() {
		throw new Error("Not implemented yet");
	}

	@Override
	public void updateOnConfigurationChange(MessageTextStrategy mtsBefore) {
		throw new Error("Not implemented yet");
	}
	
	@Extension(ordinal = 5000)
	public static class DescriptorImpl extends MessageTextStrategyDescriptor {
		@Override
		public String getDisplayName() {
			return Messages.MessageTextStrategy_MESSAGE_TEXT_TABLE();
		}
	
	}

}
