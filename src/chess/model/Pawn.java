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
        if (isWhite()) {
            return Constants.PAWN_WHITE_PIECE + "\t";
        } else {
            return Constants.PAWN_BLACK_PIECE + "\t";
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
        if (isWhite()) {
            if (position.substring(1, 2).equals("2")) {
                possibilitys.add(position.substring(0, 1) + 4);
            }
            if (!position.substring(1, 2).equals("8")) {
                possibilitys.add(position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2)) + 1));

                if (position.substring(0, 1).equals("a") && !borad[Integer.valueOf(position.substring(1, 2))][ControlGame.letterToNum("b")].isEmpty()) {
                    possibilitys.add("b" + (Integer.valueOf(position.substring(1, 2)) + 1));

                } else if (position.substring(0, 1).equals("h") && !borad[Integer.valueOf(position.substring(1, 2))][ControlGame.letterToNum("h")].isEmpty()) {
                    possibilitys.add("g" + (Integer.valueOf(position.substring(1, 2)) + 1));

                } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1).equals("a")){
                    int pos = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
                    if(!borad[Integer.valueOf(position.substring(1, 2))][pos + 1].isEmpty()) {
                        possibilitys.add(ControlGame.numToLetter(pos + 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                    }
                    if(!borad[Integer.valueOf(position.substring(1, 2))][pos - 1].isEmpty()) {
                        possibilitys.add(ControlGame.numToLetter(pos - 1) + (Integer.valueOf(position.substring(1, 2)) + 1));
                    }
                }
            }
        } else {
            if (position.substring(1, 2).equals("7")) {
                possibilitys.add(position.substring(0, 1) + 5);
            }
            if (!position.substring(1, 2).equals("1")) {
                possibilitys.add(position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2)) - 1));

                if (position.substring(0, 1).equals("a") && !borad[Integer.valueOf(position.substring(1, 2))][ControlGame.letterToNum("b")].isEmpty()) {
                    possibilitys.add("b" + (Integer.valueOf(position.substring(1, 2)) - 1));

                } else if (position.substring(0, 1).equals("h") && !borad[Integer.valueOf(position.substring(1, 2))][ControlGame.letterToNum("h")].isEmpty()) {
                    possibilitys.add("g" + (Integer.valueOf(position.substring(1, 2)) - 1));

                } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1).equals("a")){
                    int pos = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
                    if(!borad[Integer.valueOf(position.substring(1, 2))][pos + 1].isEmpty()) {
                        possibilitys.add(ControlGame.numToLetter(pos + 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                    }
                    if(!borad[Integer.valueOf(position.substring(1, 2))][pos - 1].isEmpty()) {
                        possibilitys.add(ControlGame.numToLetter(pos - 1) + (Integer.valueOf(position.substring(1, 2)) - 1));
                    }
                }
            }

        }
        return possibilitys;
    }


    // Boolean -> isValid method(current position, new position)

    //  Method to return possible position of the selected piece
    //-> isValidMove

}
