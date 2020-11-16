package chess.controller;

import chess.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ControlGame {

    public static Position[][] startGame() {
        Position[][] board = new Position[8][8];

        for (int row = board.length - 1; row >= 0; row--) {

            if (row == 7) {
                board[row][0] = new Position(new Rook(Constants.BLACK_PIECE), Constants.A8);
                board[row][1] = new Position(new Knight(Constants.BLACK_PIECE), Constants.B8);
                board[row][2] = new Position(new Bishop(Constants.BLACK_PIECE), Constants.C8);
                board[row][3] = new Position(new Queen(Constants.BLACK_PIECE), Constants.D8);
                board[row][4] = new Position(new King(Constants.BLACK_PIECE), Constants.E8);
                board[row][5] = new Position(new Bishop(Constants.BLACK_PIECE), Constants.F8);
                board[row][6] = new Position(new Knight(Constants.BLACK_PIECE), Constants.G8);
                board[row][7] = new Position(new Rook(Constants.BLACK_PIECE), Constants.H8);
            } else if (row == 6) {
                int i = 0;
                for (char letter = 97; letter < 105; letter++) {
                    board[row][i] = new Position(new Pawn(Constants.BLACK_PIECE, false, null), String.valueOf(letter) + (row + 1));
                    i++;
                }
            } else if (row > 1) {
                int i = 0;
                for (char letter = 97; letter < 105; letter++) {
                    board[row][i] = new Position(null, String.valueOf(letter) + (row + 1));
                    i++;
                }
            } else if (row == 1) {
                int i = 0;
                for (char letter = 97; letter < 105; letter++) {
                    board[row][i] = new Position(new Pawn(Constants.WHITE_PIECE, false, null), String.valueOf(letter) + (row + 1));
                    i++;
                }
            } else if (row == 0) {
                board[row][0] = new Position(new Rook(Constants.WHITE_PIECE), Constants.A1);
                board[row][1] = new Position(new Knight(Constants.WHITE_PIECE), Constants.B1);
                board[row][2] = new Position(new Bishop(Constants.WHITE_PIECE), Constants.C1);
                board[row][3] = new Position(new Queen(Constants.WHITE_PIECE), Constants.D1);
                board[row][4] = new Position(new King(Constants.WHITE_PIECE), Constants.E1);
                board[row][5] = new Position(new Bishop(Constants.WHITE_PIECE), Constants.F1);
                board[row][6] = new Position(new Knight(Constants.WHITE_PIECE), Constants.G1);
                board[row][7] = new Position(new Rook(Constants.WHITE_PIECE), Constants.H1);
            }
        }
        return board;
    }

    public static String[] getInfoMove(String input) {
        String initPos = input.substring(0, 2);
        String finPos = input.length() >= 4 ? input.substring(2, 4) : null;
        String prom = input.length() == 5 ? input.substring(4, 5) : null;

        return new String[]{initPos, finPos, prom};
    }

    public static int letterToNum(String letter) {
        HashMap<String, Integer> listLetterToNum = new HashMap<>();
        listLetterToNum.put("a", 0);
        listLetterToNum.put("b", 1);
        listLetterToNum.put("c", 2);
        listLetterToNum.put("d", 3);
        listLetterToNum.put("e", 4);
        listLetterToNum.put("f", 5);
        listLetterToNum.put("g", 6);
        listLetterToNum.put("h", 7);

        return listLetterToNum.get(letter);
    }

    public static String numToLetter(int letter) {
        HashMap<Integer, String> listLetterToNum = new HashMap<>();
        listLetterToNum.put(0, "a");
        listLetterToNum.put(1, "b");
        listLetterToNum.put(2, "c");
        listLetterToNum.put(3, "d");
        listLetterToNum.put(4, "e");
        listLetterToNum.put(5, "f");
        listLetterToNum.put(6, "g");
        listLetterToNum.put(7, "h");

        return listLetterToNum.get(letter);
    }

    public static int[] convertPosition(String position) {
        return new int[]{Integer.parseInt(position.substring(1, 2)) - 1, letterToNum(position.substring(0, 1))};
    }

    public static void listPossibleMoves(Position[][] board, String pos) {
        int[] indexOrigin = convertPosition(pos);
        Piece piece = board[indexOrigin[0]][indexOrigin[1]].getPiece();
        ArrayList<String> listMoves = removeMovesCheck(piece.move(pos, board), indexOrigin, board, piece.isWhite());
        if (listMoves.size() > 0) {
            System.out.println(Constants.POSSIBLE_MOVE_MESSAGE + " " + pos);
            System.out.println(listMoves);
        } else {
            System.out.println(Constants.NO_POSSIBLE_MOVE_MESSAGE + " " + pos);
        }
    }

    public static HashMap<String, ArrayList<String>> listAllPossibleMoves(Position[][] board, boolean isWhite) {
        ArrayList<String> allPossibleMoves = new ArrayList<>();
        HashMap<String, ArrayList<String>> allOriginWithMove = new HashMap<>();

        for (Position[] row : board) {
            for (Position col : row) {
                if (!col.isEmpty()) {
                    if (col.getPiece().isWhite() == isWhite) {
                        ArrayList<String> possibleMove = col.getPiece().move(col.getCode(), board);
                        if (possibleMove.size() > 0) {
                            allOriginWithMove.put(col.getCode(), possibleMove);
                        }
                    }
                }
            }
        }

        return allOriginWithMove;
    }

    public static String findKing(Position[][] board, boolean isWhite) {
        for (Position[] row : board) {
            for (Position col : row) {
                if (!col.isEmpty() && col.getPiece().getClass().equals(King.class) && col.getPiece().isWhite() == isWhite) {
                    return col.getCode();
                }
            }
        }
        return null;
    }

    public static void setCastling(String origin, String destination, Position[][] board) {
        int[] indexOrigin = convertPosition(origin);
        int[] indexDestination = convertPosition(destination);
        board = changeBoard(indexOrigin, indexDestination, board);
        board[indexOrigin[0]][indexOrigin[1]].setPiece(null);
    }

    public static Object[] move(Position[][] board, String origin, String destiny, String promotion) {
        int[] indexOrigin = convertPosition(origin);
        int[] indexDestiny = convertPosition(destiny);
        boolean flagOK = false;

        ArrayList<String> possibleMoves = board[indexOrigin[0]][indexOrigin[1]].getPiece().move(origin, board);

        possibleMoves = removeMovesCheck(possibleMoves, indexOrigin, board, board[indexOrigin[0]][indexOrigin[1]].getPiece().isWhite());

        if (possibleMoves.contains(destiny)) {
            board = changeBoard(indexOrigin, indexDestiny, board);
            flagOK = true;

            /// castling moves
            if (origin.equals("e1") && destiny.equals("g1")) {
                setCastling("h1", "f1", board);
            } else if (origin.equals("e1") && destiny.equals("c1")) {
                setCastling("a1", "d1", board);
            } else if (origin.equals("e8") && destiny.equals("g8")) {
                setCastling("h8", "f8", board);
            } else if (origin.equals("e8") && destiny.equals("c8")) {
                setCastling("a8", "d8", board);
            }

        } else {
            System.out.println(Constants.INVALID_MOVEMENT_MESSAGE);
        }


        if (promotion != null && (promotion.equals("q") || promotion.equals("n") ||
                promotion.equals("b") && promotion.equals("r")) &&
                board[indexDestiny[0]][indexDestiny[1]].getPiece().getValue() == 1 &&
                (indexDestiny[0] == 7 || indexDestiny[0] == 0)) {

            Pawn prom = (Pawn) board[indexDestiny[0]][indexDestiny[1]].getPiece();
            switch (promotion) {
                case "r":
                    prom.promote(new Rook(prom.isWhite()));
                    break;
                case "b":
                    prom.promote(new Bishop(prom.isWhite()));
                    break;
                case "n":
                    prom.promote(new Knight(prom.isWhite()));
                    break;
                case "q":
                    prom.promote(new Queen(prom.isWhite()));
                    break;
            }
            board[indexDestiny[0]][indexDestiny[1]].setPiece(prom);
        }

        return new Object[]{board, flagOK};
    }

    public static Position[][] changeBoard(int[] indexsOrigen, int[] indexsDestiny, Position[][] board) {
        board[indexsDestiny[0]][indexsDestiny[1]].setPiece(board[indexsOrigen[0]][indexsOrigen[1]].getPiece());
        board[indexsOrigen[0]][indexsOrigen[1]].setPiece(null);
        return board;
    }

    public static ArrayList<String> cleanMovesCheck
            (HashMap<String, ArrayList<String>> allOriginWithMove, Position[][] board, boolean isWhite) {
        Set<String> origins = allOriginWithMove.keySet();
        ArrayList<String> allPossibleMoves = new ArrayList<>();
        for (String origin : origins) {
            ArrayList<String> temp = removeMovesCheck(allOriginWithMove.get(origin), convertPosition(origin), board, isWhite);
            allPossibleMoves.addAll(temp);
        }
        return allPossibleMoves;
    }

    public static ArrayList<String> removeMovesCheck(ArrayList<String> possibleMoves,
                                                     int[] indexOrigin, Position[][] board, boolean isWhite) {

        ArrayList<String> moveToRemove = new ArrayList<>();
        for (String move : possibleMoves) {
            if (Validation.isKeepCheck(indexOrigin, convertPosition(move), board, isWhite)) {
                moveToRemove.add(move);
            }
        }

        possibleMoves.removeAll(moveToRemove);

        return possibleMoves;
    }

}
