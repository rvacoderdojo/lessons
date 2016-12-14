// Resources:
// - IIFE http://benalman.com/news/2010/11/immediately-invoked-function-expression/
// - https://www.kirupa.com/html5/creating_simple_html5_canvas_animation.htm
// - https://www.kirupa.com/html5/animating_in_code_using_javascript.htm
var storm = {
    ctx: null,

    init: function() {
        ctx = document.getElementById('snowstorm').getContext('2d');
        ctx.fillRect(25,25,100,100);
        ctx.clearRect(45,45,60,60);
        ctx.strokeRect(50,50,50,50);
    }
};


var mainCanvas = document.getElementById("snowstorm");
var mainContext = mainCanvas.getContext('2d');

var canvasWidth = mainCanvas.width;
var canvasHeight = mainCanvas.height;

var angle = 0;

var requestAnimationFrame = window.requestAnimationFrame ||
    window.mozRequestAnimationFrame ||
    window.webkitRequestAnimationFrame ||
    window.msRequestAnimationFrame;

function drawCircle() {
    mainContext.clearRect(0, 0, canvasWidth, canvasHeight);

    // color in the background
//    mainContext.fillStyle = "#EEEEEE";
//    mainContext.fillRect(0, 0, canvasWidth, canvasHeight);

    // draw the circle
    mainContext.beginPath();

    var radius = 25 + 150 * Math.abs(Math.cos(angle));
    mainContext.arc(225, 225, radius, 0, Math.PI * 2, false);
    mainContext.closePath();

    // color in the circle
    mainContext.fillStyle = "#006699";
    mainContext.fill();

    angle += Math.PI / 64;

    requestAnimationFrame(drawCircle);
}
drawCircle();