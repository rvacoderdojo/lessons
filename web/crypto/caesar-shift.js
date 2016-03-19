// A Caesar Shift Cipher is one of the methods used by Julius Caesar to encode messages.
// It has existed for over 2 thousand years.
// The cipher is pretty simple.  The alphabet is shifted a certain number of letters
// to the left or right.  So for example if we were to use a shift of 2 to the right we'd get:
// Plain: A B C D E F G
// Coded: Y Z A B C D E
// So the word "BAG" would become "ZYE"

var CaesarCrypto = (function() {
    return {
        name: 'The Caesar Shift Cipher',
        value : 'caesarShiftCipher',
        createSubstitutions : function(shiftSize) {
            var substitutions = alphabet.slice(0);
            substitutions.rotate(shiftSize);
            
            return substitutions;
        }
    };
}());

