// Setup our canvas and find the middle.
var ctx = initializeCanvas('myCanvas', document.body.clientWidth, 200);

var logoMessage = 'RVA Coder Dojo Canvas Demo';

var xCenter = ctx.canvas.width / 2;
var yCenter = ctx.canvas.height / 2;

var maxBallRadius = 30;
var maxX = ctx.canvas.width;
var maxY = ctx.canvas.height;

// Show a title
ctx.font = '30px Lucida Console';
ctx.textAlign='center';
ctx.textBaseline = 'middle';

showMessage(logoMessage);
window.setTimeout(animate, 1000);

// This handles the bouncing line animation and fading.
function animate() {
    // Put the logo text back on the screen so it never fades.
    showMessage(logoMessage);
    applyFade();

    // Now draw colored balls.
    ctx.lineWidth = 3;
    var howMany = Math.random() * 3;
    for (var count = 0; count < howMany; count++) {
        drawRandomBall();
    }

    // re-run the animation in 40 milliseconds
    window.setTimeout(animate, 40);
}

// Draws the message on the screen.
function showMessage(message) {
    ctx.lineWidth = 2;
    ctx.strokeStyle= 'lightgreen';
    ctx.fillStyle = 'green';
    ctx.strokeText(message, xCenter, yCenter);
    ctx.fillText(message, xCenter, yCenter);
}

// Apply a fade to the whole screen.
function applyFade() {
    // Set the color to a partially transparent black
    ctx.fillStyle='rgba(0,0,0,0.05)';
    // Fills the whole screen which gives us the fade out.
    ctx.fillRect(0, 0, maxX, maxY);

}

// Draw a randomly colored, sized and positioned ball
function drawRandomBall() {
    var xPos = Math.random() * maxX;
    var yPos = Math.random() * maxY;
    var radius = Math.random() * maxBallRadius;

    var red = Math.floor(Math.random() * 220);
    var green = Math.floor(Math.random() * 220);
    var blue = Math.floor(Math.random() * 220);
    var opacity = Math.random();

    // Set the circle's fill color
    ctx.fillStyle='rgba(' + (red) + ',' + (green) + ',' + (blue) + ',' + opacity +')';

    // Set the circle's outline color a little
    // brighter than the fill and not transparent.
    ctx.strokeStyle='rgba(' + (red + 35) + ',' + (green + 35) + ',' + (blue + 35) + ',1)';

    // Draw the circle
    ctx.circle(xPos, yPos, radius);
}