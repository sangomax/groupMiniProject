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

    /**
     * Adds moves in which the pawn captures "en passant" to the list of moves
     * @param moves the list of moves
     * 最終的には、possibilitiesに候補をaddする　→　そこに移動した場合は、その後ろのpawnを取り除く。
     * お互いのPawnが、4か5にいるときにしか発生し得ない
     * その上で、隣がPawnかどうか確認する
     * 移動するpossibilitiesは、斜め前
     * 掴むピースは横のpawn
     * 残像は、ポーンが２マス進んだ直後にだけ現れる。
     */
    private void enPassant(String position, Position[][] borad) {
        ArrayList<String> possibilities = new ArrayList<>();
        int x = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
        int y = Integer.valueOf(position.substring(1, 2)) - 1;

        int[] pos = {Integer.parseInt(position.substring(1, 2)) - 1, ControlGame.letterToNum(position.substring(0, 1))};
        boolean myColor = borad[pos[0]][pos[1]].getPiece().isWhite();

        if (this.isWhite() == myColor && position.substring(1, 2).equals("2")) {
            if(canCaptureEnPassant(position.substring(1, 2), borad))
                possibilities.add("b" + (Integer.valueOf(position.substring(1, 2)) + 1));
                    borad[y][x - 1].getPiece();
            if(canCaptureEnPassant(position.substring(1, 2), borad))
                possibilities.add("a" + (Integer.valueOf(position.substring(1, 2)) + 1));
                    borad[y][x + 1].getPiece();
        } else if (this.isWhite() == myColor && position.substring(1, 2).equals("3")) {
            if(canCaptureEnPassant(position.substring(1, 2), borad))
                possibilities.add("c" + (Integer.valueOf(position.substring(1, 2)) + 1));
                borad[y][x - 1].getPiece();
            if(canCaptureEnPassant(position.substring(1, 2), borad))
                possibilities.add("d" + (Integer.valueOf(position.substring(1, 2)) + 1));
                borad[y][x + 1].getPiece();
        }
    }

    /**
     * Checks if the pawn can capture another pawn by en passant
     * @param pt location of the other pawn
     * @return true if can be captured
     * 確認したい敵の箇所をEnPassantとしてGetできる可能性があるかどうか確認するboolean
     * 任意のポジションを取得する→その場所は、敵のpawnがあることを想定
     * tmpのポジションがnullでないことを確認
     * pawnであること＆敵の色であることを確認
     * 相手のpawnが移動した直後であること
     * 残像は、ポーンが2マス進んだ直後にだけ現れる
     * */
    private boolean canCaptureEnPassant(String position, Position[][] borad) {
        int x = Integer.valueOf(ControlGame.letterToNum(position.substring(0, 1)));
        int y = Integer.valueOf(position.substring(1, 2)) - 1;

        int[] pos = {Integer.parseInt(position.substring(1, 2)) - 1, ControlGame.letterToNum(position.substring(0, 1))};
        boolean myColor = borad[pos[0]][pos[1]].getPiece().isWhite();

        Piece temp = borad[y][x].getPiece();
        if (temp != null)
            if (temp instanceof Pawn && temp.isWhite() != myColor)
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
                if (!position.substring(1, 2).equals("8")) {
                    if (board[indexNumber + 1][indexLetter].isEmpty()) {
                        possibilities.add(
                            position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2))
                                + 1));
                    }
                    if (position.substring(0, 1).equals("a") && !board[indexNumber + 1][ControlGame
                        .letterToNum("b")].isEmpty() && (
                        board[indexNumber + 1][ControlGame.letterToNum("b")].getPiece().isWhite()
                            != isWhite())) {
                        possibilities.add("b" + (Integer.valueOf(position.substring(1, 2)) + 1));

                    } else if (position.substring(0, 1).equals("h") && !board[indexNumber
                        + 1][ControlGame.letterToNum("g")].isEmpty() && (
                        board[indexNumber + 1][ControlGame.letterToNum("g")].getPiece().isWhite()
                            != isWhite())) {
                        possibilities.add("g" + (Integer.valueOf(position.substring(1, 2)) + 1));

                    } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1)
                        .equals("a")) {

                        if (!board[indexNumber + 1][indexLetter + 1].isEmpty() && (
                            board[indexNumber + 1][indexLetter + 1].getPiece().isWhite()
                                != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter + 1) + (
                                Integer.valueOf(position.substring(1, 2)) + 1));
                        }
                        if (!board[indexNumber + 1][indexLetter - 1].isEmpty() && (
                            board[indexNumber + 1][indexLetter - 1].getPiece().isWhite()
                                != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter - 1) + (
                                Integer.valueOf(position.substring(1, 2)) + 1));
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
                if (!position.substring(1, 2).equals("1")) {
                    if (board[indexNumber - 1][indexLetter].isEmpty()) {
                        possibilities.add(
                            position.substring(0, 1) + (Integer.valueOf(position.substring(1, 2))
                                - 1));
                    }
                    if (position.substring(0, 1).equals("a") && !board[indexNumber - 1][ControlGame
                        .letterToNum("b")].isEmpty() && (
                        board[indexNumber - 1][ControlGame.letterToNum("b")].getPiece().isWhite()
                            != isWhite())) {
                        possibilities.add("b" + (Integer.valueOf(position.substring(1, 2)) - 1));

                    } else if (position.substring(0, 1).equals("h") && !board[indexNumber
                        - 1][ControlGame.letterToNum("g")].isEmpty() && (
                        board[indexNumber - 1][ControlGame.letterToNum("g")].getPiece().isWhite()
                            != isWhite())) {
                        possibilities.add("g" + (Integer.valueOf(position.substring(1, 2)) - 1));

                    } else if (!position.substring(0, 1).equals("h") && !position.substring(0, 1)
                        .equals("a")) {
                        if (!board[indexNumber - 1][indexLetter + 1].isEmpty() && (
                            board[indexNumber - 1][indexLetter + 1].getPiece().isWhite()
                                != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter + 1) + (
                                Integer.valueOf(position.substring(1, 2)) - 1));
                        }
                        if (!board[indexNumber - 1][indexLetter - 1].isEmpty() && (
                            board[indexNumber - 1][indexLetter - 1].getPiece().isWhite()
                                != isWhite())) {
                            possibilities.add(ControlGame.numToLetter(indexLetter - 1) + (
                                Integer.valueOf(position.substring(1, 2)) - 1));
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
