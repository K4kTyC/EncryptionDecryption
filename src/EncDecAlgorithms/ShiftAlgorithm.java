package src.EncDecAlgorithms;

class ShiftAlgorithm implements IEncDecAlgorithm {

    public String encrypt(String msg, int key) {
        StringBuilder newMsg = new StringBuilder();
        int step = key % 26;    // 26 - number of modifiable chars

        for (char symbol : msg.toCharArray()) {
            char newSymbol = (char) (symbol + step);

            if (symbol >= 65 && symbol <= 90) {   // Modify only from 'A' to 'Z'
                if (symbol + step > 90) {     // Should we go around the circle or not?
                    newSymbol = (char) (newSymbol - 26);  // 26 - number of chars from 'A' to 'Z'
                }
            } else if (symbol >= 97 && symbol <= 122) {   // Modify only from 'a' to 'z'
                if (symbol + step > 122) {
                    newSymbol = (char) (newSymbol - 26);
                }
            } else {
                newSymbol = symbol; // If symbol is NOT from 'A' to 'Z' or from 'a' to 'z' then abort shifting
            }
            newMsg.append(newSymbol);
        }

        return newMsg.toString();
    }

    public String decrypt(String msg, int key) {
        StringBuilder newMsg = new StringBuilder();
        int step = key % 26;    // 26 - number of modifiable chars

        for (char symbol : msg.toCharArray()) {
            char newSymbol = (char) (symbol - step);

            if (symbol >= 65 && symbol <= 90) { // Modify only from 'A' to 'Z'
                if (symbol - step < 65) {
                    newSymbol = (char) (newSymbol + 26);
                }
            } else if (symbol >= 97 && symbol <= 122) {  // Modify only from 'a' to 'z'
                if (symbol - step < 97) {     // Should we go around the circle or not?
                    newSymbol = (char) (newSymbol + 26);    // 26 - number of chars from 'a' to 'z'
                }
            } else {
                newSymbol = symbol; // If symbol is NOT from 'A' to 'Z' or from 'a' to 'z' then abort shifting
            }
            newMsg.append(newSymbol);
        }

        return newMsg.toString();
    }
}
