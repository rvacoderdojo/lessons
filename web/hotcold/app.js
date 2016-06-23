var BIGGEST_NUMBER = 100;
var myNumber = -1;
var lastGuess = null;
var coldLabels = ['so close', 'feeling a slight breeze', 'getting chilly', 'colder', 'shivering',
                  'freezing', 'icy', 'a block of ice'];
var hotLabels = ['so close', 'a total bonfire', 'on fire', 'burning up', 'really hot',
                  'starting to sweat', 'feeling toasty', 'a little warm' ];

function startGame() {
    document.getElementById('start-game').style.display='none';
    document.getElementById('guessing-form').style.display='block';
    lastGuess = null;
    myNumber = 50; // parseInt(1 + Math.random() * BIGGEST_NUMBER);
    console.log('My number is: ' + myNumber + ' shhhhhh!  Don\'t tell!');
}


function doStuff(formIn) {
    var guess = parseInt(formIn.guess.value);

    // Make sure they gave us an actual number.
    if (isNaN(guess)) {
        console.log('Sorry that wasn\'t a number');
        return false;
    }

    if (guess == myNumber) {
        console.log('You won!');
        return false;
    }

    // See if they've guessed before...
    if (lastGuess) {
        var lastDistance = calculateDistance(lastGuess, myNumber);
        var distance = calculateDistance(guess, myNumber);

        var text = 'about the same';
        if (lastDistance > distance) {
            text = warmer(myNumber, guess);
        }
        else {
            text = colder(myNumber, guess);
        }
        
        console.log('You are ' + text);
    }
    else {  // first guess... just tell them they didn't get it right
        console.log('Sorry that wasn\'t it');        
    }
    
    // Save last guess for this next time
    lastGuess = guess;

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
        divisionSize = 8.0 / (BIGGEST_NUMBER - secretNumber);
    }
    else {  // This handles the case where I guess was too low
        divisionSize = 8.0 / secretNumber;
    }
    
    // now we take the distance and multiple it by our division size to get a gauge of how far away their answer is.
    return parseInt(divisionSize * distance);
}

function warmer(secretNumber, guess) {
    var severity = calculateSeverity(secretNumber, guess);
    var styleName = 'hot-' + severity;
    changeBackground(styleName);
    
    return hotLabels[severity];
}

function colder(secretNumber, guess) {
    var severity = calculateSeverity(secretNumber, guess);
    var styleName = 'cold-' + severity;
    changeBackground(styleName);

    return coldLabels[severity];
}

function changeBackground(styleName) {
    document.getElementsByTagName('html')[0].className=styleName;
}
