package org.jenkinsci.plugins.systemmessage.model;

public enum MessageLevel {
	INFORMATION(Messages.MessageLevel_INFORMATION(), "org-jenkinsci-plugins-systemmessage-information"),
	WARNING(Messages.MessageLevel_WARNING(), "org-jenkinsci-plugins-systemmessage-warning"),
	FATAL(Messages.MessageLevel_FATAL(), "org-jenkinsci-plugins-systemmessage-fatal");
	
	private final String text;
	private final String cssClass;
	
	private MessageLevel(String text, String cssClass) {
		this.text = text;
		this.cssClass = cssClass;
	}

	public String getText() {
		return text;
	}

	public String getCSSClass() {
		return this.cssClass;
	}
}
