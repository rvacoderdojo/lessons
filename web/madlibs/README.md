MadLibs
=========================

This project was created to allow us to talk about the Document Object Model (DOM) and HTML Forms.  It presents
the user with an input form asking for various words.  After the user hits submit a story is shown where
the words get inserted into the story.  

The app does this by looking for "<span>" tags with an "id" that is the same as the input form's field.

There's some fun stuff with CSS animations in here as well, so if you're curious about that type of
thing you will find fully commented code that explains how  the animations work.

Files:
-------------------
* index.html : This is the core HTML file.  It contains both the input form and the MadLib story.
* madlib.js : This contains the Javascript that uses the DOM to insert the form values into the story.
* style.css : This contains most of the styling for the page.
* animation.css : This is a stylesheet with some CSS animation examples.
  
