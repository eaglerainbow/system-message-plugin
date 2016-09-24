function org_jenkinsci_plugins_systemmessage_sendMarkMessageAsRead(messageuids, jenkinsRootUrl) {
	new Ajax.Request(jenkinsRootUrl + '/plugin/system-message/markMessageAsRead', { 
		method : 'post',
		parameters : {
			messageuids : messageuids
		}
	});
}

function org_jenkinsci_plugins_systemmessage_hidePanel(id, messageuids, jenkinsRootUrl) {
	if (messageuids !== '') {
		// informing that this message was read
		org_jenkinsci_plugins_systemmessage_sendMarkMessageAsRead(messageuids, jenkinsRootUrl);
		/* NB: just fire and forget: We are not checking any result,
		 * and the call also is asynchronous
		 */
	}
	
	// hide the panel
	document.getElementById(id).style.display = 'none';
}


function org_jenkinsci_plugins_systemmessage_hideSingleMessage(messageuid, jenkinsRootUrl) {
	// inform the backend that the message was read
	org_jenkinsci_plugins_systemmessage_sendMarkMessageAsRead(messageuid, jenkinsRootUrl);
	
	// remove the table row from the table
	var trid = 'message-' + messageuid; 
	$(trid).remove();
}