package org.jenkinsci.plugins.systemmessage;

import java.util.LinkedList;

import javax.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class SystemMessagePanelManager extends LinkedList<SystemMessagePanelPageDecorator> {
	private static transient SystemMessagePanelManager instance;
	
	public static SystemMessagePanelManager getInstance() {
		if (instance == null)
			instance = new SystemMessagePanelManager();
		
		return instance;
	}
	
	public void reloadAll() {
		for (SystemMessagePanelPageDecorator pd : this) {
			pd.load();
		}
	}
}
