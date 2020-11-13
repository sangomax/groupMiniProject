package chess.controller;

import chess.model.Position;
import chess.view.BoardGame;
import chess.view.Messages;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        Position[][] board = ControlGame.startGame();
        BoardGame.drawBoard(board);
        boolean flagEndGame = false;
        boolean isWhiteTurn = true;
        do {

            Messages.messagePlayerTurn(isWhiteTurn);
            String userInput = InputCollector.getUserInput(Constants.INPUT_UCI_MESSAGE);

            switch (userInput) {
                case "help":
                    Messages.showHelpMessages();
                    continue;
                case "board":
                    BoardGame.drawBoard(board);
                    continue;
                case "resign":
                    Messages.messageResignWin(isWhiteTurn);
                    flagEndGame = true;
                    break;
                case "move":
                    System.out.println(ControlGame.listAllPossibleMoves(board, isWhiteTurn));
                    continue;
                default:
                    if (Validation.isValidInput(userInput, isWhiteTurn, board)) {
                        String[] inputMove = ControlGame.getInfoMove(userInput);
                        if (inputMove[1] == null) {
                            ControlGame.listPossibleMoves(board, inputMove[0]);
                            continue;
                        } else {
                            Object[] o = ControlGame.move(board, inputMove[0], inputMove[1], inputMove[2]);
                            if (!(boolean) o[1]) {
                                continue;
                            }
                            board = (Position[][]) o[0];
                            BoardGame.drawBoard(board);
                            System.out.println(Validation.isCheckMate(board,isWhiteTurn)); // return true if checkmate

                        }
                    } else {
                        continue;

                    }
            }


            //change player
            isWhiteTurn = !isWhiteTurn;

        } while (!flagEndGame);

        //win: Check mate & opponents king has no valid moves
        //draw : check & king has a valid move but it is also to be taken
        // lost: Opponent Check mate & King has no possibilities to move
    }


}
