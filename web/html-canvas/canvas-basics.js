// Makes the Canvas full screen,
// and returns the context.
function initializeCanvas (canvasId, width, height) {

    var canvas = document.getElementById('myCanvas');
    canvas.width = width ? width : document.body.clientWidth;
    canvas.height = height ? height : document.body.clientHeight;
    var context = canvas.getContext('2d');

    // We are going to add some convenience methods 
    // to make using the canvas a little simpler. 
    context.getWidth = function() {
        return this.canvas.width;
    }

    context.getHeight = function() {
        return this.canvas.height;
    }

    context.circle = function (x, y, radius, noFill) {
        this.beginPath();
        this.arc(x, y, radius, 0, Math.PI * 2);
        this.stroke();
        if (!noFill) {
            this.fill();
        }
    }

    return context;
}

