package org.jenkinsci.plugins.systemmessage;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import hudson.Plugin;

public class SystemMessagePluginTest {
	@Rule public JenkinsRule j = new JenkinsRule();
	
//	@Test
//	public void testDoMarkMessageAsRead() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testPluginIsRunning() {
		Plugin plugin = j.getInstance().getPlugin("system-message");
		Assert.assertTrue("Check that the plugin is enabled", plugin.getWrapper().isEnabled());
		Assert.assertTrue("Check that the plugin is active", plugin.getWrapper().isActive());
	}

}
