package chess.model;

import chess.controller.Constants;
import chess.controller.ControlGame;

import java.util.ArrayList;

public class King extends Piece {

    public King(boolean isWhite) {
        super(Constants.KING_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] board) {

        ArrayList<String> possibilities = new ArrayList<>();

//        int[] pos = {Integer.parseInt(position.substring(1, 2)) - 1, ControlGame.letterToNum(position.substring(0, 1))}; // e4 -> [3][4]   ,   a1 -> [0][0]
//        boolean myColor = board[pos[0]][pos[1]].getPiece().isWhite();   // Own piece color
////
////        // 1. 上の列三コマ// (pos[0] + 1,  pos[1] - 1), (pos[0]+ 1 , pos[1] ), (pos[0] + 1, pos[1] + 1)
////        // pos[0] が7の場合は上の列はカウントなし
//        if (pos[0] != 7) {
//            possibilities.add(ControlGame.numToLetter(pos[1] - 1) + (pos[0] + 1));
//            possibilities.add(ControlGame.numToLetter(pos[1]) + (pos[0] + 1));
//            possibilities.add(ControlGame.numToLetter(pos[1] + 1) + (pos[0] + 1));
//        }
//
//        //2. 自分の左右のコマ
//        // pos[1]が7の場合 ->自分の左だけカウント (pos[0] , pos[1] -1)
//        // pos[1]が0の場合-> 自分の右だけカウント (pos[0], pos[1] + 1)
//        if(pos[1] == 7){
//
//        }
//



        //3. 下の列三コマ  (pos[0] - 1,  pos[1] - 1), (pos[0]-1 , pos[1]), (pos[0] - 1, pos[1] + 1)
        // pos[0]が0の場合、下の列はカウントなし




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
