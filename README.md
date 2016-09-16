# system-message-plugin
This Jenkins plugin allows to show a message panel on each page viewed by a user, providing system-wide messages which can be set by a Jenkins administrator.

# Feature List (Minimal Viable Scope)

* Entire logic of system message panel implementation is encapsulated in the plugin (no need to know HTML/CSS, if not really wanted to)
* The system message panel can be disabled entirely, without losing the message

# Roles

## Administrator
The administrator wants to provide additional information to the users of the Jenkins server. Therefore, he makes use of the system message panel which gets shown to the users. He may enter the messages in the Jenkins configuration, which he is able to access, as he has the "administer" privilege.

## User
When logging on to the system (using a bookmarked link directly to a job), the user wants to be able to see the messages the administrator has entered before.
Before going ahead with the daily work, he wants to hide the message panel.

He is only able to read message, as he does not have the "administer" privilege to change the configuration.

# Future Enhancements
* You may define multiple system messages, which can be enabled/disabled individually. If multiple messages are enabled, they are concatenated in the panel.
* System messages have an assigned level: information (blue-ish (i) ), warning (yellow warning symbol), or severe (red stop symbol). The corresponding symbol is rendered in front of the corresponding message in the message panel.
* Depending on the maximum assigned level, the system message panel renders in a different color coding: 
  * white/grey background in case of only infos
  * yellow-ish background in case of at least one warning
  * red background in case of at least one servere message. 
* You may schedule messages to be only visible during a certain period of time.
* System messages have an expiry date. After having reached that date, the system message is automatically removed from the configuration.
* The "hide" link is replaced with a proper button / clickable symbol.
* Multi-Language support (system messages may need to appear in different languages, depending on the language the user has logged on) -- prio low, as in many cases administrators do not have enough time to ensure proper translation.
* Once users have read a corresponding message, users may mark them as having read them. The message then is not shown anymore.
* Users are able to review the list of current messages (in read-only mode), even after they have marked them as read by a menu option from the main menu upon request. Reading it again is not enforced.  
* Messages may be extended with link such that additional details on a message can be provided. The hyperlink may refer to an external resource (generic HTTP URL).

# Further Sections
tbd

