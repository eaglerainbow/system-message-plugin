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

	/**
	 * called by the framework in case a configuration change has occurred.
	 * 
	 * Note that before this method is called, the <code>equals</code> method is called
	 * on the instances before and after the configuration changes. Therefore, the
	 * descendant of this class needs to properly implement <code>equals</code> properly -
	 * and that even for the case where the comparing instance is not of the same type! 
	 * @param mtsBefore the instance with the values before the configuration change had happened
	 */
	public abstract void updateOnConfigurationChange(MessageTextStrategy mtsBefore);
	
}
