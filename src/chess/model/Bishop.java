package chess.model;

import chess.controller.Constants;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(Constants.BISHOP_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        System.out.println("Diagonally");
        return new ArrayList<>();
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
