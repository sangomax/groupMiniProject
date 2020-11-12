package chess.model;

import chess.controller.Constants;
import chess.controller.ControlGame;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(Constants.ROOK_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] board) {
        ArrayList<String> possibilities = new ArrayList<>();

        int[] pos = {Integer.parseInt(position.substring(1, 2)) - 1, ControlGame.letterToNum(position.substring(0, 1))}; // e4 -> [3][4]   ,   a1 -> [0][0]
        boolean myColor = board[pos[0]][pos[1]].getPiece().isWhite();   // Own piece color

        for (int i = pos[0] ; i < 8; i++){                              // possibilities forward
                if (!board[i][pos[1]].isEmpty()
                        && board[i][pos[1]] != board[pos[0]][pos[1]]
                        && board[i][pos[1]].getPiece().isWhite() == myColor){       //square is occupied by own color piece
                    break;
                }
                if(!board[i][pos[1]].isEmpty() && board[i][pos[1]].getPiece().isWhite() != myColor){        //square is occupied by opponent piece
                    possibilities.add(ControlGame.numToLetter(pos[1]) + (i + 1));
                    break;
                }
                if(board[i][pos[1]] == board[pos[0]][pos[1]]){
                    continue;
                } else {
                    possibilities.add((ControlGame.numToLetter(pos[1])) + (i + 1));
                }
        }

        for (int i = pos[0] ; i >= 0; i--){                             // possibilities backward
            if (!board[i][pos[1]].isEmpty()
                    && board[i][pos[1]] != board[pos[0]][pos[1]]
                    && board[i][pos[1]].getPiece().isWhite() == myColor){
                break;
            }
            if (!board[i][pos[1]].isEmpty() && board[i][pos[1]].getPiece().isWhite() != myColor){
                possibilities.add(ControlGame.numToLetter(pos[1]) + (i + 1));
                break;
            }
            if (board[i][pos[1]] == board[pos[0]][pos[1]]){
                continue;
            } else {
                possibilities.add(ControlGame.numToLetter(pos[1]) + (i + 1));
            }
        }

        for (int i = pos[1]; i < 8; i++){                               // possibilities on right
            if(!board[pos[0]][i].isEmpty()
                    && board[pos[0]][i] != board[pos[0]][pos[1]]
                    &&board[pos[0]][i].getPiece().isWhite() == myColor){
                break;
            }

            if(!board[pos[0]][i].isEmpty() && board[pos[0]][i].getPiece().isWhite() != myColor){
                possibilities.add( ControlGame.numToLetter(i) + (pos[0] + 1) );
                break;
            }
            if(board[pos[0]][i] == board[pos[0]][pos[1]]){
                continue;
            } else{
                possibilities.add( ControlGame.numToLetter(i) + (pos[0] + 1) );
            }
        }

        for (int i = pos[1] ; i >= 0 ; i--){                        // possibilities on left
            if(!board[pos[0]][i].isEmpty()
                    && board[pos[0]][i] != board[pos[0]][pos[1]]
                    && board[pos[0]][i].getPiece().isWhite() == myColor){
                break;
            }

            if(!board[pos[0]][i].isEmpty() && board[pos[0]][i].getPiece().isWhite() != myColor){
                possibilities.add(ControlGame.numToLetter(i) + (pos[0] + 1));
                break;
            }
            possibilities.add(ControlGame.numToLetter(i) + (pos[0] + 1));
        }
        possibilities.remove(position);
        return possibilities;
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return Constants.ROOK_WHITE_PIECE + "\t";
        } else {
            return Constants.ROOK_BLACK_PIECE + "\t";
        }
    }

    // Boolean -> isValid method(current position, new position)


    //  Method to return possible position of the selected piece
    //-> isValidMove
}
