// This is JQuery's way of saying "When you're done loading the page, do these other things"
$(document).ready(function(){

    // Loads up each of the templates and saves them in the APP_CONTEXT
    common.templateLoader('templates/apod-template.html', function(data) {
        APP_CONTEXT.PICS_TEMPLATE = data;
    });
    common.templateLoader('templates/error-modal.html', function(data) {
        APP_CONTEXT.ERROR_MODAL_TEMPLATE = data;
    });

    // Tell the search form what to do when you click "GO"
    // None of this code runs until the user "submits" the form.
    $('#apodform').submit(function(event) {
        // Save the Date value from our date picker
        var dateVal = $('#dateField').val();

        // Erases any pictures we loaded previously.
        $('#apodpic').html("");

        // Then using the variables from the form, go load the pictures.
        loadImage(dateVal);

        // This stops the form from actually submitting and erasing the page.
        event.preventDefault();
    });

});

// This loads the images on to the page.
function loadImage(earthDate) {

    // Create the variables needed to replace the values in the NASA web address.
    var urlContext = {
        earthDate : earthDate,
        apiKey    : APP_CONTEXT.API_KEY
    };
    // Create the NASA APOD search address using the Address template and our values above.
    var nasaApodApiAddress = common.parser(APP_CONTEXT.NASA_APOD_API_TEMPLATE, urlContext);

    // AJAX is the browser's way of loading data in the background and letting you still work on the web page.
    $.get(nasaApodApiAddress)
        // What to do when we have a successful call.
        .done(showImage)
        // Handle issues loading data.
        .fail(showErrorModal);
}

// This gets called when the search from NASA is successful.
// It shows the APOD image.
function showImage(response) {

    var context = {
        imgUrl      : response.url,
        title       : response.title,
        description : response.explanation
    };

    $('#apodphoto').html(common.parser(APP_CONTEXT.PICS_TEMPLATE, context));

    return false;
}

// This is our error handler.  It shows a popup (modal) with the error message.
function showErrorModal(data) {
    // Creates the error context.
    var context = {
        errorTitle : 'Error loading pictures'
    };

    if (data.responseJSON) {
        context.message = data.responseJSON.errors;
    }
    else {
        context.message = 'Error Message: ' + data.statusText;
    }

    // Writes information out to the console
    console.log('Could not load pictures: ' + JSON.stringify(context));

    // Then tells the page to show the errors in a fancy modal.
    $('#mymodal').html(common.parser(APP_CONTEXT.ERROR_MODAL_TEMPLATE, context));
    $('#error-modal').modal();

}

