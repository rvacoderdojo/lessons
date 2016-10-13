// This is out application's configuration.
var APP_CONTEXT = {
    API_KEY : 'eFkpuNbpw70bBGCiaacNcYUFOLgP5a7PfjE9WRA0',
    NASA_URL_TEMPLATE: 'https://api.nasa.gov/mars-photos/api/v1/rovers/{{rover}}/photos?api_key={{apiKey}}&earth_date={{earthDate}}&page={{page}}'
};

// This is JQuery's way of saying "When you're done loading the page, do these other things"
$(document).ready(function(){

    loadTemplates();

    // Attach behavior to the form submit so that we can go fetch images and show them.
    $('#marsform').submit(function(event) {
        var dateVal = $('#dateField').val();
        var rover = $('#rover option:selected').val();
        $('#marspics').html("");

        loadImages(rover, dateVal, 1);

        // This stops the form from actually submitting and erasing the page.
        event.preventDefault();
    });

    // This configures the Date Picker.
    $('.datepicker').datepicker({
        format: "yyyy-mm-dd",
        autoclose : true,
        endDate : "-2d"
    });

});

// This loads the images on to the page.
function loadImages(rover, earthDate, page) {

    // Create the context variables needed to replace the values in the NASA web address.
    var urlContext = {
        rover     : rover,
        earthDate : earthDate,
        page      : page,
        apiKey    : APP_CONTEXT.API_KEY
    };

    $.ajax({
        // Creates the URL address using the URL template and the context values.
        url: parser(APP_CONTEXT.NASA_URL_TEMPLATE, urlContext),
        type: "GET" })
    // What do to when we have a successful call.
        .done(function (response) {
            // For every photo response we get back, output the picture using our picture template.
            $.each(response.photos, function( index, value ) {
                var picsContext = {
                    imgUrl      : value.img_src,
                    cameraName  : value.camera.name,
                    roverName   : value.rover.name
                };
                $('#marspics').append(parser(APP_CONTEXT.PICS_TEMPLATE, picsContext));
            });

            // If there are more photos left, (and we haven't pulled more than 5 pages worth), ask for more.
            if (response.photos.length > 0  && page < 10) {
                loadImages(rover, earthDate, page+1);
            }

        })
        // Handle issues loading data.
        .fail(function(data) {
            // Constructs the error context.
            var context = {
                errorTitle : 'Error loading pictures'
            };
            if (data.responseJSON) {
                context.message = data.responseJSON.errors;
            }
            else {
                context.message = 'Error Message: ' + data.statusText;
            }

            console.log('Could not load pictures: ' + JSON.stringify(context));

            // Then tells the page to show the errors in a fancy modal.
            $('#mymodal').html(parser(APP_CONTEXT.ERROR_MODAL_TEMPLATE, context));
            $('#error-modal').modal();

        });
}

function showImage(imgUrl, roverName, cameraName) {
    var context = {
        imgUrl      : imgUrl,
        roverName   : roverName,
        cameraName  : cameraName
    };

    $('#mymodal').html(parser(APP_CONTEXT.BIG_PHOTO_TEMPLATE, context));
    $('#photo-modal').modal();
    return false;
}

// Cheap handlebars ("{{something}}") parser.   Replaces placeholders in the template with their context values.
function parser(template, context) {

    for (contextKey in context) {
        var regExp = new RegExp('{{' + contextKey + '}}', 'g');
        var value = eval('context.'+ contextKey);
        if (value)  {
            template = template.replace(regExp, value);
        }
    }
    return template;
}

function loadTemplates() {
    // Load the HTML template for pictures.
    $.get('templates/image-template.html')
        .done(function(picTemplate) {
            APP_CONTEXT.PICS_TEMPLATE = picTemplate;
            console.log('Picture template loaded');
        })
        .fail(function() {
            console.log('Failed to load the picture template');
        });

    // Load the HTML template for the errors modal
    $.get('templates/big-photo.html')
        .done(function(photoTemplate) {
            APP_CONTEXT.BIG_PHOTO_TEMPLATE = photoTemplate;
            console.log('Modal for photos loaded');
        })
        .fail(function(){
            console.log('Failed to load the photos template');
        });

    // Load the HTML template for the photos modall
    $.get('templates/error-modal.html')
        .done(function(modalTemplate) {
            APP_CONTEXT.ERROR_MODAL_TEMPLATE = modalTemplate;
            console.log('Modal for error messages loaded');
        })
        .fail(function(){
            console.log('Failed to load the modal template');
        });


}