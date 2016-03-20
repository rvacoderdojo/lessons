Encryption - I've Got A Secret
====================================

**This code base goes along with our talk on encryption which can be found here: [Slides](https://goo.gl/nCgjfi)**

This demonstrates 3 approaches to a simple substitution cipher.  At the end of the talk we show the flaws and
vulnerabilities with the substitution cipher as well as one approach to strengthening it (rotating the substitutions).
  
The substitution rotation code is commented out in this version but can be added rather simply by uncommenting the lines in app.js
that look like this:
``` javascript
substitutions = preventRepetition(substitutions);
```

Make sure to uncomment those lines in both the encrypt and decrypt functions or it won't work.


Finally, just for fun, there's a "Matrix" style animation for the background you can play with.  To work with that
go to the bottom of the index.html file and uncomment the matrix code there.  You can play with font size and 
the message that rains down as well if you'd like.

Here's a quick description of how to play with the project and what the files do:

- app.js - The main JS code.  Take note of the "seeded" random number generator which is important to create repeatable random numbers.  It is at the bottom of the app.js
- index.html - This is the web page.  The most notable tweak in here is the ability to add a Matrix style background.    
- caesar-shift.js - The Julius Caesar cipher.  The key must be a NUMBER.
- keyword-shift.js - A cipher that shifts the letters in the alphabet using a keyword and creates holes in the alphabet.  The key must be TEXT
- randomized-key.js - A substitution cipher that completely randomizes the alphabet.  

**You can also play with this project on Thimble by remixing it:  [Thimble Remix](https://d157rqmxrxj6ey.cloudfront.net/mpegram3rd/45206)**

**Also check out [Encryption for Kids](https://www.cerias.purdue.edu/education/k-12/teaching_resources/lessons_presentations/cryptology.html)** 
It has a ton of great information on Encryption, secret messages, and other means of communication throughout history with a ton of fun exercises to do.