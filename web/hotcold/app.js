function startGame() {
    document.getElementById('start-game').style.display='none';
    document.getElementById('guessing-form').style.display='block';
}
function doStuff(formIn) {
    var redColor = 0;
    var blueColor = 255;
    var maxNum = 1000;
    var actualNum = 421;
    var factor = 256/1000;
    for (var count = 0; count < maxNum; count++) {
        var diff = Math.abs(actualNum - count);
        var distance = diff * factor;
        blueColor = Math.round(distance);
        redColor =  Math.round(255 - distance);
        var newColor = 'rgb(' + redColor + ', 0, ' + blueColor +')';
        console.log('Color: ' + newColor);
        document.body.style.backgroundColor = newColor;
        for (var x = 0; x < 1000000; x++) {var y = x / 100000;}
    }
    return false;
}