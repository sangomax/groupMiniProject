package chess.controller;

import chess.model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Validation {

    public static boolean isValidInput(String userInput, boolean isWhiteTurn, Position[][] board) {
        if (userInput.length() != 2 && userInput.length() != 4 && userInput.length() != 5) {
            System.out.println(Constants.INVALID_INPUT_MESSAGE);
            return false;
        } else {
            if (userInput.length() == 2) {
                if (!isValidPosition(userInput)) {
                    System.out.println(Constants.INVALID_INPUT_MESSAGE);
                    return false;
                } else {
                    int[] indexsOrigin = ControlGame.convertPosition(userInput);
                    if (board[indexsOrigin[0]][indexsOrigin[1]].isEmpty()) {
                        System.out.println(Constants.INVALID_INPUT_MESSAGE);
                        return false;
                    } else {
                        if (board[indexsOrigin[0]][indexsOrigin[1]].getPiece().isWhite() != isWhiteTurn) {
                            System.out.println(Constants.INVALID_INPUT_MESSAGE);
                            return false;
                        }
                    }
                }
            } else if (userInput.length() == 4) {
                if (!isValidPosition(userInput.substring(0, 2)) && !isValidPosition(userInput.substring(2, 4))) {
                    System.out.println(Constants.INVALID_INPUT_MESSAGE);
                    return false;
                } else {
                    int[] indexsOrigin = ControlGame.convertPosition(userInput.substring(0, 2));
                    int[] indexsDestiny = ControlGame.convertPosition(userInput.substring(2, 4));
                    if (board[indexsOrigin[0]][indexsOrigin[1]].isEmpty()) {
                        System.out.println(Constants.INVALID_INPUT_MESSAGE);
                        return false;
                    } else {
                        if (board[indexsOrigin[0]][indexsOrigin[1]].getPiece().isWhite() != isWhiteTurn) {
                            System.out.println(Constants.INVALID_INPUT_MESSAGE);
                            return false;
                        } else if (!board[indexsDestiny[0]][indexsDestiny[1]].isEmpty() &&
                                board[indexsDestiny[0]][indexsDestiny[1]].getPiece().isWhite() == isWhiteTurn) {
                            System.out.println(Constants.INVALID_INPUT_MESSAGE);
                            return false;
                        }
                    }
                }
            } else if (userInput.length() == 5) {
                if (!isValidPosition(userInput.substring(0, 2)) && !isValidPosition(userInput.substring(2, 4))) {
                    System.out.println(Constants.INVALID_INPUT_MESSAGE);
                    return false;
                } else {
                    int[] indexsOrigin = ControlGame.convertPosition(userInput.substring(0, 2));
                    int[] indexsDestiny = ControlGame.convertPosition(userInput.substring(2, 4));
                    if (board[indexsOrigin[0]][indexsOrigin[1]].isEmpty()) {
                        System.out.println(Constants.INVALID_INPUT_MESSAGE);
                        return false;
                    } else {
                        if (board[indexsOrigin[0]][indexsOrigin[1]].getPiece().isWhite() != isWhiteTurn) {
                            System.out.println(Constants.INVALID_INPUT_MESSAGE);
                            return false;
                        } else if (!board[indexsDestiny[0]][indexsDestiny[1]].isEmpty() &&
                                board[indexsDestiny[0]][indexsDestiny[1]].getPiece().isWhite() == isWhiteTurn) {
                            System.out.println(Constants.INVALID_INPUT_MESSAGE);
                            return false;
                        } else if (!userInput.substring(4, 5).equals("q") &&
                                !userInput.substring(4, 5).equals("n") &&
                                !userInput.substring(4, 5).equals("b") &&
                                !userInput.substring(4, 5).equals("r")) {
                            System.out.println(Constants.INVALID_INPUT_MESSAGE);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isValidPosition(String pos) {
        try {
            char letter = pos.substring(0, 1).toCharArray()[0];
            if ((letter >= 97 && letter <= 104) && (Integer.valueOf(pos.substring(1, 2)) >= 1 && Integer.valueOf(pos.substring(1, 2)) <= 8)) {
                return true;
            }
        } catch (NumberFormatException e) {

        }
        return false;
    }


    /**
     * check by board
     * Check if one's pieces possibilities contain opponent's king location
     *
     * @param isWhiteTurn
     * @return true if checkmate, false if not
     */

    public static boolean isCheck(Position[][] board, boolean isWhiteTurn) {
        HashMap<String, ArrayList<String>> possiblesMoves = ControlGame.listAllPossibleMoves(board, isWhiteTurn);
        Set<String> origins = possiblesMoves.keySet();
        ArrayList<String> allPossibleMoves = new ArrayList<>();
        for (String origin : origins) {
            allPossibleMoves.addAll(possiblesMoves.get(origin));
        }

        String kingPositionOpponent = ControlGame.findKing(board, !isWhiteTurn);
        for (String possibleMove : allPossibleMoves) {
            if (possibleMove.equals(kingPositionOpponent)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isKeepCheck(int[] indexOrigen, int[] indexDestiny, Position[][] board, boolean isWhiteTurn) {
        Position copy = new Position(board[indexDestiny[0]][indexDestiny[1]].getPiece(),board[indexDestiny[0]][indexDestiny[1]].getCode());
        if (isCheck(ControlGame.changeBoard(indexOrigen, indexDestiny, board), !isWhiteTurn)) {
            ControlGame.changeBoard(indexDestiny, indexOrigen, board);
            board[indexDestiny[0]][indexDestiny[1]] = copy;
            return true;
        }
        ControlGame.changeBoard(indexDestiny, indexOrigen, board);
        board[indexDestiny[0]][indexDestiny[1]] = copy;
        return false;
    }

    public static boolean isCheckMate(Position[][] board, boolean isWhiteTurn) {
        for (Position[] row : board) {
            for (Position col : row) {
                if (!col.isEmpty() && col.getPiece().isWhite() == !isWhiteTurn) {
                    int[] indexOrigen = ControlGame.convertPosition(col.getCode());
                    for (String possiblesMove : col.getPiece().move(col.getCode(), board)) {
                        int[] indexDestiny = ControlGame.convertPosition(possiblesMove);
                        Position copy = new Position(board[indexDestiny[0]][indexDestiny[1]].getPiece(),board[indexDestiny[0]][indexDestiny[1]].getCode());
                        if (!isCheck(ControlGame.changeBoard(indexOrigen, indexDestiny, board), isWhiteTurn)) {
                            ControlGame.changeBoard(indexDestiny, indexOrigen, board);
                            board[indexDestiny[0]][indexDestiny[1]] = copy;
                            return false;
                        }
                        ControlGame.changeBoard(indexDestiny, indexOrigen, board);
                        board[indexDestiny[0]][indexDestiny[1]] = copy;
                    }
                }
            }
        }
        return true;
    }


    public static boolean isValidMove(Position origen, Position destiny) {
        try {
            switch (origen.getPiece().getValue()) {

                case Constants.PAWN_VALUE:
                    if (!origen.getCode().substring(0, 1).equals(destiny.getCode().substring(0, 1))) {
                        char letter = origen.getCode().substring(0, 1).toCharArray()[0];
                        if (destiny.isEmpty()) {
                            throw new IllegalArgumentException("This moviment is invalid");
                        }
                    }

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        return true;
    }

}
