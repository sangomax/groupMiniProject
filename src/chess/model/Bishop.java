package chess.model;

import chess.controller.Constants;
import chess.controller.Validation;
import chess.controller.ControlGame;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(Constants.BISHOP_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        ArrayList<String> possibilities = new ArrayList<>();
        int indexLetter = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
        int indexNumber = Integer.valueOf(position.substring(1, 2)) - 1;

        for (int i = 1; i < 8; i++) {
            int x = indexLetter + i;
            int y = indexNumber + i;
            while (x < 8 && x > -1 && y > -1 && y < 8) {
                if (!borad[indexNumber + 1][indexLetter + 1].isEmpty()
                    && borad[indexNumber + 1][indexLetter + 1].getPiece().isWhite() == isWhite()){
                    break;
                } else if (borad[indexNumber + i][indexLetter + i].isEmpty()) {
                    possibilities.add(ControlGame.numToLetter(indexLetter + i) + (indexNumber + i + 1));
                }
            break;
            }
        }
        return possibilities;
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return Constants.BISHOP_WHITE_PIECE + "\t";
        } else {
            return Constants.BISHOP_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)


    //  Method to return possible position of the selected piece
    //-> isValidMove
}
