package chess.controller;

import chess.model.Position;

public class Validation {

    public static boolean isValidInput(String userInput, boolean isWhiteTurn, Position[][] board) {
        if (userInput.length() != 2 && userInput.length() != 4 && userInput.length() != 5) {
            System.out.println(Constants.INVALID_INPUT_MESSAGE);
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
                        } else if (!userInput.substring(4, 5).equals("q")) {
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
            if ((letter >= 97 || letter <= 104) && (Integer.valueOf(pos.substring(1, 2)) >= 1 || Integer.valueOf(pos.substring(1, 2)) <= 8)) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

//    public static boolean isValidMove(Position origen, Position destiny) {
//        try {
//            switch (origen.getPiece().getValue()) {
//
//                case Constants.PAWN_VALUE:
//                    if (!origen.getCode().substring(0, 1).equals(destiny.getCode().substring(0, 1))) {
//                        char letter = origen.getCode().substring(0, 1).toCharArray()[0];
//                        if (destiny.isEmpty()) {
//                            throw new IllegalArgumentException("This moviment is invalid");
//                        }
//                    }
//
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getLocalizedMessage());
//            return false;
//        }
//        return true;
//    }

}
