// This randomly scrambles the letters in the alphabet.  This is a much stronger form
// of encryption than the Caesar and Keyword encryption algorithms because the resulting
// substitution alphabet does not have any letters in alphabetic order.
// It is important to note that for this to work, you need a "repeatable" random number generator.
//
// Plain: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
// Coded: K X N U O H A D Z L C T P W E Q S J F I G V Y R B M
var RandomizedCrypto = (function() {
    return {
        name: 'Randomized Substitution Cipher',
        value : 'randomizedSubstitution',
        createSubstitutions : function(seed) {

            Math.initRandomSeed(seed);
            var substitutions = [];

            // Creates a deep copy of the array so we don't mess up the alphabet.
            var alphaCopy = alphabet.slice(0);

            while (alphaCopy.length > 0) {
                // takes a letter out of "alphaCopy" and pushes it into substitution.
                substitutions.push(alphaCopy.splice(Math.seededRandom(alphaCopy.length),1).toString());
            }

            return substitutions;
        }
    };
}());

