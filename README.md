# system-message-plugin
This Jenkins plugin allows to show a message panel on each page viewed by a user, providing system-wide messages which can be set by a Jenkins administrator.

# Feature List (Minimal Viable Scope)

* Entire logic of system message panel implementation is encapsulated in the plugin (no need to know HTML/CSS, if not really wanted to)
* The system message panel can be disabled entirely, without losing the message

# Future Enhancements
* You may define multiple system messages, which can be enabled/disabled individually. If multiple messages are enabled, they are concatenated in the panel.
* System messages have an assigned level: information (blue-ish (i) ), warning (yellow warning symbol), or severe (red stop symbol). The corresponding symbol is rendered infront of the corresponding message in the message panel.
* Depending on the maximum assigned level, the system message panel renders in a different color coding: 
  * white/grey background in case of only infos
  * yellow-ish background in case of at least one warning
  * red background in case of at least one servere message. 
* You may schedule messages to be only visible during a certain period of time.
* System messages have an expiry date. After having reached that date, the system message is automatically removed from the configuration.
* The "hide" link is replaced with a proper button / clickable symbol.
* Multi-Language support (system messages may need to appear in different languages, depending on the language the user has logged on) -- prio low, as in many cases administrators do not have enough time to ensure proper translation.

# Further Sections
tbd

