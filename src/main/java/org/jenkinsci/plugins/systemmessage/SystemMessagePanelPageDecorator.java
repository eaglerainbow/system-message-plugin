package org.jenkinsci.plugins.systemmessage;

import org.jenkinsci.plugins.systemmessage.model.MessageTextStrategy;
import org.jenkinsci.plugins.systemmessage.model.MessageTextStrategyDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import hudson.model.PageDecorator;
import jenkins.model.*;
import net.sf.json.JSONObject;

@Extension
public class SystemMessagePanelPageDecorator extends PageDecorator {
	/* persisted attributes */

	/** main switch of the message panel */
	private boolean enabled;
	
	/** the heading of the system message panel */
	private String headingText;
	
	/** the approach how the body of the system message panel shall be rendered */
	private MessageTextStrategy messageTextStrategy;
	
	/* transient attributes */
	// none
	
	public SystemMessagePanelPageDecorator() {
		super();
		
		this.load();
		SystemMessagePanelManager.getInstance().add(this);
	}

	@DataBoundConstructor
	public SystemMessagePanelPageDecorator(String headingText, 
			MessageTextStrategy messageTextStrategy, 
			boolean enabled) {
		this();
		this.enabled = enabled;
		this.headingText = headingText;
		this.messageTextStrategy = messageTextStrategy;

	}

	@Override
	public String getDisplayName() {
		return "System Message (Panel) Plugin";
	}
	
	
	
	/* decoration takes places via the header.jelly/footer.jelly files 
	 * in the resource folder
	 */

	@Override
	public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
		boolean b = super.configure(req, json);
		
		req.bindJSON(this, json);
		
		this.save();
		SystemMessagePanelManager.getInstance().reloadAll();
		
		return b;
	}
	
	public boolean getMessagePanelVisible() {
		if (!this.getEnabled())
			return false;
		
		if (this.messageTextStrategy == null)
			return false;
		
		return this.messageTextStrategy.isDisplayable();
	}
	
	public String getMessageText() {
		if (!this.getEnabled())
			return "";
		
		if (this.messageTextStrategy == null)
			return "";
					
		return this.messageTextStrategy.getMessageText();
	}
	
	/* Getter / Setter methods */
	public String getHeadingText() {
		return headingText;
	}

	public void setHeadingText(String headingText) {
		this.headingText = headingText;
	}

	public MessageTextStrategy getMessageTextStrategy() {
		return messageTextStrategy;
	}

	public void setMessageTextStrategy(MessageTextStrategy messageTextStrategy) {
		this.messageTextStrategy = messageTextStrategy;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
	
}
