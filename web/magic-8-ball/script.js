// This is an array of answers that the Magic 8 Ball could give.
var answers = [
// These are the traditional answers from the Magic 8 Ball.
    'It is certain',
    'It is decidedly so',
    'Without a doubt',
    'Yes, definitely',
    'You may rely on it',
    'As I see it, yes',
    'Most likely',
    'Outlook good',
    'Yes',
    'Signs point to yes',
    'Reply hazy try again',
    'Ask again later',
    'Better not tell you now',
    'Cannot predict now',
    'Concentrate and ask again',
    'Don\'t count on it',
    'Concentrate and ask again',
    'Don\'t count on it',
    'My reply is no',
    'My sources say no',
    'Outlook not so good',
    'Very doubtful',
// These are ones I made up!
    'My Mom doesn\'t think so',
    'My Dad says yes!',
    'My brother doubts it'
];

// This function handles randomly picking an answer, and displaying it.
function predictTheFuture(form) {
    // First we pick a number between 0 and the length of the answer list.
    var answerIndex = Math.floor(Math.random() * answers.length);
    // Using that number we get the text of the answer at that position.
    var myAnswer = answers[answerIndex];

    // We use JavasScript to find the HTML element on the page where our answers
    // should be displayed.  This is the HTML tag with an id of "answers"
    var answerDiv = document.getElementById('answer');
    answerDiv.innerHTML=myAnswer;

    // Finally we tell the program to do a fade in by adding the "fadeIn" style.
    answerDiv.classList.toggle('fadeIn');

    // After waiting 3 seconds (3000 milliseconds) it's time
    // to turn off the "fadeIn" style.
    window.setTimeout(function() {
        answerDiv.classList.toggle('fadeIn');
        form['question'].value = "";
    }, 3000);

    // This keeps the form from submitting (and going to a different page).
    return false;
}
