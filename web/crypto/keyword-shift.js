// This will work a little bit like a Caesar cipher, except a keyword is given and used to shift
// the alphabet.  The keyword is used at the front of the substitution set, and all remaining
// letters will follow in order starting with the next letter.
// For example, given the keyword "funny", you would get:
// Plain: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
// Coded: F U N Y Z A B C D E G H I J K L M O P Q R S T V W X
// So the word "DRIVER" would become "YPETAP"


var KeywordShiftCrypto = (function() {

    return {
        name: 'The Keyword Shift Cipher',
        value : 'keywordShiftCipher',
        createSubstitutions : function(keyword) {

            var valueOfA = 'A'.charCodeAt(0);
            var substitutions = [];

            // Creates a deep copy of the array so we don't mess up the alphabet.
            var alphaCopy = alphabet.slice(0);

            // Walk through the key seed, and extract it's letters and put them at the front.
            var scrubbedKey = keyword.toUpperCase();
            for (var x = 0; x < scrubbedKey.length; x++) {
                var seedLetter = scrubbedKey.charAt(x);
                // Look to see if the next letter in the key is still available in our original alphabet.
                var letterIndex = alphaCopy.findIndex(function(val) {
                    return seedLetter == val;
                });
                // If found, add it to the substitution list.
                if (letterIndex >= 0 && letterIndex < alphaCopy.length) {
                    substitutions.push(alphaCopy.splice(letterIndex, 1)).toString();
                }
            }

            // Now using the last letter we found, find the next available
            // letter in the alphabet and use that to start.  So if our last
            // letter was "D", we'd probably start with "E" as the next letter in the
            // sequence.
            var lastLetter = scrubbedKey.charCodeAt(scrubbedKey.length - 1);
            var nextLetter = null;
            while (!nextLetter) {
                var nextLetterPos = (valueOfA + lastLetter + 1) % 26;
                var letterCandidate = alphabet[nextLetterPos];
                var pos = alphaCopy.indexOf(letterCandidate);
                if ( pos >= 0) {
                    alphaCopy.rotate(pos);
                    nextLetter = alphaCopy[0];
                }
                else {
                    lastLetter++;
                }
            }
            while (alphaCopy.length > 0) {
                // takes a letter out of "alphaCopy" and pushes it into substitution.
                substitutions.push(alphaCopy.splice(0,1).toString());
            }

            return substitutions;

        }
    };
}());
