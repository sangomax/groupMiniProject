package chess.model;

import chess.controller.Constants;
import chess.controller.ControlGame;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean promoted;

    private Piece newPiece;

    public Pawn(boolean isWhite, boolean promoted, Piece newPiece) {
        super(Constants.PAWN_VALUE, isWhite);
        if (promoted) {
            promote(newPiece);
        } else {
            this.promoted = promoted;
            this.newPiece = null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Pawn pawn = (Pawn) obj;
        return this.getValue() == pawn.getValue() &&
            this.isWhite() == pawn.isWhite() &&
            this.isPromoted() == pawn.isPromoted() &&
            (
                (this.isPromoted() == false &&
                    (this.getNewPiece() == null && pawn.newPiece == null)
                ) || (this.isPromoted() == true &&
                    (this.getNewPiece() != null && pawn.newPiece != null) &&
                    (this.getNewPiece().equals(pawn.newPiece))
                )
            );
    }

    @Override
    public String toString() {
        if (!isPromoted()) {
            if (isWhite()) {
                return Constants.PAWN_WHITE_PIECE + "\t";
            } else {
                return Constants.PAWN_BLACK_PIECE + "\t";
            }
        } else {
            return getNewPiece().toString();
        }
    }

    public void promote(Piece newPiece) {
        if (newPiece != null && newPiece.getValue() != 1000 && newPiece.getValue() != 1) {
            setPromoted(true);
            setNewPiece(newPiece);
        }
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public Piece getNewPiece() {
        return newPiece;
    }

    public void setNewPiece(Piece newPiece) {
        this.newPiece = newPiece;
    }

    private boolean canCaptureEnPassant(String position, Position[][] board) {
        int[] index = ControlGame.convertPosition(position);
        int x = index[1];
        int y = index[0];

        if (!board[y][x + 1].isEmpty())
            if (board[y + 1][x + 1].isEmpty()
                && board[y][x + 1].getPiece() instanceof Pawn
                && board[y][x + 1].getPiece().isWhite() != isWhite())
                return true;

            else if (!board[y][x - 1].isEmpty());
        if (board[y - 1][x - 1].isEmpty()
            && board[y][x - 1].getPiece() instanceof Pawn
            && board[y][x - 1].getPiece().isWhite() != isWhite())
            return true;

        return false;
    }

    @Override
    public ArrayList<String> move(String position, Position[][] board) {
        ArrayList<String> possibilities = new ArrayList<>();
        int[] index = ControlGame.convertPosition(position);
        int indexLetter = index[1];
        int indexNumber = index[0];

        if (!isPromoted()) {
            if (isWhite()) {
                if (position.substring(1, 2).equals("2")) {
                    if (board[indexNumber + 1][indexLetter].isEmpty() &&
                        board[indexNumber + 2][indexLetter].isEmpty()) {
                        possibilities.add(position.substring(0, 1) + 4);
                    }
                }
                if (position.substring(1, 2).equals("5")) {
                    if (canCaptureEnPassant(position, board)) {
                        if (!board[indexNumber][indexLetter + 1].isEmpty()){
                            possibilities.add(ControlGame.numToLetter(indexLetter + 1) + (indexNumber + 2));
                        }
                        if (!board[indexNumber][indexLetter - 1].isEmpty()){
                            possibilities.add(ControlGame.numToLetter(indexLetter - 1) + (indexNumber + 2));
                        }
                    }
                }
                if (!position.substring(1, 2).equals("8")) {
                    if (board[indexNumber + 1][indexLetter].isEmpty()) {
                        possibilities.add(position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                    }
                    if (position.substring(0, 1).equals("a") && !board[indexNumber + 1][ControlGame.letterToNum("b")].isEmpty() && (board[indexNumber + 1][ControlGame.letterToNum("b")].getPiece().isWhite() != isWhite())) {
                        possibilities.add("b" + (Integer.valueOf(position.substring(1, 2)) + 1));

                    } else if (position.substring(0, 1).equals("h") && !board[indexNumber + 1][ControlGame.letterToNum("g")].isEmpty() && (board[indexNumber + 1][ControlGame.letterToNum("g")].getPiece().isWhite() != isWhite())) {
                        possibilities.add("g" + (Integer.valueOf(position.substring(1, 2)) + 1));

                    } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1).equals("a")) {

                        if (!board[indexNumber + 1][indexLetter + 1].isEmpty() && (board[indexNumber + 1][indexLetter + 1].getPiece().isWhite() != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter + 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                        }
                        if (!board[indexNumber + 1][indexLetter - 1].isEmpty() && (board[indexNumber + 1][indexLetter - 1].getPiece().isWhite() != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter - 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                        }
                    }
                }
            } else {
                if (position.substring(1, 2).equals("7")) {
                    if (board[indexNumber - 1][indexLetter].isEmpty() &&
                        board[indexNumber - 2][indexLetter].isEmpty()) {
                        possibilities.add(position.substring(0, 1) + 5);
                    }
                }
                if (position.substring(1, 2).equals("4")) {
                    if (canCaptureEnPassant(position, board)) {
                        if (!board[indexNumber][indexLetter + 1].isEmpty()){
                            possibilities.add(ControlGame.numToLetter(indexLetter + 1) + (indexNumber));
                        }
                        if (!board[indexNumber][indexLetter - 1].isEmpty()){
                            possibilities.add(ControlGame.numToLetter(indexLetter - 1) + (indexNumber));
                        }
                    }
                }
                if (!position.substring(1, 2).equals("1")) {
                    if (board[indexNumber - 1][indexLetter].isEmpty()) {
                        possibilities.add(position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                    }
                    if (position.substring(0, 1).equals("a") && !board[indexNumber - 1][ControlGame.letterToNum("b")].isEmpty() && (board[indexNumber - 1][ControlGame.letterToNum("b")].getPiece().isWhite() != isWhite())) {
                        possibilities.add("b" + (Integer.valueOf(position.substring(1, 2)) - 1));

                    } else if (position.substring(0, 1).equals("h") && !board[indexNumber - 1][ControlGame.letterToNum("g")].isEmpty() && (board[indexNumber - 1][ControlGame.letterToNum("g")].getPiece().isWhite() != isWhite())) {
                        possibilities.add("g" + (Integer.valueOf(position.substring(1, 2)) - 1));

                    } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1).equals("a")) {
                        if (!board[indexNumber - 1][indexLetter + 1].isEmpty() && (board[indexNumber - 1][indexLetter + 1].getPiece().isWhite() != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter + 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                        }
                        if (!board[indexNumber - 1][indexLetter - 1].isEmpty() && (board[indexNumber - 1][indexLetter - 1].getPiece().isWhite() != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter - 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                        }
                    }
                }

            }
        } else {
            possibilities = getNewPiece().move(position, board);
        }
        return possibilities;
    }


    // Boolean -> isValid method(current position, new position)

    //  Method to return possible position of the selected piece
    //-> isValidMove

}