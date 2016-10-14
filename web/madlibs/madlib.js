function showStory(form) {

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
    for (var x = 0; x < form.elements.length; x++) {
        fillIn(form.elements[x]);
    }
}

function fillIn(formField) {
    var name = formField.name;
    var value = formField.value;
    var placeholder = document.getElementById(name);

    if (placeholder) {
        placeholder.innerHTML = value;
        placeholder.classList.toggle('inserted-word');
        formField.value='';
    }
}

function showForm() {
    swapViews();

    // After waiting 1 second (1000 milliseconds) for the transition to complete,
    // fill in the blank form.
    window.setTimeout(function() {
        // Clears out the spans, and resets the insert animation.
        var spans = document.getElementsByTagName('span');
        for (var x = 0; x < spans.length; x++) {
            var placeholder = spans[x];
            placeholder.innerHTML = '';
            placeholder.classList.toggle('inserted-word');
        }
    }, 1000);

    return false;
}

// Changes between the two divs.
function swapViews() {
    var madlibForm = document.getElementById('madlib-form');
    var madlibStory = document.getElementById('madlib-story');

    madlibForm.classList.toggle('storytime');

    madlibStory.classList.toggle('storytime');
}

