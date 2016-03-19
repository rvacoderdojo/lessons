// The alphabet.  This is where we start from when we create our substitution ciphers
var alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ];

// This is here to help us calculate the array index.  All letters have a numerical value in
// a computers.  We can use that fact to help us find a letter in the alphabet array.
// For example: Capital 'A' = 65, Capital 'C' = 67.  'C' - 'A' = 2   (67 - 65)
// If you look at our alphabet above you'll see the letter 'C' is in the 3rd position but we
// start counting at 0 so 'A' is at position 0, 'B' is at 1, and 'C' is at position 2.
var valueOfA = 'A'.charCodeAt(0);

// This helps to map our encryption algorithm to what the user selects in the drop down box.
var cryptoMap = (function() {
    var map = {};
    map[CaesarCrypto.value] = CaesarCrypto;
    map[KeywordShiftCrypto.value] = KeywordShiftCrypto;
    map[RandomizedCrypto.value] = RandomizedCrypto;

    return map;
}());

// This is what gets run when you hit the "convert" button
function process(formIn) {
    
    var originalMessage = formIn.inputMessage.value;
    var keyValue = formIn.key.value;
    var doEncrypt = formIn.encrypt.checked;

    var encodedMessage="";
    if (doEncrypt) {  
        encodedMessage = encrypt(keyValue, originalMessage, cryptoMap[formIn.algorithm.value]);
    }
    else {
        encodedMessage = decrypt(keyValue, originalMessage, cryptoMap[formIn.algorithm.value]);
    }
    formIn.outputMessage.value = encodedMessage;

    return false;
};

// This runs our encryption by first getting the substitution alphabet from the selected encryption algorithm
// then it does the swapping of letters.
function encrypt(key, message, cryptoStrategy) {
    var substitutions = cryptoStrategy.createSubstitutions(key);
    updateEncryptedDisplay(substitutions)

    // Converts to uppercase
    var scrubbedMessage = message.toUpperCase();
    var encrypted = "";
    for (var x = 0; x < scrubbedMessage.length; x++) {
        // Figure out what position in the alphabet the next letter is.
        var substitutionIndex = scrubbedMessage.charCodeAt(x) - valueOfA;
        
        // replace that letter with the one in the same position in the substitution alphabet.
        if (substitutionIndex >= 0 && substitutionIndex <= substitutions.length) {
            encrypted += substitutions[scrubbedMessage.charCodeAt(x) - valueOfA];
        }
        else {  // we don't recognize the character (say a space, period, question mark) just output it as is.
            encrypted += scrubbedMessage.charAt(x);
        }
        // If you uncomment the line below you can prevent repeating letters
//        substitutions = preventRepetition(substitutions);
    }
    return encrypted;

}

// This runs the decryption  by first getting the substitution alphabet from the selected encryption algorithm,
// figuring out where in the substitution list the encrypted letter appears, then swapping it for the
// letter that's at the same position in the regular alphabet.
function decrypt(key, message, cryptoStrategy) {
    var substitutions = cryptoStrategy.createSubstitutions(key);
    updateEncryptedDisplay(substitutions)

    var scrubbedMessage = message.toUpperCase();
    var decoded = "";
    for (var x = 0; x < scrubbedMessage.length; x++) {
        // Figures out where in the substitution letter set our encrypted letter is.
        var substitutionIndex = substitutions.findIndex(function(letter) {
            return letter == scrubbedMessage.charAt(x);
        });
        
        // replaces the encrypted letter with the letter that's at that same position in the regular alphabet.
        if (substitutionIndex >= 0 && substitutionIndex <= substitutions.length) {
            decoded += alphabet[substitutionIndex];
        }
        else {
            decoded += scrubbedMessage.charAt(x);
        }
        // If you uncomment the line below you can prevent repeating text
//        substitutions = preventRepetition(substitutions);
    }
    return decoded;


}

// This is a strategy that changes the substitution alphabet so if you type the same letter over and over
// you don't get the same substitution value.  It makes the encryption stronger.
function preventRepetition(substitutions) {
    substitutions.rotate(5);
    updateEncryptedDisplay(substitutions);

    return substitutions;
}

// This updates the "Encrypted Alphabet" on the screen so you can see what the substitutions look like.
function updateEncryptedDisplay(substitutions) {
    var displaySet = "";
    for (var x = 0; x < substitutions.length; x++) {
        displaySet += (substitutions[x] + " ");
    }
    document.getElementById("encrypted-set").innerHTML = displaySet;
}


// Random number generator that allows seeding since Javascript's doesn't.
// Repeatable random number sequences are important for encryption

// This sets the seed for the random number generator.
Math.initRandomSeed = function (seedValue) {
    Math.seed = seedValue;
}

// in order to work 'Math.seed' must NOT be undefined,
// so in any case, you HAVE to provide a Math.seed
Math.seededRandom = function(max, min) {

    // If the seed hasn't been set, seed it off of the current time.
    if (Math.seed == undefined) {
        Math.seed = Date.now();
    }

    max = max || 1;
    min = min || 0;

    Math.seed = (Math.seed * 9301 + 49297) % 233280;
    var rnd = Math.seed / 233280;

    return min + rnd * (max - min);
}

// Decorate Array with a rotate function so we can rotate elements in an array.
Array.prototype.rotate = function(count) {
    count = count % this.length;
    if (count < 0) {
        this.unshift.apply(this, this.splice(count));
    }
    else {
        this.push.apply(this, this.splice(0, count));
    }
    return this;
}