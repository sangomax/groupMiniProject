package chess.controller;

import chess.model.*;
import chess.view.BoardGame;
import chess.view.Messages;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        Position[][] board = ControlGame.startGame();
        BoardGame.drawBoard(board);
        boolean flagEndGame = false;
        boolean isWhiteTurn = true;
        do{

            Messages.messagePlayerTurn(isWhiteTurn);
            String userInput = InputCollector.getUserInput(Constants.INPUT_UCI_MESSAGE);

            switch (userInput) {
                case "help":    Messages.showHelpMessages();
                                continue;
                case "board":   BoardGame.drawBoard(board);
                                continue;
                case "resign":  Messages.messageResignWin(isWhiteTurn);
                                flagEndGame = true;
                                break;
                case "moves":
                                break;
                default:

            }


            //change player
            isWhiteTurn = !isWhiteTurn;

        } while(!flagEndGame);

        //win: Check mate & opponents king has no valid moves
        //draw : check & king has a valid move but it is also to be taken
        // lost: Opponent Check mate & King has no possibilities to move
    }




}
