package org.jenkinsci.plugins.systemmessage.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.WithoutJenkins;
import org.jvnet.hudson.test.recipes.LocalData;

import hudson.ExtensionList;
import hudson.model.Descriptor;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;

public class UserReadSystemMessagesUserPropertyTest {

	public final static transient UUID TESTUUID = UUID.randomUUID();
	
	@Test
	@WithoutJenkins
	public void testMarkMessageRead() throws Exception {
		UserReadSystemMessagesUserProperty subject = new UserReadSystemMessagesUserProperty(UserReadSystemMessagesUserProperty.EMPTY_MESSAGE_SET);
		
		subject.markMessageRead(TESTUUID.toString());
	}
	
	@Test
	@WithoutJenkins
	public void testIsMessageRead() throws Exception {
		UserReadSystemMessagesUserProperty subject = new UserReadSystemMessagesUserProperty(UserReadSystemMessagesUserProperty.EMPTY_MESSAGE_SET);
		
		subject.markMessageRead(TESTUUID.toString());
		
		Assert.assertTrue(subject.isMessageRead(TESTUUID.toString()));
		Assert.assertFalse(subject.isMessageRead("01234"));
		
		Set<String> msgs = subject.getReadMessages();
		Assert.assertEquals("Check that exactly one message is marked as read", 1, msgs.size());
	}

	@Test
	@WithoutJenkins
	public void testCleanupMessageRead() throws Exception {
		UserReadSystemMessagesUserProperty subject = new UserReadSystemMessagesUserProperty(UserReadSystemMessagesUserProperty.EMPTY_MESSAGE_SET);
		
		subject.markMessageRead(TESTUUID.toString());
		
		Assert.assertTrue(subject.isMessageRead(TESTUUID.toString()));
		
		Set<String> msgs = subject.getReadMessages();
		Assert.assertEquals("Check that exactly one message is marked as read", 1, msgs.size());
		
		subject.cleanupMessageRead(TESTUUID.toString());
		
		msgs = subject.getReadMessages();
		Assert.assertEquals("Check that after cleanup no message is marked as read anymore", 0, msgs.size());
	}
	
	@Test
	@WithoutJenkins
	public void testInitializingWithValue() throws Exception {
		HashSet<String> initial = new HashSet<String>();
		initial.add(TESTUUID.toString());
		
		UserReadSystemMessagesUserProperty subject = new UserReadSystemMessagesUserProperty(initial);
		Assert.assertTrue(subject.isMessageRead(TESTUUID.toString()));
		
		Set<String> msgs = subject.getReadMessages();
		Assert.assertEquals("Check that exactly one message is marked as read", 1, msgs.size());
	}
	
	
	@Rule public JenkinsRule j = new JenkinsRule();
	
	@Test
	@LocalData
	public void testGetInstanceOfCurrentUser() throws Exception {
		Authentication a = j.getInstance().getUser("tester").impersonate();
		SecurityContextHolder.getContext().setAuthentication(a);
		
		UserReadSystemMessagesUserProperty ursmup = UserReadSystemMessagesUserProperty.getInstanceOfCurrentUser();
		Assert.assertNotNull("Ensure that instance is returned", ursmup);
		
		Set<String> msgs = ursmup.getReadMessages();
		Assert.assertEquals("Check that after cleanup no message is marked by default", 0, msgs.size());
	}
}
