// Defines a Snowflake class that we can use over and over.
// It has all the behaviors we need for a single snowflake.

var Snowflake = function(xpos, ypos, size, xdir) {
    // We need to add code to save the
    // xpos, ypos and xdir values in variables.
    this.xpos = xpos;
    this.ypos = ypos;
    this.xdir = xdir;
    this.size = size > 0 && size <= 3 ? size : 1;
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

// Gets the size of the snowflake so it can be drawn.
Snowflake.prototype.getSize = function() {
    return this.size;
};
