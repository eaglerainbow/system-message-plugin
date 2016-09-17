package org.jenkinsci.plugins.systemmessage.user;

import org.junit.Assert;

import java.util.UUID;

import org.junit.Test;
import org.jvnet.hudson.test.WithoutJenkins;

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
	}

	
}
