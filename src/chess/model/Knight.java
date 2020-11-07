package chess.model;

import chess.controller.Constants;

public class Knight extends Piece{

    public Knight(boolean isWhite) {
        super(Constants.KNIGHT_VALUE, isWhite);
    }

    @Override
    public void move() {
        System.out.println("Like an L   ");
    }

    @Override
    public String toString() {
        if(isWhite()) {
            return Constants.KNIGHT_WHITE_PIECE + "\t";
        } else {
            return Constants.KNIGHT_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)

    //  Method to return possible position of the selected piece
    //-> isValidMove
}
