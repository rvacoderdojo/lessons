// This will hide the form, show the story and trigger
// inserting the form values into the story.
function showStory(form) {

    // Hide the form and show our story.
    swapViews();

    // After waiting 1 second (1000 milliseconds) for the transition to complete,
    // fill in the blanks in the story.
    window.setTimeout(function() {
        writeStory(form);
    }, 1000);

    return false;
}

// Fills in the story.
function writeStory(form) {

    // this will be our pointer to the next form field in the list.
    var index = 0;

    // we're just showing off now.  This will introduce
    // a small delay between adding each word so you
    // can see the words get added to the story one at a time.
    var interval = window.setInterval(function() {

        // Check if we've reached the end of the list.
        if (index < form.elements.length) {

            // Call our function to fill in the form field.
            fillIn(form.elements[index]);
            // Point our index to the next form field.
            index++;
        }
        else {
            // If we've reached the end of the list of form fields,
            // stop the interval timer from running.
            window.clearInterval(interval);
        }
    }, 20); // This 20 means 20 milliseconds.

}

// This will read the form field's name and value
// and look for an ID in the web page that matches the name
// if it finds it, it will insert the value into the tag that
// id is attached to.
function fillIn(formField) {

    // Grab the form field's name and value.
    var name = formField.name;
    var value = formField.value;

    // Try to find an HTML tag with an "id={name}"
    var placeholder = document.getElementById(name);

    // If we find one that matches, add the value and
    // add the "inserted-word" class which causes the field to animate
    if (placeholder) {
        placeholder.innerHTML = value;
        placeholder.classList.toggle('inserted-word');
    }
    // Clear the form field for us to use again later.
    formField.value='';
}

// This will show the form and hide the story, as well
// as clear out the placeholders in the story.
function showForm() {
    swapViews();

    // After waiting 1 second (1000 milliseconds) for the transition to complete,
    // fill in the blank form.
    window.setTimeout(function() {

        // Clears out the story spans, and resets the insert animation.
        var spans = document.getElementsByTagName('span');
        for (var x = 0; x < spans.length; x++) {
            var placeholder = spans[x];
            placeholder.innerHTML = '';
            placeholder.classList.toggle('inserted-word');
        }
    }, 1000);

    return false;
}

// Changes between the form view and the story view by
// applying the "storytime" class to both divs.
function swapViews() {

    // Make sure we're looking at the top of the page.
    window.scrollTo(0, 0);

    // Find the form and story by using their IDs
    var madlibForm = document.getElementById('madlib-form');
    var madlibStory = document.getElementById('madlib-story');

    // Toggle the storytime class on or off.
    madlibForm.classList.toggle('storytime');
    madlibStory.classList.toggle('storytime');
}

