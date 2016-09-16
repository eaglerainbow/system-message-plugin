package org.jenkinsci.plugins.systemmessage.model;

import hudson.DescriptorExtensionList;
import hudson.model.*;
import jenkins.model.*;

import java.util.List;

import com.infradna.tool.bridge_method_injector.WithBridgeMethods;

public abstract class MessageTextStrategyDescriptor extends Descriptor<MessageTextStrategy> {
	
    @WithBridgeMethods(List.class)
    public static DescriptorExtensionList<MessageTextStrategy,MessageTextStrategyDescriptor> all() {
        return Jenkins.getInstance().<MessageTextStrategy,MessageTextStrategyDescriptor> getDescriptorList(MessageTextStrategy.class);
    }
}
