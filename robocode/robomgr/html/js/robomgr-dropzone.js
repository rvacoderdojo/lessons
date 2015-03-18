Dropzone.options.botDropzone = { // The camelized version of the ID of the form element

    // The configuration we've talked about above
    autoProcessQueue: false,
    uploadMultiple: false,
    parallelUploads: 1,
    maxFiles: 100,
    maxFilesize: 100,
    previewsContainer: '#dropzone-thumbs',

    // The setting up of the dropzone
    init: function() {
        var myDropzone = this;

        // First change the button to actually tell Dropzone to process the queue.
        this.element.querySelector("button[type=submit]").addEventListener("click", function(e) {
            // Make sure that the form isn't actually being sent.
            e.preventDefault();
            e.stopPropagation();
            myDropzone.processQueue();
        });

        // Override the thumbnail with a Robocode image.
        this.on("addedfile", function(file) {
            myDropzone.emit("thumbnail", file, "/images/tank-thumbnail.png");
            console.log('Rendering thumbnail');
        });

        this.on("complete", function(file){
            if (myDropzone.getQueuedFiles()) {
                myDropzone.processQueue();
            }
        });

    }

}