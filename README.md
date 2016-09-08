# system-message-plugin
This Jenkins plugin allows to show a message panel on each page viewed by a user, providing system-wide messages which can be set by a Jenkins administrator.

# Feature List (Minimal Viable Scope)

* Entire logic of system message panel implementation is encapsulated in the plugin (no need to know HTML/CSS, if not really wanted to)
* The system message panel can be disabled entirely, without losing the message

# Future Enhancements
* You may define multiple system messages, which can be enabled/disabled individually. If multiple messages are enabled, they are concatenated in the panel.
* You may schedule messages to be only visible during a certain period of time.
* Multi-Language support (system messages may need to appear in different languages, depending on the language the user has logged on) -- prio low, as in many cases administrators do not have enough time to ensure proper translation.

# Further Sections
tbd

