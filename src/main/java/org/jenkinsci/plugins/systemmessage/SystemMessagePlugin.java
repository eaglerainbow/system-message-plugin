/**
 * System Message (Panel) Jenkins Plugin
 * 
 * This software is distributed under the MIT License; for the full text
 * of the license, see the LICENSE.txt file included with this
 * distribution. 
 * @author eaglerainbow
 */


package org.jenkinsci.plugins.systemmessage;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.systemmessage.user.UserReadSystemMessagesUserProperty;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import hudson.Plugin;
import hudson.model.*;
import hudson.util.FormValidation;


public class SystemMessagePlugin extends Plugin {
	private static transient final Logger LOGGER = Logger.getLogger(SystemMessagePlugin.class.getName());
	
	public void doMarkMessageAsRead(StaplerRequest request, StaplerResponse response) {
		/* Note
		 * Even though we did not provide any message uids in case that there is 
		 * no user logged on right now, we still need to check here, if a user has logged on.
		 * Reasons:
		 * 1. The session could have expired in the meantime (then we would even git
		 * a valid list of messageUids, but we would not know where to write them)
		 * 2. A non-logged on malicious user still could send a direct call (ignoring 
		 * the JS coding of the browser) directly to this API.
		 */
		String messageUidsParam = request.getParameter("messageuids");
		String[] messageUids = StringUtils.split(messageUidsParam, SystemMessagePanelPageDecorator.MESSAGEUID_SEPARATOR);

		// safety net only
		if (User.current() == null) {
			try {
				response.sendError(500);
				response.getOutputStream().println("No user is logged on");
			} catch (IOException e) {
				LOGGER.warning("Unable to write error message to response");
				return;
			}
		}
		
		UserReadSystemMessagesUserProperty ursmup = UserReadSystemMessagesUserProperty.getInstanceOfCurrentUser();
		if (ursmup == null) {
			// we were unable to retrieve our User Property (and also a new one could not be created)
			LOGGER.warning("Writing read messages failed because the user property could not be read/created");
			try {
				response.sendError(500);
				response.getOutputStream().println("Unable to write Message UIds to User Properties");
			} catch (IOException e) {
				LOGGER.warning("Unable to write error message to response");
				return;
			}
		}
		
		for (String muid : messageUids) {
			LOGGER.info(String.format("Marking message %s for current user as being read", muid));
			ursmup.markMessageRead(muid);
		}
		
		try {
			User.current().save();
		} catch (FormValidation e) {
			LOGGER.info("Form Validation exceptiopn on saving configuration changes of current user; ignoring");
		} catch (IOException e) {
			LOGGER.info("Unable to save configuration changes of current user; ignoring");
		}
	}
	
}
