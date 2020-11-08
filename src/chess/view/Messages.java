package chess.view;

import chess.controller.Constants;

public class Messages {

    public static void messagePlayerTurn(boolean isWhiteTurn) {
        if(isWhiteTurn) {
            System.out.println(Constants.ASK_WHITE_MOVE_MESSAGE);
        } else {
            System.out.println(Constants.ASK_BLACK_MOVE_MESSAGE);
        }
    }

    public static void messageResignWin(boolean isWhiteTurn) {
        if(isWhiteTurn) {
            System.out.println(Constants.BLACK_WON_RESIGN_MESSAGE);
        } else {
            System.out.println(Constants.WHITE_WON_RESIGN_MESSAGE);
        }
    }

    public static void showHelpMessages() {
        System.out.println(Constants.TYPE_HELP_HELP_MESSAGE);
        System.out.println(Constants.TYPE_BOARD_HELP_MESSAGE);
        System.out.println(Constants.TYPE_RESIGN_HELP_MESSAGE);
        System.out.println(Constants.TYPE_MOVES_HELP_MESSAGE);
        System.out.println(Constants.TYPE_SQUARE_HELP_MESSAGE);
        System.out.println(Constants.TYPE_UCI_HELP_MESSAGE);
        System.out.println();
    }

}
