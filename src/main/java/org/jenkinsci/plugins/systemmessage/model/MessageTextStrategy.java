package org.jenkinsci.plugins.systemmessage.model;

/**
 * defines how the text in the system message panel shall be stored/rendered
 * @author eaglerainbow
 *
 */
public enum MessageTextStrategy {
	PLAIN_TEXT(Messages.MessageTextStrategy_PLAIN_TEXT()),
	MESSAGE_TEXT_TABLE(Messages.MessageTextStrategy_MESSAGE_TEXT_TABLE());
	
	private final String description;
	
	private MessageTextStrategy(String description) {
		this.description = description;
	}
	
	/**
	 * returns the human-readable description of this strategy (i18n-enabled)
	 * @return the human-readable description as string
	 */
	public String getDescription() {
		return this.description;
	}
	
}
