// Setup our canvas and find the middle.
var ctx = initializeCanvas('myCanvas');
var centerX = ctx.canvas.width / 2;
var centerY = ctx.canvas.height / 2;

// Setup a few variables.
var radius = 170;
var fontSize = 80;
var halfRadius = (radius / 2);

ctx.strokeStyle= '#black';
ctx.lineWidth = 1;
ctx.font= fontSize + 'px Lucida Console';
ctx.textAlign='center';
ctx.textBaseline = 'middle';

// Draw the "white" side
ctx.beginPath();
ctx.fillStyle = 'white';
// Creates the outer half circle
ctx.arc(centerX, centerY, radius, 0.5 * Math.PI, 1.5 * Math.PI);
ctx.arc(centerX, centerY - halfRadius, halfRadius, 1.5 * Math.PI, 0.5 * Math.PI );
// Now create the top semi-circle
ctx.arc(centerX, centerY + halfRadius, halfRadius, 1.5 * Math.PI, 0.5 * Math.PI, true);
// Go back to center and draw the tail
ctx.moveTo(centerX, centerY + radius);
ctx.fill();
ctx.stroke();

// Draw the "black" side
ctx.beginPath();
ctx.fillStyle = 'black';
// Creates the outer half circle
ctx.arc(centerX, centerY, radius, 0.5 * Math.PI, 1.5 * Math.PI, true);
ctx.arc(centerX, centerY - halfRadius, halfRadius, 1.5 * Math.PI, 0.5 * Math.PI );
// Now create the top semi-circle
ctx.arc(centerX, centerY + halfRadius, halfRadius, 1.5 * Math.PI, 0.5 * Math.PI, true);
// Go back to center and draw the tail
ctx.moveTo(centerX, centerY + radius);
ctx.fill();
ctx.stroke();

// Put a 0 at the top of the Yin/Yang
ctx.fillText("0", centerX, centerY - halfRadius);

ctx.fillStyle = 'white';
ctx.strokeStyle= 'white';

// Put a 1 at the bottom of the Yin/Yang
ctx.fillText("1", centerX, centerY + halfRadius);

// Put our name at the top
ctx.font = '50px Lucida Console';
ctx.fillStyle = 'black';
ctx.fillText("RVA Coder Dojo", centerX, 50);
