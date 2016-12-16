// Resources:
// - IIFE http://benalman.com/news/2010/11/immediately-invoked-function-expression/
// - https://www.kirupa.com/html5/animating_in_code_using_javascript.htm

// This creates the definition of a "snow storm"
var storm = (function() {
    // Create a constant for our cirlce so we don't recalculate it everytime.
    var PI2 = Math.PI * 2;
    var ctx = null;
    var maxFlakes = 100;
    var snowflakes = [];
    var width = window.innerWidth;
    var height = window.innerHeight;
    var requestAnimationFrame = null;

    return {
        // Initialize the snow storm settings.
        init: function(canvasId, flakes) {
            maxFlakes = flakes;
            // Grab a reference to the canvas attached to the Canvas ID.
            ctx = document.getElementById(canvasId).getContext('2d');

            // Hook into the animation loop for better performance.
            requestAnimationFrame = window.requestAnimationFrame ||
                window.mozRequestAnimationFrame ||
                window.webkitRequestAnimationFrame ||
                window.msRequestAnimationFrame;

            // Create the initial set of snowflakes (just 10 to start)
            for (var index = 0; index < 10; index++) {
                snowflakes[index] = createSnowflake();
            }
        },

        // Function to start snowing.
        startSnow: function() {
            drawSnow();
        }
    }

    // Creates a new random snowflake at a random position of random size.
    function createSnowflake() {
        // Place the snowflake in a random place and at a random height near the top
        var xpos = Math.round(Math.random() * width);
        var ypos = Math.round(Math.random() * 30);

        // Set the size of the flake.
        var size = Math.round(Math.random() * 3) + 1;

        // Set which way left or right it will blow (this creates a number between -2 and 2)
        var xdir = Math.round((Math.random() * 4) - 2);

        return new Snowflake(xpos, ypos, size, xdir);
    }

    // Draws a single frame of the snow animation.
    function drawSnow() {
        // Re-measure the window size in case the user resized it.
        width = window.innerWidth;
        height = window.innerHeight;

        // Make canvas fit the full page.
        ctx.canvas.width = width;
        ctx.canvas.height = height;

        // Clean up the prior set of flakes.
        ctx.clearRect(0, 0, width, height);

        // Make the snow white
        ctx.fillStyle = '#eee';

        // Draw each snowflake.
        snowflakes.forEach(function (snowflake, index, snowflakes) {

            var position = snowflake.move();

            // Make sure the snowflake is still on the screen.
            if (position.y < height && position.x > 0 && position.x < width) {
                circle(position.x, position.y, snowflake.getSize());
            }
            else {
                // The snowflake was off of the screen.
                // So we replace it with a new one.
                snowflakes[index] = createSnowflake();
            }
        });

        // Add more flakes gradually until we hit the max.
        if (snowflakes.length <  maxFlakes) {
            snowflakes.push(createSnowflake());
        }

        // Tell the browser to paint the screen with our new set of snowflakes.
        requestAnimationFrame(drawSnow);
    }

    // A function to make drawing circles easier.
    function circle(x, y, size) {
        ctx.beginPath();
        ctx.arc(x, y, size, 0, PI2);
        ctx.fill();
        ctx.closePath();
    }
})();
