package src;

import src.DataProcessing.*;
import src.EncDecAlgorithms.*;

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
        String newMsg;
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
