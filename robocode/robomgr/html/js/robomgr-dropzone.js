Dropzone.options.botDropzone = { // The camelized version of the ID of the form element

    autoProcessQueue: false,  // Don't start until the user hits the submit button
    uploadMultiple: false,  // Don't send multiple uploads at once
    maxFiles: 100,  // No more than 100 files in the upload zone at once
    maxFilesize: 100, // No files larger than 100kb
    previewsContainer: '#dropzone-thumbs',  // identify the HTML element where previews will show up.

    // Setting up the dropzone
    init: function() {
        var myDropzone = this;

        // First change the button to actually tell Dropzone to process the queue.
        this.element.querySelector("button[type=submit]").addEventListener("click", function(e) {
            // Make sure that the form isn't actually being sent.
            e.preventDefault();
            e.stopPropagation();
            myDropzone.processQueue();
        });

        this.element.querySelector("button[type=reset]").addEventListener("click", function(e){
           myDropzone.removeAllFiles();
        });

        // Override the thumbnail with a Robocode image.
        this.on("addedfile", function(file) {
            myDropzone.emit("thumbnail", file, "/images/tank-thumbnail.png");
        });

        // If the user dropped multiple files before hitting submit,
        // tell the queue to process the next one that's pending.
        this.on("complete", function(file){
            if (myDropzone.getQueuedFiles()) {
                myDropzone.processQueue();
            }
        });

        // Tidy up a successful upload
        this.on("success", function(file){
            // Remove the file from the display 1.25 secs after completion.
            setInterval(function() {
                myDropzone.removeFile(file);
            }, 1250);

        })
    }

}