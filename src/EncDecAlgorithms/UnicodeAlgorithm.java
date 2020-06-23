package src.EncDecAlgorithms;

class UnicodeAlgorithm implements IEncDecAlgorithm {

    public String encrypt(String msg, int key) {
        StringBuilder newMsg = new StringBuilder();
        int step = key % 95;    // 95 - number of modifiable chars

        for (char symbol : msg.toCharArray()) {
            char newSymbol = (char) (symbol + step);

            if (symbol >= 32 && symbol <= 126) {    // Modify only from ' ' to '~'
                if (symbol + step > 126) {  // Should we go around the circle or not?
                    newSymbol = (char) (newSymbol - 95);    // 95 - number of chars from ' ' to '~'
                }
            }
            else {
                newSymbol = symbol; // If symbol is NOT from ' ' to '~' then abort shifting
            }
            newMsg.append(newSymbol);
        }

        return newMsg.toString();
    }

    public String decrypt(String msg, int key) {
        StringBuilder newMsg = new StringBuilder();
        int step = key % 95;    // 95 - number of modifiable chars

        for (char symbol : msg.toCharArray()) {
            char newSymbol = (char) (symbol - step);

            if (symbol >= 32 && symbol <= 126) {    // Modify only from ' ' to '~'
                if (symbol - step < 32) {   // Should we go around the circle or not?
                    newSymbol = (char) (newSymbol + 95);    // 95 - number of chars from ' ' to '~'
                }
            }
            else {
                newSymbol = symbol; // If symbol is NOT from ' ' to '~' then abort shifting
            }
            newMsg.append(newSymbol);
        }

        return newMsg.toString();
    }
}
