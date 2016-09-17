function org_jenkinsci_plugins_systemmessage_hidePanel(id, messageuids, jenkinsRootUrl) {
	if (messageuids !== '') {
		// informing that this message was read
		new Ajax.Request(jenkinsRootUrl + '/plugin/system-message/markMessageAsRead', { 
			method : 'post',
			parameters : {
				messageuids : messageuids
			}
		});
		/* NB: just fire and forget: We are not checking any result,
		 * and the call also is asynchronous
		 */
	}
	
	// hide the panel
	document.getElementById(id).style.display = 'none';

}
