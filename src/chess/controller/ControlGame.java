package chess.controller;

import chess.model.*;

public class ControlGame {

    public static Position[][] startGame() {
        Position[][] board = new Position[8][8];

        for (int row = board.length - 1; row >= 0; row--) {

            if (row == 7) {
                board[row][0] = new Position(new Rook(Constants.BLACK_PIECE));
                board[row][1] = new Position(new Knight(Constants.BLACK_PIECE));
                board[row][2] = new Position(new Bishop(Constants.BLACK_PIECE));
                board[row][3] = new Position(new Queen(Constants.BLACK_PIECE));
                board[row][4] = new Position(new King(Constants.BLACK_PIECE));
                board[row][5] = new Position(new Bishop(Constants.BLACK_PIECE));
                board[row][6] = new Position(new Knight(Constants.BLACK_PIECE));
                board[row][7] = new Position(new Rook(Constants.BLACK_PIECE));
            } else if(row == 6) {
                for(int i = 0; i < 8; i ++) {
                    board[row][i] = new Position(new Pawn(Constants.BLACK_PIECE, false, null));
                }
            } else if (row > 1) {
                for(int i = 0; i < 8; i ++) {
                    board[row][i] = new Position(null);
                }
            } else if(row == 1) {
                for(int i = 0; i < 8; i ++) {
                    board[row][i] = new Position(new Pawn(Constants.WHITE_PIECE, false, null));
                }
            } else if (row == 0) {
                board[row][0] = new Position(new Rook(Constants.WHITE_PIECE));
                board[row][1] = new Position(new Knight(Constants.WHITE_PIECE));
                board[row][2] = new Position(new Bishop(Constants.WHITE_PIECE));
                board[row][3] = new Position(new Queen(Constants.WHITE_PIECE));
                board[row][4] = new Position(new King(Constants.WHITE_PIECE));
                board[row][5] = new Position(new Bishop(Constants.WHITE_PIECE));
                board[row][6] = new Position(new Knight(Constants.WHITE_PIECE));
                board[row][7] = new Position(new Rook(Constants.WHITE_PIECE));
            }
        }
        return board;
    }

}
