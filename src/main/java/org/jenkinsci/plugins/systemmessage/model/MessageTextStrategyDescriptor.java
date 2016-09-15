package org.jenkinsci.plugins.systemmessage.model;

import hudson.model.*;
import org.jenkinsci.plugins.systemmessage.model.Messages;

public class MessageTextStrategyDescriptor extends Descriptor<MessageTextStrategy> {

	@Override
	public String getDisplayName() {
		return Messages.MessageTextStrategy_ABSTRACT();
	}

}
