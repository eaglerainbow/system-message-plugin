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

## System message on "Jenkins is going to shut down"

If Jenkins is preparing to shut down (either for "safe restart" or in "prepare for shutdown" mode), a message box is provided on top of all main pages. However, this approach lacks several aspects:

* The message is not configurable (i.e. the text is [hard-coded](https://github.com/jenkinsci/jenkins/blob/9fce1ee933eb5276baff977d562fc8e183f1c8d6/core/src/main/resources/lib/layout/main-panel.jelly#L33) and cannot be changed by the administrator).
* The message is only shown in case the system is in a cool-down phase. To obvious reasons, system messages, which are not related to an immediate shutdown of the Jenkins servers, cannot be displayed to the users using this approach.

It was considered to provide patches to [Jenkins Core](https://github.com/jenkinsci/jenkins). However, given the second aspect discussed above yields that this only would partly solve the use case (i.e. those cases, where the system message is related to a downtime).

## Welcome Message

The welcome message (see also `/configure` - welcome message) is a configuration option to show any arbitrary text. It is being displayed on the Jenkins main screen above the view tabs.
Usage of this configuration settings yielded the experience that messages written there are only read by a minor fraction of users: In most cases, frequent users of the server bookmark their jobs directly and are not using the main page with the tabs to navigate to their target. Therefore, the message is not shown to them and thus will not be read.  

## Page Markup Plugin

The [Page Markup Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Page+Markup+Plugin) may serve the purpose.
However, as it is a very generic plugin, properly formatting the necessary HTML coding can be cumbersome. As depicted on the plugin's wiki page, there is the directory `userContent/` available to the rescue, but still major parts of the entire system message panel would have to stay in the plugin's configuration.
Moreover, there is no mechanism to disable viewing the panel without removing the HTML code entirely. Again, as a sort of workaround, it would be possible to use HTML comments to remove the relevant sections.

All in all, the plugin would be able to serve the purpose. However, usability is expected to be bad, as the plugin is considered to be too generic for easy handling for the group of administrators.

Extending this plugin would be an option, but, on the other hand, this would jeopardize the generality of the approach the plugin has chosen.

## PageDecorator Scan

Scanning the [where-used list of the PageDecorator Extension Point](https://wiki.jenkins-ci.org/display/JENKINS/Extension+points#Extensionpoints-hudson.model.PageDecorator) led to the detection of the Page Markup Plugin as discussed above. Further plugins which may serve the same purpose as depicted above, could not be found (as of 2016-09).

## Discussions on mailing list

The following discussion on very similar topics has already taken place on the Jenkins mailing lists:

* [Custom login screen message](https://groups.google.com/forum/#!msg/jenkinsci-users/v8GxcZtfq60/CYzpUh_yc8gJ;context-place=searchin/jenkinsci-users/%22system$20message%22%7Csort:relevance): The question is raised whether there is an opportunity to put a message on the login page. The suggestion provided was to use the "system massage" (in fact the welcome message is meant), which then turns out to be only visible on the main page. The thread faded away without alternative or conclusion. A similar [feature request](https://issues.jenkins-ci.org/browse/JENKINS-18439) has been filed and provides some additional ideas on how to solve it. 

## Google Search

Several searches for "jenkins plugin" plus additional terms on

* system message
* message panel

have been performed, but none of the plugins seem to properly go into the desired direction. 