package chess.model;

import chess.controller.Constants;

public class King extends Piece{

    public King(boolean isWhite) {
        super(Constants.KING_VALUE, isWhite);
    }

    @Override
    public void move() {
        System.out.println("One square");
    }

    @Override
    public String toString() {
        if(isWhite()) {
            return Constants.KING_WHITE_PIECE + "\t";
        } else {
            return Constants.KING_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)

    //  Method to return possible position of the selected piece
    //-> isValidMove
}
