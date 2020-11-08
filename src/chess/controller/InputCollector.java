package chess.controller;

import java.util.Scanner;

public class InputCollector {
    // Method to take user input (take position as string)
    //isValidInput -> string length: 2 to 4, check letter is from a-h, 1-8
    public static String getUserInput(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt);
        return scan.nextLine();
    }

}
