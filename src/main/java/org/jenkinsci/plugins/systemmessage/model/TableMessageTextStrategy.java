package org.jenkinsci.plugins.systemmessage.model;

import java.util.List;
import java.util.Set;

import org.jenkinsci.plugins.systemmessage.user.UserReadSystemMessagesUserProperty;
import org.kohsuke.stapler.DataBoundConstructor;

import com.infradna.tool.bridge_method_injector.WithBridgeMethods;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import jenkins.model.Jenkins;

/**
 * defines that the text in the system message panel shall be
 * based on a set of items which shall be rendered in a table
 * @author eaglerainbow
 *
 */
@Extension
public class TableMessageTextStrategy extends MessageTextStrategy<TableMessageTextStrategy> {
	
	private List<PlainMessageTextStrategy> messages;
	
	
	public TableMessageTextStrategy() {
		throw new Error("Shall never be used");
	}
	
	@DataBoundConstructor
	public TableMessageTextStrategy(List<PlainMessageTextStrategy> messages) {
		
	}
	
	@Override
	public boolean isDisplayable() {
		return true; // TODO dummy, temporary
	}

	@Override
	public MessageLevel getPanelMessageLevel() {
		return MessageLevel.INFORMATION; // TODO Dummy, temporary
	}

	@Override
	public void updateOnConfigurationChange(MessageTextStrategy<?> mtsBefore) {
		
		// Nothing to do so far...
	}
	
	
	
	@Override
	public Set<String> getMessageUidsOnHideButton() {
		return UserReadSystemMessagesUserProperty.EMPTY_MESSAGE_SET;
	}



	@Extension(ordinal = 5000)
	public static class DescriptorImpl extends MessageTextStrategyDescriptor<TableMessageTextStrategy> {
		@Override
		public String getDisplayName() {
			return Messages.MessageTextStrategy_MESSAGE_TEXT_TABLE();
		}
		
	    @WithBridgeMethods(List.class)
	    public static DescriptorExtensionList<TableMessageTextStrategy,DescriptorImpl> all() {
	        return Jenkins.getInstance().<TableMessageTextStrategy,DescriptorImpl> getDescriptorList(TableMessageTextStrategy.class);
	    }
	
	}

}
