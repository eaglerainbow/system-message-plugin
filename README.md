# system-message-plugin
This Jenkins plugin allows to show a message panel on each page viewed by a user, providing system-wide messages which can be set by a Jenkins administrator.

# Roles

## Administrator
The administrator wants to provide additional information to the users of the Jenkins server. Therefore, he makes use of the system message panel which gets shown to the users. He may enter the messages in the Jenkins configuration, which he is able to access, as he has the "administer" privilege.

## User
When logging on to the system (using a bookmarked link directly to a job), the user wants to be able to see the messages the administrator has entered before.
Before going ahead with the daily work, he wants to hide the message panel.

He is only able to read message, as he does not have the "administer" privilege to change the configuration.

# Features

## Minimal Viable Scope

- [x] Entire logic of system message panel implementation is encapsulated in the plugin (no need to know HTML/CSS, if not really wanted to)
- [x] The system message panel can be disabled entirely, without losing the message
- [ ] Jenkins-Wiki page with detailed description of plugin
- [ ] Extend Jenkins-Wiki page with documentation (what all the fields mean, when to use what etc.)

## Future Enhancements
- [ ] You may define multiple system messages, which can be enabled/disabled individually. If multiple messages are enabled, they are concatenated in the panel.
- [x] System messages have an assigned level: information (blue-ish (i) ), warning (yellow warning symbol), or severe (red stop symbol). The corresponding symbol is rendered in front of the corresponding message in the message panel.
- [x] Depending on the maximum assigned level, the system message panel renders in a different color coding: 
  * white/grey background in case of only infos
  * yellow-ish background in case of at least one warning
  * red background in case of at least one severe message. 
- [ ] You may schedule messages to be only visible during a certain period of time (use scheduling textarea as used in job scheduling).
- [ ] System messages have an expiry date (use calendar popover). After having reached that date, the system message is automatically removed from the configuration.
- [x] The "hide" link is replaced with a proper button / clickable symbol.
- [ ] Multi-Language support (system messages may need to appear in different languages, depending on the language the user has logged on) -- prio low, as in many cases administrators do not have enough time to ensure proper translation.
- [x] Once users have read a corresponding message, users may mark them as having read them. The message then is not shown anymore.
- [x] Once the user has read a single message (out of multiple one's which are shown) and marks them as having it read, the message is no longer shown.
- [ ] Users are able to review the list of current messages (in read-only mode), even after they have marked them as read by a menu option from the main menu upon request. Reading it again is not enforced.  
- [ ] Messages may be extended with link such that additional details on a message can be provided. The hyperlink may refer to an external resource (generic HTTP URL).
- [ ] JUnit tests 

# Known Issues List
- [ ] If you mark the last message of the panel as read, the panel should appear altogether (but currently does not).
      The problem is even bigger than that: Also if the last "fatal" message is removed, the theming of the panel should change as well (i.e. it should be rendered as a "warning" for instance): Solution idea could be to turn the rendering stuff into a Widget (see also http://javadoc.jenkins-ci.org/hudson/widgets/Widget.html and https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/hudson/widgets/HistoryWidget/index.jelly) and retrieve all the html rendering stuff asynchronously. 
- [ ] What shall be the behavior when clicking the "big hide" button in case that multiple single messages are shown? Possible alternatives:
   * Mark them all as read 
   * Just hide the panel
   * Don't show the message panel for the next x minutes
   * Don't show the message panel again until you logon again
- [ ] Once the final repository is reached, the feature list and the known issue list shall be moved to github projects and github issues.

# Open Tasks for which Help is Wanted

- [ ] Improve design of message panel: that still looks awkward


