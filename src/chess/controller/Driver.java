package chess.controller;

import chess.model.*;
import chess.view.BoardGame;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        Position[][] board = ControlGame.startGame();
        BoardGame.drawBoard(board);
    }


}
