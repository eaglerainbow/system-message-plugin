package org.jenkinsci.plugins.systemmessage.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.*;

/**
 * defines that the text in the system message panel shall be
 * based on a set of items which shall be rendered in a table
 * @author eaglerainbow
 *
 */
public class TableMessageTextStrategy extends MessageTextStrategy {
	
	private List<PlainMessageTextStrategy> messages;
	
	public TableMessageTextStrategy() {
		this.messages = new LinkedList<>();
	}
	
	@DataBoundConstructor
	public TableMessageTextStrategy(List<PlainMessageTextStrategy> messages) {
		this.messages = messages;
	}
	
	@Override
	public boolean isDisplayable() {
		return true;
	}

	@Override
	public MessageLevel getPanelMessageLevel() {
		return MessageLevel.WARNING; // TODO DUMMY!
	}

	@Override
	public void updateOnConfigurationChange(MessageTextStrategy mtsBefore) {
		// throw new Error("Not implemented yet");
		// Nothing to do so far...
	}
	
	public List<PlainMessageTextStrategy> getMessages() {
		return Collections.unmodifiableList(this.messages);
	}
	
	@Override
	public Set<String> getMessageUidsOnHideButton() {
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
