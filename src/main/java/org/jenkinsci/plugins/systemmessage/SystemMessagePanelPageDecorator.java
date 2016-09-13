package org.jenkinsci.plugins.systemmessage;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.PageDecorator;

@Extension
public class SystemMessagePanelPageDecorator extends PageDecorator {
	/* persisted attributes */
	
	private String headingText;

	
	/* transient attributes */
	// none
	
	public SystemMessagePanelPageDecorator() {
		super();
		
		this.load();
		SystemMessagePanelManager.getInstance().add(this);
	}

	@DataBoundConstructor
	public SystemMessagePanelPageDecorator(String headingText) {
		super();
		this.headingText = headingText;
	}

	@Override
	public String getDisplayName() {
		return "System Message (Panel) Plugin";
	}
	
	/* decoration takes places via the header.jelly/footer.jelly files 
	 * in the resource folder
	 */

	public String getHeadingText() {
		return headingText;
	}

	public void setHeadingText(String headingText) {
		this.headingText = headingText;
	}
	
}
