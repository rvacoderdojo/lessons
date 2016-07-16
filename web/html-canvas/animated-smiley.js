// Setup our canvas and find the middle.
var ctx = initializeCanvas('myCanvas');

var centerX = ctx.getWidth() / 2;
var centerY = ctx.getHeight() / 2;

// Sets the line color and thickness
ctx.strokeStyle= 'black';
ctx.lineWidth = 4;

ctx.fillStyle = 'yellow';
ctx.circle(centerX, centerY, 250);

// Draw the mouth.
ctx.fillStyle = 'pink';
ctx.beginPath();
ctx.moveTo(centerX - 150, centerY + 50);
ctx.quadraticCurveTo(centerX, centerY + 120, centerX + 150, centerY + 50);
ctx.quadraticCurveTo(centerX, centerY + 250, centerX - 150, centerY + 50);
ctx.stroke();
ctx.fill();

var leftEyeX = centerX - 100;
var rightEyeX = centerX + 100;
var eyeY = centerY - 100;

drawEye(leftEyeX, eyeY);
drawEye(rightEyeX, eyeY);

// Creates an event listener and runs everytime the mouse moves.
ctx.canvas.addEventListener('mousemove', function(evt) {
    // Redraws the eyes.  
    drawEye(leftEyeX, eyeY, evt.clientX, evt.clientY);
    drawEye(rightEyeX, eyeY, evt.clientX, evt.clientY);
});

// Draws an Eye centered at the X/Y position looking at 
// the lookAtX and lookAtY position.
function drawEye(eyeCenterX, eyeCenterY, lookAtX, lookAtY) {

    // Draw the whites of the eyes
    ctx.fillStyle = 'white';
    ctx.circle(eyeCenterX, eyeCenterY, 40);

    // Now figure out where to put the pupils
    var xAdjust = 0;
    var yAdjust = 0;

    // If we have something to look at we
    // we need to calculate where the pupils go. 
    // See: http://www.mathsisfun.com/polar-cartesian-coordinates.html 
    if (lookAtX && lookAtY) {
        // Arc tangent gives us the angle  
        var arcTan = Math.atan2(lookAtY - eyeCenterY,
            lookAtX - eyeCenterX);

        // Then we can calculate how to adjust around 
        // the circle the position of our pupils
        xAdjust = 20 * Math.cos(arcTan);
        yAdjust = 20 * Math.sin(arcTan);
        // 
    }

    // Draw pupils
    ctx.fillStyle = 'black';
    ctx.circle(eyeCenterX + xAdjust, eyeCenterY + yAdjust, 15);
}
