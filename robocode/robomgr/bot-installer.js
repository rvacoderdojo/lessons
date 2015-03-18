var parser = require('java-class-parser');
var mkdirp = require('mkdirp');
var fs = require('fs');

var BotInstaller = function(classfile, basePath) {

    // Parse the java .Class file, and move it into the Robocode base classpath.
    parser([classfile], function(err, rs) {

        // For each class found.... (should only be one)
        for (var className in rs) {

            // Split the full class name into pieces and place in an array.
            // Example: com.rvacoderdojo.MyBot becomes
            // ["com", "rvacoderdojo" "MyBot"]
            var packageParts = className.split(".");

            var fullClasspath = basePath;
            // Build up package folder path
            for (var index = 0; index < packageParts.length -1; index++) {
                fullClasspath += "/" + packageParts[index];
            }
            // Now add in the class filename
            var fullFinalPath = fullClasspath + "/" + packageParts[packageParts.length - 1] + ".class";

            // Create the package folders.
            mkdirp(fullClasspath, function (err) {
                if (err)
                    console.error(err)
                else {
                    // now move the file out of the dropzone and into the final destination.
                    fs.renameSync(classfile, fullFinalPath);
                    console.log('-- Moved bot ' + className + ' into: ' + fullFinalPath);
                }
            });
        }
    });

};

module.exports=BotInstaller;