package org.jenkinsci.plugins.systemmessage;

import java.util.Set;

import org.jenkinsci.plugins.systemmessage.model.MessageLevel;
import org.jenkinsci.plugins.systemmessage.model.MessageTextStrategy;
import org.jenkinsci.plugins.systemmessage.model.PlainMessageTextStrategy;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;

public class SystemMessagePanelPageDecoratorTest {

	@Rule public JenkinsRule j = new JenkinsRule();
	@LocalData
	
	@Test
	public void testAnonymousSystemMessagePanelVisible() throws Exception {
		SystemMessagePanelPageDecorator pd = new SystemMessagePanelPageDecorator();
		
		Assert.assertTrue("Panel is generated in HTML page", pd.getMessagePanelVisible());
		
		Assert.assertTrue("Panel is enabled", pd.getEnabled());
		Assert.assertEquals("Heading is as expected", "System Messages customy", pd.getHeadingText());
		
		MessageTextStrategy mts = pd.getMessageTextStrategy();
		Assert.assertNotNull("Message Text Strategy is not null", mts);
		Assert.assertTrue("Message Text Strategy is of type PlainMessageText", mts instanceof PlainMessageTextStrategy);
		
		PlainMessageTextStrategy pmts = (PlainMessageTextStrategy) mts;
		Assert.assertEquals("Plain Message Text is provided as is", "test", pmts.getPlainMessageText());
		
		Assert.assertEquals("Warning Message Level is issued", MessageLevel.WARNING, pmts.getLevel());
		Assert.assertEquals("Message Uid provided is as stored", "6dfdd89f-9ddb-42cb-9889-641a6427b2e1", pmts.getMessageUid());
		Set<String> set = pmts.getMessageUidsOnHideButton();
		Assert.assertEquals("getMessageUidsOnHideButton() must return exactly one entry", 1, set.size());
		Assert.assertTrue("returned set needs to have exactly the same Message Uid", set.contains("6dfdd89f-9ddb-42cb-9889-641a6427b2e1"));
	}

}
