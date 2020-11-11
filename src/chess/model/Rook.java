package chess.model;

import chess.controller.Constants;
import chess.controller.ControlGame;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(boolean isWhite) {
        super(Constants.ROOK_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        ArrayList<String> possibilities = new ArrayList<>();
        String[] temp = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int index = 0;
        for (int i = 0; i < 8; i++){
            if(temp[i].equals(position.substring(0,1))){
                index = i;
            }
        }
        String[] temp2 = {"1","2","3","4","5","6","7","8"};
        int index2 = 0;
        for (int i = 0; i < 8;i++){
            if(temp2[i].equals(position.substring(1,2))){
                index2 = i;
            }
        }

        if (isWhite()) {
            if (position.substring(0, 1).equals(temp[index])) {
                for (int i = 1; i < 8; i++) {
                    possibilities.add(position.substring(0, 1) + i);
                    if (borad[i][ControlGame.letterToNum(temp[index])] != borad[Integer.valueOf(position.substring(1, 2)) - 1][ControlGame.letterToNum(position.substring(0, 1))]
                            && !borad[i][ControlGame.letterToNum(temp[index])].isEmpty()) {
                        possibilities.remove(position);
                        break;
                    }
                }
            }
            if(position.substring(1,2).equals(temp2[index2])){
                for (int i = 0; i < 8; i++){
                    if (borad[Integer.valueOf(position.substring(1,2)) - 1][ControlGame.letterToNum(temp[i])] != borad[Integer.valueOf(position.substring(1,2)) -1][ControlGame.letterToNum(position.substring(0, 1))]
                            && !borad[Integer.valueOf(position.substring(1,2)) - 1][ControlGame.letterToNum(temp[i])].isEmpty()){
                        possibilities.remove(position);
                        break;
                    }
                    possibilities.add(temp[i] + position.substring(1,2));
                }
            }
        } else {
            if (position.substring(0, 1).equals(temp[index])) {

                for (int i = 7; i >= 0; i--) {
                    if (borad[i][ControlGame.letterToNum(temp[index])] != borad[Integer.valueOf(position.substring(1, 2)) - 1][ControlGame.letterToNum(position.substring(0, 1))]
                            && !borad[i][ControlGame.letterToNum(temp[index])].isEmpty()) {
                        possibilities.remove(position);
                        break;
                    }
                    possibilities.add(position.substring(0, 1) + (i + 1));
                }
            }
            if(position.substring(1, 2).equals(temp2[index2])){
                for (int i = 0; i < 8; i++){
                    if (borad[Integer.valueOf(position.substring(1,2)) - 1][ControlGame.letterToNum(temp[i])] != borad[Integer.valueOf(position.substring(1,2)) -1][ControlGame.letterToNum(position.substring(0, 1))]
                                && !borad[Integer.valueOf(position.substring(1,2)) - 1][ControlGame.letterToNum(temp[i])].isEmpty()){
                        possibilities.remove(position);
                        break;
                    }
                    possibilities.add(temp[i] + position.substring(1,2));
                }
            }
        }
        possibilities.remove(position);
        return possibilities;
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
