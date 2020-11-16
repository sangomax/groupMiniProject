package chess.model;

import chess.controller.Constants;
import chess.controller.ControlGame;
import java.util.ArrayList;

public class King extends Piece {

    public King(boolean isWhite) {
        super(Constants.KING_VALUE, isWhite);
    }


    public int isCastleOK (boolean isWhite, Position [][] board){
        try{
        if (isWhite && board[0][4].getPiece().getValue() == Constants.KING_VALUE && board[0][4].getPiece().isWhite()
                && board[0][7].getPiece().getValue() == Constants.ROOK_VALUE && board[0][7].getPiece().isWhite()
                && board[0][5].isEmpty() && board[0][6].isEmpty()){
                 return 1;
        } else if (isWhite && board[0][4].getPiece().getValue() == Constants.KING_VALUE && board[0][4].getPiece().isWhite()
                && board[0][0].getPiece().getValue() == Constants.ROOK_VALUE && board[0][0].getPiece().isWhite()
                && board[0][1].isEmpty() && board[0][2].isEmpty() && board[0][3].isEmpty()){
            return 2;
        } else if (!isWhite && board[7][4].getPiece().getValue() == Constants.KING_VALUE && !board[7][4].getPiece().isWhite()
                && board[7][0].getPiece().getValue() == Constants.ROOK_VALUE && !board[7][0].getPiece().isWhite() && board[7][1].isEmpty()
                && board[7][2].isEmpty() && board[7][3].isEmpty()){
              return 3;
        } else if (!isWhite && board[7][4].getPiece().getValue() == Constants.KING_VALUE && !board[7][4].getPiece().isWhite()
                && board[7][7].getPiece().getValue() == Constants.ROOK_VALUE && !board[7][7].getPiece().isWhite()
                && board[7][5].isEmpty() && board[7][6].isEmpty()) {
            return 4;
        } else {
            return 5;
            }
        } catch (NullPointerException e){
            return 5;
        }
    }


    @Override
    public ArrayList<String> move(String position, Position[][] board) {
        ArrayList<String> possibilities = new ArrayList<>();
        int[] pos = {Integer.parseInt(position.substring(1, 2)) - 1, ControlGame.letterToNum(position.substring(0, 1))};
        int y = pos[0];
        int x = pos[1];
        boolean myColor = board[pos[0]][pos[1]].getPiece().isWhite();

        if (isCastleOK(myColor, board) == 1) {
            for (int i = 0; i < 2; i++){
                for (int j = 3 ; j < 6 ; j++){
                    if (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor){
                        possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                    }
                }
            }
            possibilities.add("g1");
        } else if (isCastleOK(myColor, board) == 2) {
            for (int i = 0; i < 2; i++){
                for (int j = 3 ; j < 6 ; j++){
                    if (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor){
                        possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                    }
                }
            }
            possibilities.add("c1");
        } else if (isCastleOK(myColor, board) == 3) {
            for (int i = 7 ; i > 5; i--){
                for (int j = 3; j < 6; j++){
                    if (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor){
                        possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                    }
                }
            }
            possibilities.add("c8");
        } else if (isCastleOK(myColor, board) == 4) {
            for (int i = 7 ; i > 5; i--){
                for (int j = 3; j < 6; j++){
                    if (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor){
                        possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                    }
                }
            }
            possibilities.add("g8");
        } else if (isCastleOK(myColor,board) == 5) {

            //e4 -> 34(yx)   44
            if ((y != 0 && y != 7) && (x != 0 && x != 7)) {
                for (int i = y - 1; i < y + 2; i++) {
                    for (int j = x - 1; j < x + 2; j++) {
                        if (board[i][j] != board[pos[0]][pos[1]] && (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor)) {
                            possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                        }
                    }
                }
            }

            if (y == 7) {
                if (x == 7) {
                    for (int i = y - 1; i < y + 1; i++) {
                        for (int j = x - 1; j < x + 1; j++) {
                            if (board[i][j] != board[pos[0]][pos[1]] && (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor)) {
                                possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                            }
                        }
                    }
                } else if (x == 0) {
                    for (int i = y - 1; i < y + 1; i++) {
                        for (int j = x; j < x + 2; j++) {
                            if (board[i][j] != board[pos[0]][pos[1]] && (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor)) {
                                possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                            }
                        }
                    }
                } else {
                    for (int i = y - 1; i < y + 1; i++) {
                        for (int j = x - 1; j < x + 2; j++) {
                            if (board[i][j] != board[pos[0]][pos[1]] && (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor)) {
                                possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                            }
                        }
                    }
                }
            }
            if (y == 0) {
                if (x == 7) {
                    for (int i = y; i < y + 1; i++) {
                        for (int j = x - 1; j < x + 1; j++) {
                            if (board[i][j] != board[pos[0]][pos[1]] && (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor)) {
                                possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                            }
                        }
                    }
                } else if (x == 0) {
                    for (int i = y; i < y + 1; i++) {
                        for (int j = x; j < x + 2; j++) {
                            if (board[i][j] != board[pos[0]][pos[1]] && (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor)) {
                                possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                            }
                        }
                    }
                } else {
                    for (int i = y; i < y + 2; i++) {
                        for (int j = x - 1; j < x + 2; j++) {
                            if (board[i][j] != board[pos[0]][pos[1]] && (board[i][j].isEmpty() || board[i][j].getPiece().isWhite() != myColor)) {
                                possibilities.add(ControlGame.numToLetter(j) + (i + 1));
                            }
                        }
                    }
                }
            }
        }
        return possibilities;
    }


    @Override
    public String toString() {
        if (isWhite()) {
            return Constants.KING_WHITE_PIECE + "\t";
        } else {
            return Constants.KING_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)

    //  Method to return possible position of the selected piece
    //-> isValidMove
}
