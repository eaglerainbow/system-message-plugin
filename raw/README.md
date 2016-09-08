This folder contains "raw material", i.e. stuff which is the foundation of the plugin itself.

The folder is not participating in the build process, but serves as depot of material, which helped to build the plugin.

## Checklist
* Write down a detailed use case for this new plugin.
* Write to [Jenkins developer discussion list](https://wiki.jenkins-ci.org/display/JENKINS/Mailing+Lists) to see if there is another plugin available.

## Additional Links

* [Before starting a new plugin](https://wiki.jenkins-ci.org/display/JENKINS/Before+starting+a+new+plugin#Beforestartinganewplugin-contributor) - Work out use case in more detail and write emails to the mailinglists mentioned on the wikipage (this is a decision matrix!)
* [Jenkins Plugin Tutorial](https://wiki.jenkins-ci.org/display/JENKINS/Plugin+tutorial)
* [Page Markup Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Page+Markup+Plugin) - Plugin which may serve as template for creating this one (this plugin is too generic and is a bit too generic/flexible for the purpose (the administrator requires to have HTML/CSS knowledge to achieve the same effect).
* [Javadoc PageDecorator Extension Point](http://javadoc.jenkins-ci.org/hudson/model/PageDecorator.html) - Extension Point of which this plugin will mainly make use.