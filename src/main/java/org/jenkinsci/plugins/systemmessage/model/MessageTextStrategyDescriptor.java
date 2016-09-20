package org.jenkinsci.plugins.systemmessage.model;

import java.util.List;

import com.infradna.tool.bridge_method_injector.WithBridgeMethods;

import hudson.DescriptorExtensionList;
import hudson.model.*;
import jenkins.model.Jenkins;

public class MessageTextStrategyDescriptor<T extends MessageTextStrategy<T>> extends Descriptor<T> {
    @SuppressWarnings("unchecked")
	@WithBridgeMethods(List.class)
    // public static <D extends Descriptor<T extends Describable<T>>> DescriptorExtensionList<MessageTextStrategy, D> all() {
    public static <T extends Describable<T>, D extends Descriptor<T>> DescriptorExtensionList<T,D> all() {
    	Class c = (Class) MessageTextStrategy.class;
    	
        return Jenkins.getInstance().getDescriptorList(c);
    }
    // <T extends Describable<T>, D extends Descriptor<T>>
    // public <D extends Descriptor<MessageTextStrategy<?>> DescriptorExtensionList<MessageTextStrategy,D> getDescriptorList(Class<MessageTextStrategy> type) {

	@Override
	public String getDisplayName() {
		return "dummy";
	}
    
    
}
