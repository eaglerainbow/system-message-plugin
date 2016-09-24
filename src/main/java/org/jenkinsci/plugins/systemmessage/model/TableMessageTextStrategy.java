package org.jenkinsci.plugins.systemmessage.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jenkinsci.plugins.systemmessage.user.UserReadSystemMessagesUserProperty;
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
		/* We need to check the list of messages, which are provided,
		 * to see if there is something displayable
		 */
		
		if (this.messages == null)
			// there are simply no messages at (even not a list of it)
			return false;
		
		if (this.messages.isEmpty())
			// no messages have been configured -- there is nothing to show
			return false;
		
		for(PlainMessageTextStrategy item : this.messages) {
			if (item.isDisplayable()) {
				return true; // there is at least one thing to show
			}
		}
		
		// all messages do not have anything to show
		return false;
	}

	@Override
	public MessageLevel getPanelMessageLevel() {
		/* the "highest" message level wins 
		 * and thus determines how to show the entire message panel
		 */
		
		if (this.messages == null || this.messages.isEmpty()) {
			// apparently there is nothing to show; so why to be critical?
			return MessageLevel.INFORMATION;
		}
		
		MessageLevel maxLevel = MessageLevel.INFORMATION;
		for (PlainMessageTextStrategy item : this.messages) {
			if (!item.isDisplayable())
				/* messages, which are not considered to be displayable
				 * shall not be taken into account for determining the
				 * theming of the message panel 
				 */
				continue;
			
			if (item.getLevel().compareTo(maxLevel) > 0) 
				maxLevel = item.getLevel();
		}
		return maxLevel;
	}

	@Override
	public void updateOnConfigurationChange(MessageTextStrategy mtsBefore) {
		// Nothing to do so far...
	}
	
	public List<PlainMessageTextStrategy> getMessages() {
		return Collections.unmodifiableList(this.messages);
	}
	
	@Override
	public Set<String> getMessageUidsOnHideButton() {
		// TODO Unclear implementation: What shall the "big hide" button do in case of TableMessageTextStrategy?
		return UserReadSystemMessagesUserProperty.EMPTY_MESSAGE_SET;
	}

	
	@Extension(ordinal = 5000)
	public static class DescriptorImpl extends MessageTextStrategyDescriptor {
		@Override
		public String getDisplayName() {
			return Messages.MessageTextStrategy_MESSAGE_TEXT_TABLE();
		}
	
	}

}
