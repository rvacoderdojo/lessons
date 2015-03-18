# Robomgr

This tool is here to assist with Robocode group battles in the dojo.  It provides a simple web interface to upload
Robocode bots and move them into the Robocode application's classpath.

## Pre-conditions:
* [NodeJS](https://nodejs.org/) or [IO.js](https://iojs.org/)
* Java with "javap" in the OS path (but you already have that if you're running Robocode)
* Configuration changes to the "config.js" (see below)

## Installation and setup
1. npm update
2. Modify config.js
    * `scratchFolder` is the landing folder when a file is uploaded. (`./landingzone` is the default)
    * `classesFolder` is the base that Robocode is looking in for Robot classes.
        * Note: This app will build up appropriate sub-folders as needed based on the packaging needs of the uploaded Robot
    * `listenPort` The port to listen on (6160 is the default)
3. Make sure both `scratchFolder` and `classesFolder` exist and match the configuration.
4. node server.js

## Usage
Access the main page at: `http://{hostname}:{listenPort}`

Upload your Robot classes by dragging and dropping them into the "hot zone" on the page, and clicking the "Teleport your bot!" button.

## Credit
* [HapiJS](http://hapijs.com) There is simply no better way to make HTTP services in Javascript.
* [Dropzone](http://www.dropzonejs.com/) made this all very easy.  Much thanks to Matias Meno
* [java-class-parser](https://github.com/villadora/java-class-parser) For parsing the compiled .class files to extract package info.

