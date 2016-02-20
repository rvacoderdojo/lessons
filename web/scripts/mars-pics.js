// This is JQuery's way of saying "When you're done loading the page, do these other things"
$(document).ready(function(){

    // Loads up each of the templates and saves them in the APP_CONTEXT
    common.templateLoader('templates/image-template.html', function(data) {
        APP_CONTEXT.PICS_TEMPLATE = data;
    });
    common.templateLoader('templates/big-photo.html', function(data) {
        APP_CONTEXT.BIG_PHOTO_TEMPLATE = data;
    });
    common.templateLoader('templates/error-modal.html', function(data) {
        APP_CONTEXT.ERROR_MODAL_TEMPLATE = data;
    });

    // Tell the search form what to do when you click "GO"
    // None of this code runs until the user "submits" the form.
    $('#marsform').submit(function(event) {
        // Save the Date value from our date picker
        var dateVal = $('#dateField').val();
        // Save the Mars Rover the user selected.
        var rover = $('#rover option:selected').val();

        // Erases any pictures we loaded previously.
        $('#marspics').html("");

        // Then using the variables from the form, go load the pictures.
        loadImages(rover, dateVal, 1);

        // This stops the form from actually submitting and erasing the page.
        event.preventDefault();
    });

});

// This loads the images on to the page.
function loadImages(rover, earthDate, page) {

    // Create the variables needed to replace the values in the NASA web address.
    var urlContext = {
        rover     : rover,
        earthDate : earthDate,
        page      : page,
        apiKey    : APP_CONTEXT.API_KEY
    };
    // Create the NASA Rover Search address using the Address template and our values above.
    var nasaRoverApiAddress = common.parser(APP_CONTEXT.NASA_ROVER_API_TEMPLATE, urlContext);

    // AJAX is the browser's way of loading data in the background and letting you still work on the web page.
    $.get(nasaRoverApiAddress)
        // What to do when we have a successful call.
        .done(function (response) {
            // For every photo response we get back, output the picture using our picture template.
            $.each(response.photos, function( index, value ) {
                var picsContext = {
                    imgUrl      : value.img_src,
                    cameraName  : value.camera.name,
                    roverName   : value.rover.name
                };
                // This will insert a "image-template.html" block of code into the
                // <div> that has the id "marspics"
                $('#marspics').append(common.parser(APP_CONTEXT.PICS_TEMPLATE, picsContext));
            });

            // If there are more photos left, (and we haven't pulled more than 5 pages worth), ask for more.
            if (response.photos.length > 0  && page < 5) {
                loadImages(rover, earthDate, page+1);
            }

        })
        // Handle issues loading data.
        .fail(showErrorModal);
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

// This gets called when you click on an image.
function showImage(imgUrl, roverName, cameraName) {
    var context = {
        imgUrl      : imgUrl,
        roverName   : roverName,
        cameraName  : cameraName
    };

    $('#mymodal').html(common.parser(APP_CONTEXT.BIG_PHOTO_TEMPLATE, context));
    $('#photo-modal').modal();
    return false;
}
