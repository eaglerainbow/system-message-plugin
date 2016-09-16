package org.jenkinsci.plugins.systemmessage.model;

import hudson.model.AbstractDescribableImpl;

/**
 * defines how the text in the system message panel shall be stored/rendered
 * @author eaglerainbow
 *
 */
public abstract class MessageTextStrategy extends AbstractDescribableImpl<MessageTextStrategy> {
	
	/**
	 * the rendered/parsed representation of the message text that shall be shown
	 * to the user.
	 * 
	 * May contain generated HTML code.
	 * @return the text which shall be sent to the browser.
	 */
	public String getMessageText() {
		return null;
	}
	
	/**
	 * boolean indicating whether there is a message text which shall be shown 
	 * to the user and if the configuration is consistent.
	 * Note that if <code>true</code> is returned, <code>getMessageText()</code> 
	 * also must be able to provide the generated text).  
	 * @return <code>true</code> if the system message panel will be able 
	 * to show the message text. <code>False</code> otherwise.
	 */
	public boolean isDisplayable() {
		return false;
	}

	/**
	 * providesthe message level in which the system message shall be rendered (and thus its theming)
	 * @return the <code>MessageLevel</code> of the panel itself
	 */
	public abstract MessageLevel getPanelMessageLevel();

}
