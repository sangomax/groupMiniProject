package chess.controller;

import chess.model.*;
import chess.view.BoardGame;

import java.util.ArrayList;

public class Driver {

    public static final boolean WHITE_PIECE = true;
    public static final boolean BLACK_PIECE = false;

    public static void main(String[] args) {
        Position[][] board = ControlGame.startGame();
        BoardGame.drawBoard(board);
    }


}
