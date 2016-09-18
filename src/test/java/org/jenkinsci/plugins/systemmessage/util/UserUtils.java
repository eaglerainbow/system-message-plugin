package org.jenkinsci.plugins.systemmessage.util;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

import jenkins.model.*;

public class UserUtils {
	public static void changeCurrentUser(Jenkins j, String username) {
		Authentication a = j.getUser(username).impersonate();
		SecurityContextHolder.getContext().setAuthentication(a);
	}	
}
