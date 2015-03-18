var fs = require('fs');
var config = require('./config.js');
var BotInstaller = require('./bot-installer');

var handler = function (request, reply) {
    var data = request.payload;
    if (data.file) {
        console.log("-- Receiving: " + data.file.hapi.filename);
        var name = data.file.hapi.filename;
        var path = (config.scratchFolder || './landingzone') + "/" + name;
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
            new BotInstaller(path, config.classesFolder);

            reply(JSON.stringify(ret));
        })
    }


};

module.exports = handler;