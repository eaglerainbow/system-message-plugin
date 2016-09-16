package org.jenkinsci.plugins.systemmessage.model;

public enum MessageLevel {
	INFORMATION(Messages.MessageLevel_INFORMATION()),
	WARNING(Messages.MessageLevel_WARNING()),
	FATAL(Messages.MessageLevel_FATAL());
	
	private final String text;
	
	private MessageLevel(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
