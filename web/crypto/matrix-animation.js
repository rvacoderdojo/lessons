var MatrixAnimation = (function(){
    
    var containerId = 'matrix-container';
    var renderDelay = 50;
    var fontSize = 8;
    var matrixText = '田由甲申甴电甶男甸甹町画甼甽甾甿畀畁畂畃畄畅畆畇畈畉畊畋界畍畎畏畐畑';
    var matrixCanvas;
    var matrixContext;
    var matrixTextArr;
    var columns;
    var drops;
    var textIndex;
    
    return {
        init : function(containerName) {
            containerId = containerName;
        },
        setDelay : function(delayTime) {
            renderDelay = delayTime;
        },
        setText : function(textIn) {
            matrixText = textIn;
        },
        setFontSize : function(fontSizeIn) {
            if (fontSizeIn != undefined && fontSizeIn > 0)
                fontSize = fontSizeIn;
        },
        start : function() {
            matrixCanvas = document.getElementById(containerId);
            matrixCanvas.height = window.innerHeight;
            matrixCanvas.width = window.innerWidth;
            
            matrixContext = matrixCanvas.getContext('2d');
            
            // How many characters across the canvas
            matrixTextArr = matrixText.split("");
            columns = matrixCanvas.width / fontSize;
            drops = [];
            
            // Where to start in the matrix code
            // This allows for readable text messages to rain down.
            textIndex = [];

            for (var x = 0; x < columns; x++) {
                drops[x] = matrixCanvas.height + 1;
                textIndex[x] = Math.floor(Math.random() * matrixTextArr.length);
            }
            
            setInterval(this.render, renderDelay);
        },
        
        render: function() {
            matrixContext.fillStyle="rgba(0,0,0,0.05)";
            matrixContext.fillRect(0, 0, matrixCanvas.width, matrixCanvas.height);
            matrixContext.fillStyle = "#0F0"; // green text
            matrixContext.font = fontSize + "px arial";
            
            // Loop over the drops
            for (var i=0; i < drops.length; i++) {

                textIndex[i] = (textIndex[i] + 1) % matrixTextArr.length;
                var text = matrixTextArr[textIndex[i]];
                // x = i * fontSize, y = value of drops[i]*fontSize
                matrixContext.fillText(text, i*fontSize, drops[i]*fontSize);

                // reset a drop after it hits the bottom
                if (drops[i] * fontSize > matrixCanvas.height && Math.random() > 0.875) {
                    drops[i] = 0;
                }
                // bump Y coordinate
                drops[i]++;
            }
        }
        
    }
}());
