// Setup our canvas and find the middle.
var ctx = initializeCanvas('myCanvas');

var centerX = ctx.canvas.width / 2;
var centerY = ctx.canvas.height / 2;

// Sets the line color and thickness
ctx.strokeStyle= '#000';
ctx.lineWidth = 3;

// Draws a line
ctx.beginPath();
ctx.moveTo(centerX - 50, 15);
ctx.lineTo(centerX + 50, 15);
ctx.stroke();

// Sets the color that will fill in a shape
ctx.fillStyle = 'yellow';

// Draw a rectangle
ctx.beginPath();
ctx.rect(centerX - 50, 50, 100, 50);
ctx.fill();
ctx.stroke();

// Draw a circle
ctx.beginPath();
ctx.arc(centerX, 200, 50, 0, 2 * Math.PI, false);
ctx.fillStyle = 'red';
ctx.fill();
ctx.stroke();

// Draws a partial circle going clockwise
ctx.beginPath();
ctx.strokeStyle='blue';
ctx.arc(centerX, 350, 50, 1.25 * Math.PI, 1.75 * Math.PI, false);
ctx.stroke();


// Draws a partial circle going counter-clockwise.
ctx.beginPath();
ctx.strokeStyle='red';
// Note the parameters are the same as above except for the direction.
ctx.arc(centerX, 350, 50, 1.25 * Math.PI, 1.75 * Math.PI, true);
ctx.stroke();
