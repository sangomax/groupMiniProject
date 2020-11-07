package chess.controller;

import chess.model.*;

public class ControlGame {

    public static Position[][] startGame() {
        Position[][] board = new Position[8][8];
        char letter = 102;
        for (int row = board.length - 1; row >= 0; row--) {

            if (row == 7) {
                board[row][0] = new Position(new Rook(Constants.BLACK_PIECE),Constants.H1);
                board[row][1] = new Position(new Knight(Constants.BLACK_PIECE),Constants.H2);
                board[row][2] = new Position(new Bishop(Constants.BLACK_PIECE),Constants.H3);
                board[row][3] = new Position(new Queen(Constants.BLACK_PIECE),Constants.H4);
                board[row][4] = new Position(new King(Constants.BLACK_PIECE),Constants.H5);
                board[row][5] = new Position(new Bishop(Constants.BLACK_PIECE),Constants.H6);
                board[row][6] = new Position(new Knight(Constants.BLACK_PIECE),Constants.H7);
                board[row][7] = new Position(new Rook(Constants.BLACK_PIECE),Constants.H8);
            } else if(row == 6) {
                for(int i = 0; i < 8; i ++) {
                    board[row][i] = new Position(new Pawn(Constants.BLACK_PIECE, false, null),"g"+(i+1));
                }
            } else if (row > 1) {
                for(int i = 0; i < 8; i ++) {
                    board[row][i] = new Position(null,String.valueOf(letter) + (i + 1));
                }
                letter--;
            } else if(row == 1) {
                for(int i = 0; i < 8; i ++) {
                    board[row][i] = new Position(new Pawn(Constants.WHITE_PIECE, false, null),"b"+(i+1));
                }
            } else if (row == 0) {
                board[row][0] = new Position(new Rook(Constants.WHITE_PIECE),Constants.A1);
                board[row][1] = new Position(new Knight(Constants.WHITE_PIECE),Constants.A2);
                board[row][2] = new Position(new Bishop(Constants.WHITE_PIECE),Constants.A3);
                board[row][3] = new Position(new Queen(Constants.WHITE_PIECE),Constants.A4);
                board[row][4] = new Position(new King(Constants.WHITE_PIECE),Constants.A5);
                board[row][5] = new Position(new Bishop(Constants.WHITE_PIECE),Constants.A6);
                board[row][6] = new Position(new Knight(Constants.WHITE_PIECE),Constants.A7);
                board[row][7] = new Position(new Rook(Constants.WHITE_PIECE),Constants.A8);
            }
        }
        return board;
    }
    // Generic method: return possible position of the selected piece

}
