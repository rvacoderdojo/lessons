var fs = require('fs');
var Hapi = require('hapi');

var server = new Hapi.Server();
server.connection({ port : 6160 });  // Why 6160?  GI/GO

// Define a static route for content
server.route({
    method: 'GET',
    path: '/{param*}',
    handler: {
        directory : {
            path: 'html'
        }
    }
});

// Special route to handle file upload.
server.route({
    method: 'POST',
    path: '/upload',
    config: {

        payload: {
            output: 'stream',
            parse: true,
            allow: 'multipart/form-data'
        },

        handler: function (request, reply) {
            var data = request.payload;
            console.log(data);
            if (data.file) {
                var name = data.file.hapi.filename;
                var path = __dirname + "/landingzone/" + name;
                var file = fs.createWriteStream(path);

                file.on('error', function (err) {
                    console.error(err)
                });

                data.file.pipe(file);

                data.file.on('end', function (err) {
                    var ret = {
                        filename: data.file.hapi.filename,
                        headers: data.file.hapi.headers
                    };
                    reply(JSON.stringify(ret));
                })
            }

        }
    }
});

server.start(function () {
    console.log('[info]', 'Server running at: ' + server.info.uri);
});