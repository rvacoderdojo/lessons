var Hapi = require('hapi');
var fs = require('fs');
var config = require('./config.js');
var uploadHandler = require('./uploadHandler');

// Initialize our server
var server = new Hapi.Server();

// Setup the server's options including listening port.
server.connection({
    port : (config.listenPort || 6160) // Why 6160?  GI/GO
});

// Define a static route for content HTML/CSS/JS/Image content.
server.route({
    method: 'GET',
    path: '/{param*}',
    handler: {
        directory : {
            path: 'html'
        }
    }
});

// Special route to handle robot file upload
server.route({
    method: 'POST',
    path: '/upload',
    config: {

        payload: {
            output: 'stream',
            parse: true,
            allow: 'multipart/form-data',
            maxBytes: 209715200 // 200 MB
        },

        handler: uploadHandler  // See uploadHandler.js
    }
});

// When the server completes startup, log the URL to the console.
server.start(function () {
    console.log('[info]', 'Server running at: ' + server.info.uri);
});
