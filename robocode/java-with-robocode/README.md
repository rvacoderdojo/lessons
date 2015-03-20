# Intro to Java with Robocode
Robocode allows you create battle robots, place them in a virtual arena with other
Robocode bots, and let them fight it out to the finish.  Robocode is a great intro to Java coding concepts,
and allows for teaching a lot of interesting math concepts as well (coordinate plane, angles, degrees, radians,
heading, and bearing to name just a few).

## Presentations
- [Lesson 1](http://goo.gl/7ICTZ1) - Environment Setup, basic Java, and our first bot

## Building/Setup
This has been implemented as a Maven project to make it easier to build and manage in IDEs.  If you're using Eclipse
or IntelliJ/Idea just make sure you have [Maven](http://maven.apache.org/) setup on your system, and Maven plugins installed
in your IDE.

From commandline, have [Maven](http://maven.apache.org/) installed, and do a basic: ``mvn clean package``

For Robocode setup, see instructions in [Lesson 1](http://goo.gl/7ICTZ1)

### Modules
#### bot-brains
This is a collection of utility classes that are used to make it easier and more accessible for dojo ninjas to start
coding quickly with Robocode.  It houses and masks some of the complex or mundane aspects of building an intelligent
bot.  This code is too long to do in a single dojo session, so we will use it to teach concepts, but provide it in
library form to let the kid concentrate on the more interesting aspects of developing battle strategy and bots.

#### dojobots
This houses our growing collection of Robocode bots developed in the dojo.

Source code for the robots can be found in [``dojobots/src/main/java``](https://github.com/rvacoderdojo/lessons/tree/master/robocode/java-with-robocode/dojobots/src/main/java)

- [SimpleBot.java](https://github.com/rvacoderdojo/lessons/blob/master/robocode/java-with-robocode/dojobots/src/main/java/coderdojo/bots/simple/SimpleBot.java)
- [SmarterBot.java](https://github.com/rvacoderdojo/lessons/blob/master/robocode/java-with-robocode/dojobots/src/main/java/coderdojo/bots/smart/SmarterBot.java)

Note: To run these bots you will need to include the "bot-brains" jar file in your Robocode classpath somewhere.

## Robocode: Code the Best, Destroy the Rest

