package org.jenkinsci.plugins.systemmessage.model;

public enum MessageLevel {
	INFORMATION(Messages.MessageLevel_INFORMATION(), "org-jenkinsci-plugins-systemmessage-information", "images/info.png"),
	WARNING(Messages.MessageLevel_WARNING(), "org-jenkinsci-plugins-systemmessage-warning", "images/warning.png"),
	FATAL(Messages.MessageLevel_FATAL(), "org-jenkinsci-plugins-systemmessage-fatal", "images/fatal.png");
	
	private final String text;
	private final String cssClass;
	private final String iconPath;
	
	private MessageLevel(String text, String cssClass, String iconPath) {
		this.text = text;
		this.cssClass = cssClass;
		this.iconPath = iconPath;
	}

	public String getText() {
		return text;
	}

	public String getCSSClass() {
		return this.cssClass;
	}
	
	public String getIconPath() {
		return this.iconPath;
	}
}
