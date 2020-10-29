package chess.view;

import chess.controller.Constants;
import chess.model.*;

import java.util.ArrayList;

public class BoardGame {


    public static void drawBoard(Position[][] arrayToDraw) {

        for (int row = arrayToDraw.length - 1; row >= 0; row--) {
            for (int col = 0; col < arrayToDraw[row].length; col++) {
                System.out.print(arrayToDraw[row][col].toString());
            }
            System.out.println(" " + (row + 1) );
        }
        System.out.println();
        System.out.println("A\tB\tC\tD\tE\tF\tG\tH");

    }


}
