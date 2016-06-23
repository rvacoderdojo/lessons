var BIGGEST_NUMBER = 100;
var myNumber = -1;
var lastGuess = null;
var temperatureText = ['so close', 'a total bonfire', 'on fire', 'burning up', 'really hot',
                 'starting to sweat', 'feeling toasty', 'seeing some sparks',
                 'feeling a slight breeze', 'getting chilly', 'shivering', 'in need of a coat',
                 'freezing', 'icy', 'a block of ice'];

function startGame() {
    document.getElementById('start-game').style.display='none';
    document.getElementById('guessing-form').style.display='block';
    lastGuess = null;
    myNumber = parseInt(1 + Math.random() * BIGGEST_NUMBER);
    console.log('My number is: ' + myNumber + ' shhhhhh!  Don\'t tell!');
}

function endGame() {
    changeBackground('gameover');
    document.getElementById('start-game').style.display='block';
    document.getElementById('guessing-form').style.display='none';
}

function playGame(formIn) {
    var guess = parseInt(formIn.guess.value);

    // Make sure they gave us an actual number and it's inside a reasonable range.
    if (isNaN(guess)) {
        showMessage('Sorry that wasn\'t a number')
        return false;
    }

    if (guess == myNumber) {
        showMessage('You won!');
        endGame();
        return false;
    }

    var severity =  calculateSeverity(myNumber, guess);
    showMessage('You are ' + temperatureText[severity])
    changeBackground('guess-' + severity);
    formIn.guess.focus();

    return false;
}

function calculateDistance(number1, number2) {
    return Math.abs(number1 - number2);
}

// Figures out how severely HOT or COLD your guess is.
function calculateSeverity(secretNumber, guess) {

    // First let's figure out the distance between the two numbers
    var distance = calculateDistance(secretNumber, guess);
    
    var divisionSize = 1;
    
    // Now let's divide the range above our secret number and below it into 5 equal segments to assign severity.
    // If our guess is ABOVE the secret number, then we divide up 5 / (MAX_NUMBER - secretNumber)
    if (guess > secretNumber) {
        divisionSize = 15.0 / (BIGGEST_NUMBER - secretNumber);
    }
    else {  // This handles the case where I guess was too low
        divisionSize = 15.0 / secretNumber;
    }
    
    // now we take the distance and multiple it by our division size to get a gauge of how far away their answer is.
    return parseInt(divisionSize * distance);
}

function changeBackground(styleName) {
    document.getElementsByTagName('html')[0].className=styleName;
}

function showMessage(msg) {
    document.getElementById('guess-result').innerHTML = '<span>' + msg + '</span>';
    console.log(msg);
}
