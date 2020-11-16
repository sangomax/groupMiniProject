package chess.model;

import chess.controller.Constants;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(Constants.QUEEN_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        ArrayList<String> possibilities = new ArrayList<>();
        Piece tempRook = new Rook(isWhite());
        Piece tempBishop = new Bishop(isWhite());

        possibilities.addAll(tempRook.move(position, borad));
        possibilities.addAll(tempBishop.move(position, borad));

        return possibilities;
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return Constants.QUEEN_WHITE_PIECE + "\t";
        } else {
            return Constants.QUEEN_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)


    //  Method to return possible position of the selected piece
    //-> isValidMove
}
