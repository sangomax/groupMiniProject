package chess.model;

import chess.controller.Constants;
import chess.controller.ControlGame;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(Constants.KNIGHT_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        ArrayList<String> possibilities = new ArrayList<>();
        int indexLetter = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
        int indexNumber = Integer.valueOf(position.substring(1, 2)) - 1;

        int y = 2;
        for (int x = 1; x < 3; x++) {
            if (indexLetter + y < 8) {
                if (indexNumber + x < 8) {
                    if (borad[indexNumber + x][indexLetter + y].isEmpty() ||
                            (borad[indexNumber + x][indexLetter + y].getPiece().isWhite() != isWhite())) {
                        possibilities.add(ControlGame.numToLetter(indexLetter + y) + (indexNumber + x + 1));
                    }
                }
                if (indexNumber - x >= 0) {
                    if (borad[indexNumber - x][indexLetter + y].isEmpty() ||
                            (borad[indexNumber - x][indexLetter + y].getPiece().isWhite() != isWhite())) {
                        possibilities.add(ControlGame.numToLetter(indexLetter + y) + (indexNumber - x + 1));
                    }
                }
            }
            if (indexLetter - y >= 0) {
                if (indexNumber + x < 8) {
                    if (borad[indexNumber + x][indexLetter - y].isEmpty() ||
                            (borad[indexNumber + x][indexLetter - y].getPiece().isWhite() != isWhite())) {
                        possibilities.add(ControlGame.numToLetter(indexLetter - y) + (indexNumber + x + 1));
                    }
                }
                if (indexNumber - x >= 0) {
                    if (borad[indexNumber - x][indexLetter - y].isEmpty() ||
                            (borad[indexNumber - x][indexLetter - y].getPiece().isWhite() != isWhite())) {
                        possibilities.add(ControlGame.numToLetter(indexLetter - y) + (indexNumber - x + 1));
                    }
                }
            }
            y--;
        }

        return possibilities;
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return Constants.KNIGHT_WHITE_PIECE + "\t";
        } else {
            return Constants.KNIGHT_BLACK_PIECE + "\t";
        }
    }
}
