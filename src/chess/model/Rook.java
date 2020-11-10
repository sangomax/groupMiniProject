package chess.model;

import chess.controller.Constants;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(boolean isWhite) {
        super(Constants.ROOK_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        System.out.println("Horizontally or vertically");
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        if(isWhite()) {
            return Constants.ROOK_WHITE_PIECE + "\t";
        } else {
            return Constants.ROOK_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)


    //  Method to return possible position of the selected piece
    //-> isValidMove
}
