var fs = require('fs');
var config = require('./config.js');
var BotInstaller = require('./bot-installer');

// HAPI handler for the file upload route.
var handler = function (request, reply) {
    var data = request.payload;

    // If there's a "file" input element, then handle that.
    if (data.file) {
        console.log("-- Receiving: " + data.file.hapi.filename);
        var name = data.file.hapi.filename;
        // Save the file to either the configured scratchFolder or fallback to "./landingzone"
        var path = (config.scratchFolder || './landingzone') + "/" + name;
        var file = fs.createWriteStream(path);

        // Handle errors
        file.on('error', function (err) {
            console.error(err)
        });

        // Write the data from the upload to the file.
        data.file.pipe(file);

        // When we reach the end of the file output, run the BotInstaller, and respond back to the browser.
        data.file.on('end', function (err) {
            var ret = {
                filename: data.file.hapi.filename,
                headers: data.file.hapi.headers
            };
            new BotInstaller(path, config.classesFolder);

            reply(JSON.stringify(ret));
        })
    }


};

module.exports = handler;