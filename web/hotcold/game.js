// This is the largest number that could be picked. 
var BIGGEST_NUMBER = 100;

// This calculates the how we can evenly split up the different temperatures
// into buckets for the different sayings ("you are hot, you are cold")
// and seperate colors.   We allow for 15 different colors and sayings.
var divisionSize = 15.0 / BIGGEST_NUMBER;

// This will be the variable that holds the random number picked by the computer.
var myNumber = -1;

// These are all the different sayings from REALLY hot to REALLY cold.
// Change them to make them your own.
var temperatureText = ['like a volcano', 'a total bonfire', 'on fire', 'burning up', 'really hot',
                 'starting to sweat', 'feeling toasty', 'seeing some sparks',
                 'feeling a slight breeze', 'getting chilly', 'shivering', 'in need of a coat',
                 'freezing', 'icy', 'a block of ice'];

/**
 * This handles starting the game.  It does the following things:
 * 1. It swaps out the "Start Game" button for the guessing form.
 * 2. It picks a random number of you to guess.
 * 3. It recalculates how to split up the 15 divisions for the answers/colors  This is based on either the
 * highest number or lowest number (1) that is furthest from the computer's selected number.
 * 4. It tells the user to start guessing and what the allowable range is.
 */
function startGame() {
    // This hides the "start-game" button
    document.getElementById('start-game').style.display='none';
    // And shows the game input form.
    document.getElementById('guessing-form').style.display='block';
    
    // We are picking a random number between 1 and the largest number allowed.
    myNumber = parseInt(1 + Math.random() * BIGGEST_NUMBER);
    
    // See comments on this method for a clearer explanation, but this is
    // basically dividing the range from the computer's number to the furthest number out (either 1 or 
    // the BIGGEST_NUMBER) into 15 even sections so we can use the full range of hot and cold colors and text.
    divisionSize = calculateDivisionSize(myNumber);
    
    // Show the user the message to start the game.
    showMessage('Please pick a number between 1 and ' + BIGGEST_NUMBER);
}

/**
 * This handles the end of game.  It basically:
 * 1. Swaps the input form view with the start game view
 * 2. Clears out the guesses on the input form.
 */
function endGame(formIn) {
    
    // Sets the background back to white.
    changeBackground('gameover');
    
    // Show the start game button
    document.getElementById('start-game').style.display='block';
    
    // hide the guessing form.
    document.getElementById('guessing-form').style.display='none';
    
    // Clear out the value in the form so if we start a new game we don't show the old guess.
    formIn.guess.value="";
}

/**
 * This handles the game play. 
 * 
 * @param formIn - the guessing game's input form.
 * @returns false.. .this is needed to keep the form submit from going to a new web page.
 */
function playGame(formIn) {
    
    // try to convert the value the user typed into the form into a number.  
    var guess = parseInt(formIn.guess.value);

    // Make sure they gave us an actual number and not text. 
    if (isNaN(guess)) {
        showMessage('Sorry that wasn\'t a number')
        return false;
    }

    // Make sure their guess is between 1 and the largest possible number.
    if (guess < 1 || guess > BIGGEST_NUMBER) {
        showMessage('Please choose a number between 1 and ' + BIGGEST_NUMBER);
        return false;
    }

    // See if the user guessed the number right.  If so tell them they won and end the game.
    if (guess == myNumber) {
        showMessage('You won!');
        endGame(formIn);
        return false;
    }

    // If they didn't get it right, this figures out how far away they are and tells them how hot or ocld they are.
    var severity =  calculateSeverity(myNumber, guess);
    var distance = calculateDistance(myNumber, guess);
    
    // If they're really close give them that hinr.
    var message = distance < 3 ? 'very close!' : temperatureText[severity];
    
    // Show the Hot/Cold message
    showMessage('You are ' + message);
    // Change the background color to show how hot or cold they are.
    changeBackground('guess-' + severity);
    
    // This makes the cursor go back to the input form field 
    // so they can easily guess again.
    formIn.guess.focus();
    formIn.guess.select();

    return false;
}

/**
 * This figures out how far apart 2 numbers are.  
 * It uses the "absolute" value of the difference 
 * to keep us from getting a negative number so
 * 5 - 3 will give us the same value as 3 - 5;
 */
function calculateDistance(number1, number2) {
    return Math.abs(number1 - number2);
}

// Figure out how to divide up the maximum range 
// of the distance between computer's chosen number
// and either 1 or the BIGGEST_NUMBER.  
// This is done by seeing if the secret number is 
// past the midpoint of the full range.
function calculateDivisionSize(secretNumber) {

    // See if secret number is closer to the biggest number then
    // the greatest distance is the distance from 1 to the secret number.
    if (secretNumber > BIGGEST_NUMBER / 2) {
        divisionSize = 15.0 / secretNumber;
    }
    else {
        // Otherwise the division size will be based on the distance from
        // the secret number to the biggest number.
        divisionSize = 15.0 / (BIGGEST_NUMBER - secretNumber);
    }

    return divisionSize;

}
// Figures out how severely HOT or COLD your guess is.
function calculateSeverity(secretNumber, guess) {

    // First let's figure out the distance between the two numbers
    var distance = calculateDistance(secretNumber, guess);

    // now we take the distance and multiply it by our division size 
    // to get a gauge of how far away their answer is.
    return parseInt(divisionSize * distance);
}

// Changes the background color to a given style.
function changeBackground(styleName) {
    document.getElementsByTagName('html')[0].className=styleName;
}

// Shows a message
function showMessage(msg) {
    document.getElementById('guess-result').innerHTML = '<span>' + msg + '</span>';
}
