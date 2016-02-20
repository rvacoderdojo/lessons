Exploring NASA's APIs
=========================

The intent of this project was make something fun to play with, while trying to introduce the concepts for Web APIs and 
JSON (JavaScript Object Notation).  Those concepts end up requiring a little more advanced knowledge of the web to explain.

The code has been thoroughly commented, but is not really for beginners.

Here's a quick description of how to play with the project and what the files do:

- index.html - This is a simple set of links to navigate to the 2 Nasa API pages.    
- mars-rovers.html - This page will use the NASA Mars Rover APIs to show you all the pictures taken by a rover on a given date.
- apod.html - This page will show you the Astronomy Picture of the Day for a given date.

Folders/Files:
-------------------
* [Folder] /images: These are the background images for the pages.
* [Folder] /scripts: These are the Javascript files for the two projects.
    * apod.js : The Astronomy Picture Of the Day javascript code
    * common-stuff.js : Common code shared by both pages.
    * mars-pics.js : Code for the Mars Rover page  
* [Folder] /styles: CSS for the pages. 
* [Folder] /templates: Both the APOD and Mars Rover pages use a simple "template" feature that can read in HTML, and replace placeholder values with variable values.  So for example: <b>Hello {{name}}</b> could become <b>Hello Bob</b> if the variable "name" = "Bob"
    * apod-template.html : The APOD picture template with title, image, and description
    * big-photo.html : The template used to show a zoomed in version of the Mars Rover photos in a modal dialog
    * error-modal.html : A modal dialog to show error messages.
    * image-template.html : The template used for each of the thumbnails on the Mars Rover page.
  
