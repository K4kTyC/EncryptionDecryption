package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean srcFile = true;
        boolean outFile = false;
        String mode = "enc";
        IEncDecAlgorithm algorithm = new ShiftAlgorithm();
        StringBuilder data = new StringBuilder();
        String newMsg = "";
        int key = 0;
        File fin = null;
        File fout = null;

        try {
            for (int i = 0; i < args.length; i += 2) {
                if ("-mode".equals(args[i])) {
                    mode = args[i + 1];
                }
                if ("-alg".equals(args[i])) {
                    if ("unicode".equals(args[i + 1])) {
                        algorithm = new UnicodeAlgorithm();
                    }
                }
                if ("-key".equals(args[i])) {
                    key = Integer.parseInt(args[i + 1]);
                }
                if ("-data".equals(args[i])) {
                    data.append(args[i + 1]);
                    srcFile = false;
                }
                if ("-in".equals(args[i])) {
                    fin = new File(args[i + 1]);
                }
                if ("-out".equals(args[i])) {
                    fout = new File(args[i + 1]);
                    outFile = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        if (srcFile) {
            try (Scanner scanner = new Scanner(fin)) {
                while (scanner.hasNext()) {
                    data.append(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        DataProcessing dp = new Encrypting(algorithm);
        if ("dec".equals(mode)) {
            dp = new Decrypting(algorithm);
        }
        newMsg = dp.processData(data.toString(), key);

        if (outFile) {
            try (FileWriter writer = new FileWriter(fout)) {
                writer.write(newMsg);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else {
            System.out.println(newMsg);
        }
    }
}

interface IEncDecAlgorithm {
    String encrypt(String msg, int key);
    String decrypt(String msg, int key);
}

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

abstract class DataProcessing {

    protected IEncDecAlgorithm algorithm;

    public DataProcessing(IEncDecAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    protected abstract String processData(String data, int key);
}

class Encrypting extends DataProcessing {

    public Encrypting(IEncDecAlgorithm algorithm) {
        super(algorithm);
    }

    protected String processData(String data, int key) {
        return algorithm.encrypt(data, key);
    }
}

class Decrypting extends DataProcessing {

    public Decrypting(IEncDecAlgorithm algorithm) {
        super(algorithm);
    }

    protected String processData(String data, int key) {
        return algorithm.decrypt(data, key);
    }
}