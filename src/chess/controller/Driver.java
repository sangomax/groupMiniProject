package chess.controller;

import chess.model.*;
import chess.view.BoardGame;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        Position[][] board = ControlGame.startGame();
        BoardGame.drawBoard(board);

        //win: Check mate & opponents king has no valid moves
        //draw : check & king has a valid move but it is also to be taken
        // lost: Opponent Check mate & King has no possibilities to move
    }


}
