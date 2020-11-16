package chess.model;

import chess.controller.Constants;
import chess.controller.ControlGame;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(Constants.BISHOP_VALUE, isWhite);
    }

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        ArrayList<String> possibilities = new ArrayList<>();
        int indexLetter = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
        int indexNumber = Integer.valueOf(position.substring(1, 2)) - 1;

        for (int i = 1; i < 8; i++) {
            int x = indexLetter + i;
            int y = indexNumber + i;
            while (x < 8 && x > -1 && y > -1 && y < 8
                    && (borad[y][x].isEmpty()
                    || borad[y][x].getPiece().isWhite() != isWhite())) {
                possibilities.add(ControlGame.numToLetter(x) + (y + 1));
                break;
            }
            if (x + 1 > 7
                    || y + 1 > 7
                    || !borad[y][x].isEmpty()) {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            int x = indexLetter - i;
            int y = indexNumber + i;
            while (x < 8 && x > -1 && y > -1 && y < 8
                    && (borad[y][x].isEmpty()
                    || borad[y][x].getPiece().isWhite() != isWhite())) {
                possibilities.add(ControlGame.numToLetter(x) + (y + 1));
                break;
            }
            if (x - 1 < 0
                    || y + 1 > 7
                    || !borad[y][x].isEmpty()) {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            int x = indexLetter + i;
            int y = indexNumber - i;
            while (x < 8 && x > -1 && y > -1 && y < 8
                    && (borad[y][x].isEmpty()
                    || borad[y][x].getPiece().isWhite() != isWhite())) {
                possibilities.add(ControlGame.numToLetter(x) + (y + 1));
                break;
            }
            if (x + 1 > 7
                    || y - 1 < 0
                    || (!borad[y][x].isEmpty())) {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            int x = indexLetter - i;
            int y = indexNumber - i;
            while (x < 8 && x > -1 && y > -1 && y < 8
                    && (borad[y][x].isEmpty()
                    || borad[y][x].getPiece().isWhite() != isWhite())) {
                possibilities.add(ControlGame.numToLetter(x) + (y + 1));
                break;
            }
            if (x - 1 < 0
                    || y - 1 < 0
                    || !borad[y][x].isEmpty()) {
                break;
            }
        }

        return possibilities;
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return Constants.BISHOP_WHITE_PIECE + "\t";
        } else {
            return Constants.BISHOP_BLACK_PIECE + "\t";
        }
    }
}
