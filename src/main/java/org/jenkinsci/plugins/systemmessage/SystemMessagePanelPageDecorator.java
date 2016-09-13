package org.jenkinsci.plugins.systemmessage;

import org.jenkinsci.plugins.systemmessage.model.MessageTextStrategy;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.model.PageDecorator;
import net.sf.json.JSONObject;

@Extension
public class SystemMessagePanelPageDecorator extends PageDecorator {
	/* persisted attributes */
	
	private String headingText;
	private MessageTextStrategy messageTextStrategy;
	private String plainMessageText;
	
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
			String plainMessageText) {
		this();
		this.headingText = headingText;
		this.messageTextStrategy = messageTextStrategy;
		this.setPlainMessageText(plainMessageText);
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
		if (this.headingText != null && this.plainMessageText != null)
			return true;
		
		return false;
	}
	
	public String getMessageText() {
		return this.plainMessageText;
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

	public String getPlainMessageText() {
		return plainMessageText;
	}

	public void setPlainMessageText(String plainMessageText) {
		this.plainMessageText = plainMessageText;
	}
	
	
}
