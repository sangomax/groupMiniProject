package chess.model;

import chess.controller.Constants;

public class Queen extends Piece{

    public Queen(boolean isWhite) {
        super(Constants.QUEEN_VALUE, isWhite);
    }

    @Override
    public void move() {
        System.out.println("Like bishop and rook");
    }

    @Override
    public String toString() {
        if(isWhite()) {
            return Constants.QUEEN_WHITE_PIECE + "\t";
        } else {
            return Constants.QUEEN_BLACK_PIECE + "\t";
        }
    }
}
