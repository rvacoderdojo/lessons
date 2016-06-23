var BIGGEST_NUMBER = 100;
var divisionSize = 15.0 / BIGGEST_NUMBER;
var myNumber = -1;
var temperatureText = ['like a volcano', 'a total bonfire', 'on fire', 'burning up', 'really hot',
                 'starting to sweat', 'feeling toasty', 'seeing some sparks',
                 'feeling a slight breeze', 'getting chilly', 'shivering', 'in need of a coat',
                 'freezing', 'icy', 'a block of ice'];

function startGame() {
    showMessage('');
    document.getElementById('start-game').style.display='none';
    document.getElementById('guessing-form').style.display='block';
    myNumber = parseInt(1 + Math.random() * BIGGEST_NUMBER);
    divisionSize = calculateDivisionSize(myNumber);
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

    if (guess < 1 || guess > BIGGEST_NUMBER) {
        showMessage('Please choose a number between 1 and ' + BIGGEST_NUMBER);
        return false;
    }

    if (guess == myNumber) {
        showMessage('You won!');
        endGame();
        return false;
    }

    var severity =  calculateSeverity(myNumber, guess);
    var distance = calculateDistance(myNumber, guess);
    var message = distance < 3 ? 'very close!' : temperatureText[severity];
    showMessage('You are ' + message);
    changeBackground('guess-' + severity);
    formIn.guess.focus();
    formIn.guess.select();

    return false;
}

function calculateDistance(number1, number2) {
    return Math.abs(number1 - number2);
}

function calculateDivisionSize(secretNumber) {

    // See if secret number is closer to the biggest number then
    // the greatest distance is the distance from 0 to the secret number.
    if (secretNumber > BIGGEST_NUMBER / 2) {
        divisionSize = 15.0 / secretNumber;
    }
    else {
        // Otherwise the the division size will be based on the distance from
        // the secret number to the biggest number.
        divisionSize = 15.0 / (BIGGEST_NUMBER - secretNumber);
    }

    return divisionSize;

}
// Figures out how severely HOT or COLD your guess is.
function calculateSeverity(secretNumber, guess) {

    // First let's figure out the distance between the two numbers
    var distance = calculateDistance(secretNumber, guess);

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
