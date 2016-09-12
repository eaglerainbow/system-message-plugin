package org.jenkinsci.plugins.systemmessage;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.PageDecorator;

@Extension
public class SystemMessagePanelPageDecorator extends PageDecorator {
	/* persisted attributes */
	
	private String captionText;

	
	/* transient attributes */
	// none
	
	public SystemMessagePanelPageDecorator() {
		super();
		
		this.load();
		SystemMessagePanelManager.getInstance().add(this);
	}

	@DataBoundConstructor
	public SystemMessagePanelPageDecorator(String captionText) {
		super();
		this.captionText = captionText;
	}

	@Override
	public String getDisplayName() {
		return "System Message (Panel) Plugin";
	}
	
	/* decoration takes places via the header.jelly/footer.jelly files 
	 * in the resource folder
	 */
	
}
