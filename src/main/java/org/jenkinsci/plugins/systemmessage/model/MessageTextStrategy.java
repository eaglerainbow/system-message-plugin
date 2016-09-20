package org.jenkinsci.plugins.systemmessage.model;

import java.util.HashSet;
import java.util.Set;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Describable;

/**
 * defines how the text in the system message panel shall be stored/rendered
 * @author eaglerainbow
 *
 */
public abstract class MessageTextStrategy<T extends MessageTextStrategy<T>> extends AbstractDescribableImpl<T>
	implements Describable<T>, ExtensionPoint {
	
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
	 * @return <code>true</code> if the system message panel will be able 
	 * to show the message text. <code>False</code> otherwise.
	 */
	public boolean isDisplayable() {
		return false;
	}

	/**
	 * provides the message level in which the system message shall be rendered (and thus its theming)
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
	public abstract void updateOnConfigurationChange(MessageTextStrategy<?> mtsBefore);
	
	/**
	 * returns the message unique ids, which shall be marked as read for the user, 
	 * in case the "hide" button of the message panel has be clicked. 
	 * @return a set of message unique ids which will be marked as read.
	 */
	public Set<String> getMessageUidsOnHideButton() {
		return new HashSet<String>();
	}
}
