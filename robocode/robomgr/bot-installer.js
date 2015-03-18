var parser = require('java-class-parser');
var mkdirp = require('mkdirp');
var fs = require('fs');

var BotInstaller = function(classfile, basePath) {

    var self = this;

    parser([classfile], function(err, rs) {
        for (var className in rs) {
            var fullClassName = className;
            var packageParts = fullClassName.split(".");
            var fullClasspath = basePath;
            // Build up package folder path
            for (var index = 0; index < packageParts.length -1; index++) {
                fullClasspath += "/" + packageParts[index];
            }
            // Now add in the class filename
            var fullFinalPath = fullClasspath + "/" + packageParts[packageParts.length - 1] + ".class";

            mkdirp(fullClasspath, function (err) {
                if (err)
                    console.error(err)
                else {
                    fs.renameSync(classfile, fullFinalPath);
                    console.log('-- Moved bot ' + className + ' into: ' + fullFinalPath);
                }
            });
        }
    });

};

module.exports=BotInstaller;