// This is Javascript's way of creating a name that we can attach stuff to.
// We are going to attach some common data and functions to the name "common"
// then we can use them in our application by saying something like:
// common.parser();
var common = {
    // This is out application's configuration context
    context : {
        API_KEY : 'eFkpuNbpw70bBGCiaacNcYUFOLgP5a7PfjE9WRA0',
        NASA_ROVER_API_TEMPLATE : 'https://api.nasa.gov/mars-photos/api/v1/rovers/{{rover}}/photos?api_key={{apiKey}}&earth_date={{earthDate}}&page={{page}}',
        NASA_APOD_API_TEMPLATE : 'https://api.nasa.gov/planetary/apod?api_key={{apiKey}}&date={{earthDate}}'
    },

    // This will replace variable placeholders that look like
    // this: {{variableName}}
    // with the value assigned to that variable in the "context"
    // Example: if context.myName = "Bob"
    //          and the template has: Hello {{myName}}
    //          the result will be "Hello Bob"
    parser : function(template, context) {

        // This loops through each of the variables in the context
        // and substitutes any of the {{variableName}} markers with the value of the variable.
        for (contextKey in context) {
            var regExp = new RegExp('{{' + contextKey + '}}', 'g');
            var value = eval('context.' + contextKey);
            if (value) {
                template = template.replace(regExp, value);
            }
        }
        return template;
    },

    // This is a utility method to read in a template file.
    // A template file is just an HTML file that has our special variable
    // markers in it so they can be replaced.   So for example a file might have
    // something like this:
    //     <b>Hello {{myName}}</b>
    templateLoader : function(url, successCallback, failureCallback) {
        $.get(url) // Load the template
            // If successful run the function below.
            .done(function(templateData) {
                console.log('Template: ' + url + ' loaded');
                if (successCallback)
                    successCallback(templateData);
            })
            // If loading fails, run the function below.
            .fail(function() {
                console.log('Error loading template: ' + url);
                if (failureCallback)
                    failureCallback();
            });
    }
};

// For convenience... Expose the "common.context" under a shorter name "APP_CONTEXT"
var APP_CONTEXT = common.context;

// This configures the Date Picker.
$('.datepicker').datepicker({
    format: "yyyy-mm-dd",
    autoclose : true,
    endDate : "-2d"
});


