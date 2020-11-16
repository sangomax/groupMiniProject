package chess.controller;

import chess.model.Position;
import chess.view.BoardGame;
import chess.view.Messages;


public class Driver {

    public static void main(String[] args) {
        Position[][] board = ControlGame.startGame();
        BoardGame.drawBoard(board);
        boolean flagEndGame = false;
        boolean isWhiteTurn = true;
        boolean isCheck = false;
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
                    System.out.println(ControlGame.cleanMovesCheck(ControlGame.listAllPossibleMoves(board, isWhiteTurn), board, isWhiteTurn));
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
                                if (isCheck) {
                                    System.out.println(Constants.KING_IS_CHECK_MESSAGE);
                                }
                                continue;
                            }
                            board = (Position[][]) o[0];
                            BoardGame.drawBoard(board);
                            isCheck = Validation.isCheck(board, isWhiteTurn); // return true if checkmate
                            if (isCheck) {
                                String m = "Check";
                                if (Validation.isCheckMate(board, isWhiteTurn)) {
                                    flagEndGame = true;
                                    m = "CheckMate";
                                }
                                System.out.println(m);
                            } else {
                                if(Validation.isStaleMate(board,isWhiteTurn)){
                                    System.out.println("Draw Game");
                                }
//                                if(Validation.isCheckMate(board,isWhiteTurn)) {
//                                    flagEndGame = true;
//                                    System.out.println("Draw Game");
//                                }
                            }
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
