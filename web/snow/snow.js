// Resources:
// - IIFE http://benalman.com/news/2010/11/immediately-invoked-function-expression/
// - https://www.kirupa.com/html5/animating_in_code_using_javascript.htm

var STARTING_FLAKES = 10;
var MAX_FLAKE_SIZE = 3;

// Create a snowflake with size, position, and x-direction
var Snowflake = function(xpos, ypos, size, xdir) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.size = size > 0 && size <= MAX_FLAKE_SIZE ? size : 1;
    this.xdir = xdir;
};

// Add a method to make the flake move down and over
Snowflake.prototype.move = function() {
    this.xpos += this.xdir;
    this.ypos += this.size;

    return {
        x: this.xpos,
        y: this.ypos
    }
};

// Gets the size of the snowflake
Snowflake.prototype.getSize = function() {
    return this.size;
};

// This creates the definition of a "snow storm"
var storm = (function() {
    // Create a constant for our cirlce so we don't recalculate it everytime.
    var PI2 = Math.PI * 2;
    var ctx = null;
    var maxFlakes = STARTING_FLAKES;
    var requestAnimationFrame = null;
    var snowflakes = [];
    var width = window.innerWidth;
    var height = window.innerHeight;

    return {
        // Initialize the snow storm settings.
        init: function(canvasId, flakes) {
            maxFlakes = flakes;
            ctx = document.getElementById(canvasId).getContext('2d');

            // Hook into the animation loop
            requestAnimationFrame = window.requestAnimationFrame ||
                window.mozRequestAnimationFrame ||
                window.webkitRequestAnimationFrame ||
                window.msRequestAnimationFrame;

            // Create the initial set of snowflakes.
            for (var index = 0; index < STARTING_FLAKES; index++) {
                snowflakes[index] = createSnowflake();
            }
        },

        // Function to start snowing.
        startSnow: function() {
            drawSnow();
        }
    }

    // Creates a new random snowflake
    function createSnowflake() {
        // Place the snowflake in a random place.
        var xpos = Math.round(Math.random() * width);
        var ypos = Math.round(Math.random() * 10);

        // Set the size of the flake.
        var size = Math.round(Math.random() * MAX_FLAKE_SIZE) + 1;

        // Set which way left or right it will blow.
        var xdir = Math.round((Math.random() * 4) - 2);

        return new Snowflake(xpos, ypos, size, xdir);
    }

    // Draws a single frame of the snow animation.
    function drawSnow() {
        // Re-measure the window size in case the user resized something.
        width = window.innerWidth;
        height = window.innerHeight;

        // Make canvas fit the full page.
        ctx.canvas.width = width;
        ctx.canvas.height = height;

        // Clean up the prior set of flakes.
        ctx.clearRect(0, 0, width, height);
        ctx.fillStyle = '#eee';

        // Draw each snowflake.
        snowflakes.forEach(function (snowflake, index, snowflakes) {

            var position = snowflake.move();
            // Make sure the snowflake is still on the screen.  If not create a new one.
            if (position.y < height && position.x > 0 && position.x < width) {
                circle(position.x, position.y, snowflake.getSize());
            }
            else {
                // Replace the snowflake that is off the screen with a new one.
                snowflakes[index] = createSnowflake();
            }
        });

        // Add more flakes gradually until we hit the max.
        if (snowflakes.length <  maxFlakes) {
            snowflakes.push(createSnowflake());
        }

        // Tell the browser to draw the frame, and start painting a new frame.
        requestAnimationFrame(drawSnow);
    }

    // Convenient function to draw a circle
    function circle(x, y, size) {
        ctx.beginPath();
        ctx.arc(x, y, size, 0, PI2);
        ctx.fill();
        ctx.closePath();
    }


})();

storm.init('snowstorm', 1000);
storm.startSnow();
