package chess.model;

import chess.controller.Constants;

public class Bishop extends Piece{

    public Bishop(boolean isWhite) {
        super(Constants.BISHOP_VALUE, isWhite);
    }

    @Override
    public void move() {
        System.out.println("Diagonally");
    }

    @Override
    public String toString() {
        if(isWhite()) {
            return Constants.BISHOP_WHITE_PIECE + "\t";
        } else {
            return Constants.BISHOP_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)

    //  Method to return possible position of the selected piece
    //-> isValidMove
}
