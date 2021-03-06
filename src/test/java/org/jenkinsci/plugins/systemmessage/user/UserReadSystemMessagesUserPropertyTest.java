package org.jenkinsci.plugins.systemmessage.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.WithoutJenkins;
import org.jvnet.hudson.test.recipes.LocalData;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jenkinsci.plugins.systemmessage.util.UserUtils;

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
		UserUtils.changeCurrentUser(this.j.getInstance(), "tester");
		
		UserReadSystemMessagesUserProperty ursmup = UserReadSystemMessagesUserProperty.getInstanceOfCurrentUser();
		Assert.assertNotNull("Ensure that instance is returned", ursmup);
		
		Set<String> msgs = ursmup.getReadMessages();
		Assert.assertEquals("Check that after cleanup no message is marked by default", 0, msgs.size());
	}
		
	@Test
	@LocalData
	public void testUserConfigurationExceptionFree() throws Exception {
		HtmlPage page = j.createWebClient().goTo("user/tester/configure");
		HtmlElement body = page.getBody();
		String bodyString = body.toString();
		Assert.assertFalse("Exception shall not be mentioned in the body", bodyString.contains("Exception"));
	}
}
