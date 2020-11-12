package chess.model;

import chess.controller.Constants;

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
            if (indexLetter + i < 8)
                if (indexNumber + i < 8) {
                    if (borad[indexNumber + i][indexLetter + i].isEmpty()
                        || (borad[indexNumber + i][indexLetter + i].getPiece().isWhite() != isWhite())) {
                possibilities.add(ControlGame.numToLetter(indexLetter + i) + (indexNumber + i));
                }
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
