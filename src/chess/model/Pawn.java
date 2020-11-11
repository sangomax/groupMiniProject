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

    @Override
    public ArrayList<String> move(String position, Position[][] borad) {
        ArrayList<String> possibilitys = new ArrayList<>();
        int indexLetter = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
        int indexNumber = Integer.valueOf(position.substring(1, 2)) - 1;

        if (!isPromoted()) {
            if (isWhite()) {
                if (position.substring(1, 2).equals("2")) {
                    if (borad[indexNumber + 1][indexLetter].isEmpty() &&
                            borad[indexNumber + 2][indexLetter].isEmpty()) {
                        possibilitys.add(position.substring(0, 1) + 4);
                    }
                }
                if (!position.substring(1, 2).equals("8")) {
                    if (borad[indexNumber + 1][indexLetter].isEmpty()) {
                        possibilitys.add(position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                    }
                    if (position.substring(0, 1).equals("a") && !borad[indexNumber + 1][ControlGame.letterToNum("b")].isEmpty() && (borad[indexNumber + 1][ControlGame.letterToNum("b")].getPiece().isWhite() != isWhite())) {
                        possibilitys.add("b" + (Integer.valueOf(position.substring(1, 2)) + 1));

                    } else if (position.substring(0, 1).equals("h") && !borad[indexNumber + 1][ControlGame.letterToNum("g")].isEmpty() && (borad[indexNumber + 1][ControlGame.letterToNum("g")].getPiece().isWhite() != isWhite())) {
                        possibilitys.add("g" + (Integer.valueOf(position.substring(1, 2)) + 1));

                    } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1).equals("a")) {

                        if (!borad[indexNumber + 1][indexLetter + 1].isEmpty() && (borad[indexNumber + 1][indexLetter + 1].getPiece().isWhite() != isWhite())) {
                            possibilitys.add(ControlGame.numToLetter(indexLetter + 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                        }
                        if (!borad[indexNumber + 1][indexLetter - 1].isEmpty() && (borad[indexNumber + 1][indexLetter - 1].getPiece().isWhite() != isWhite())) {
                            possibilitys.add(ControlGame.numToLetter(indexLetter - 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                        }
                    }
                }
            } else {
                if (position.substring(1, 2).equals("7")) {
                    if (borad[indexNumber - 1][indexLetter].isEmpty() &&
                            borad[indexNumber - 2][indexLetter].isEmpty()) {
                        possibilitys.add(position.substring(0, 1) + 5);
                    }
                }
                if (!position.substring(1, 2).equals("1")) {
                    if (borad[indexNumber - 1][indexLetter].isEmpty()) {
                        possibilitys.add(position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                    }
                    if (position.substring(0, 1).equals("a") && !borad[indexNumber - 1][ControlGame.letterToNum("b")].isEmpty() && (borad[indexNumber - 1][ControlGame.letterToNum("b")].getPiece().isWhite() != isWhite())) {
                        possibilitys.add("b" + (Integer.valueOf(position.substring(1, 2)) - 1));

                    } else if (position.substring(0, 1).equals("h") && !borad[indexNumber - 1][ControlGame.letterToNum("g")].isEmpty() && (borad[indexNumber - 1][ControlGame.letterToNum("g")].getPiece().isWhite() != isWhite())) {
                        possibilitys.add("g" + (Integer.valueOf(position.substring(1, 2)) - 1));

                    } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1).equals("a")) {
                        if (!borad[indexNumber - 1][indexLetter + 1].isEmpty() && (borad[indexNumber - 1][indexLetter + 1].getPiece().isWhite() != isWhite())) {
                            possibilitys.add(ControlGame.numToLetter(indexLetter + 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                        }
                        if (!borad[indexNumber - 1][indexLetter - 1].isEmpty() && (borad[indexNumber - 1][indexLetter - 1].getPiece().isWhite() != isWhite())) {
                            possibilitys.add(ControlGame.numToLetter(indexLetter - 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                        }
                    }
                }

            }
        } else {
            possibilitys = getNewPiece().move(position, borad);
        }
        return possibilitys;
    }


    // Boolean -> isValid method(current position, new position)

    //  Method to return possible position of the selected piece
    //-> isValidMove

}
