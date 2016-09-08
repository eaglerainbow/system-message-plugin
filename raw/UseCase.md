# Use Case
There are installations of Jenkins servers, which serve a large user base (for example ~1000 users). Central administration groups are operating the system. Jobs are being created centrally via REST API via scripts automatically. 
Therefore, users even may not have authorizations to create/edit existing jobs on the server. Using single-sign-on techniques, these users tend to directly bookmark jobs in which they are interested. Thus, they are typically no longer logging on to the main screen of Jenkins.

The group of administrators requires to be able to provide operating information related to the status of the Jenkins server. Typically, this information is provided via messages describing events such as 
* planned downtimes, 
* planned or ongoing limitations of service, and
* known issues (or links to a list of them).

For sure the administration group may broadcast these kind of messages via mailinglist to which the user should be subscribed. However, experience has shown that users are skipping to read these kind of messages (for example due to "inbox overload") or simply may forget an announcement which was made earlier.
That is why these kind of messages (or at least brief reminders with links to further details) shall be made available from within the Jenkins server with the aim

1. to easily remind users of already communicated messages, and
2. to easily allow announcing further messages without providing the need that users correlate them to "their" Jenkins instance (i.e. if there is a landscape consisting of multiple Jenkins servers in place, then users need to check on each communication whether they are affected or not. If the messaging is provided "inline" to the Jenkins server, this matching is no longer necessary).  

By this communication and synchronization between developers (here: users of the Jenkins) and the operating crew is facilitated.

# Search for Existing Work

## System message on "Jenkins is going to shutdown"

If Jenkins is preparing to shutdown (either for "safe restart" or in "prepare for shutdown" mode), a message box is provided on top of all main pages. However, this approach lacks several aspects:

* The message is not customizable (the text is hard-coded and cannot be configured).
* The message is only shown in case the system is in a cool-down phase. To obvious reasons, system messages which are not related to an immediate shutdown of the Jenkins servers cannot be communicated to the users using this approach.

## Page Markup Plugin

The [Page Markup Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Page+Markup+Plugin) may serve the purpose.
However, as it is a very generic plugin, properly formatting the necessary HTML coding can be cumbersome. As depicted on the plugin's wiki page, there is the directory userContent/ available to the rescue, but still major parts of the entire system message panel would have to stay in the plugin's configuration.
Moreover, there is no mechanism to disable viewing the panel without removing the HTML code entirely. Again as a sort of workaround it would be possible to use HTML comments to remove the relevant sections.

All in all, the plugin would be able to serve the purpose. However, usability is expected to be bad, as the plugin is considered to be too generic for easy handling for the group of administrators.

## PageDecorator Scan

Scanning the [where-used list of the PageDecorator Extension Point](https://wiki.jenkins-ci.org/display/JENKINS/Extension+points#Extensionpoints-hudson.model.PageDecorator) led to the detection of the Page Markup Plugin as discussed above. Further plugins which may serve the same purpose as depicted above, could not be found (as of 2016-09).

## Google Search

Several searches for "jenkins plugin" plus additional terms on

* system message
* message panel

have been performed, but none of the plugins seem to properly go into the desired direction. 