package chess.model;

import chess.controller.Constants;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(boolean isWhite) {
        super(Constants.KNIGHT_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        System.out.println("Like an L   ");
        return new ArrayList<>();
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
