package no.uib.inf101.sem2.checkers.model;

import java.util.ArrayList;
import java.util.List;
import no.uib.inf101.sem2.checkers.controller.ControllableCheckersPiece;
import no.uib.inf101.sem2.checkers.model.checkerspiece.AbstractPiece;
import no.uib.inf101.sem2.checkers.model.checkerspiece.PieceFactory;
import no.uib.inf101.sem2.checkers.view.ViewableCheckersModel;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class CheckersModel implements ViewableCheckersModel, ControllableCheckersPiece {
    private final CheckersBoard board;
    private final PieceFactory factory;
    private GameState gameState;

    private char currentPlayer = 'w';
    private CellPosition selectedPosition = new CellPosition(5, 4);

    public CheckersModel(CheckersBoard board) {
        this.board = board;
        this.factory = new PieceFactory();
        this.gameState = GameState.START_SCREEN;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    /**
     * Sets the inital position of the board.
     * Alternates by setting white/black pieces
     */
    public void setInitalBoard() {
        for (int i = 0; i < board.rows(); i++) {
            // draws the white pieces
            if (i % 2 == 0) {
                board.set(new CellPosition(7, i), factory.getNext('P', 'w'));
            }
            if (i % 2 != 0) {
                board.set(new CellPosition(6, i), factory.getNext('P', 'w'));
            }
            if (i % 2 == 0) {
                board.set(new CellPosition(5, i), factory.getNext('P', 'w'));
            }

            // draws the black pieces
            if (i % 2 != 0) {
                board.set(new CellPosition(0, i), factory.getNext('P', 'b'));
            }
            if (i % 2 == 0) {
                board.set(new CellPosition(1, i), factory.getNext('P', 'b'));
            }
            if (i % 2 != 0) {
                board.set(new CellPosition(2, i), factory.getNext('P', 'b'));
            }
        }
    }

    /**
     * 
     * @return a strinified version of the board in the terminal
     */
    public String outPutBoard() {
        String outPutBoard = "";
        for (int i = 0; i < board.rows(); i++) {
            System.out.print("\n");
            for (int j = 0; j < board.cols(); j++) {
                outPutBoard += (board.get(new CellPosition(i, j)).getPieceType());
                System.out.print(board.get(new CellPosition(i, j)).getPieceType());
            }
            if (i < board.rows() - 1) {
                outPutBoard += ("\n");
            }
        }
        return outPutBoard;
    }

    @Override
    public boolean isLegalMove(CellPosition oldPos, CellPosition newPos) {
        char pieceType = board.get(oldPos).getPieceType();
        char teamColor = board.get(oldPos).getTeam();
        boolean isKing = pieceType == 'K';
        boolean isCapture = false;
        int deltaX = newPos.row() - oldPos.row();
        int deltaY = newPos.col() - oldPos.col();

        int capturedRow = oldPos.row() + (deltaX / 2);
        int capturedCol = oldPos.col() + (deltaY / 2);

        // Check if capturing move is possible
        if ((deltaX == -2 && Math.abs(deltaY) == 2 && board.get(oldPos).getTeam() == 'w') ||
                (deltaX == 2 && Math.abs(deltaY) == 2 && board.get(oldPos).getTeam() == 'b') ||
                (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2 && board.get(oldPos).getPieceType() == 'K')) {
            // Check if there is a piece to capture
            if (board.get(new CellPosition(capturedRow, capturedCol)).getTeam() != board.get(oldPos).getTeam()
                    && board.get(new CellPosition(capturedRow, capturedCol)).getTeam() != '-') {
                isCapture = true;
            } else {
                return false; // Cannot make non-capturing moves when a capturing move is possible
            }
        }

        if (!isCapture && hasCaptureAvailable(board.get(oldPos).getTeam())) {
            return false; // Mandatory capture
        }

        if (teamColor != 'w' && teamColor != 'b') {
            return false; // Piece has no team color
        }

        if (board.get(newPos).getPieceType() != '-') {
            return false;
        }

        // if it is not a king rules is aplied to check that pieces can only go in one
        // direction
        if (!isKing) {
            if ((teamColor == 'w' && (deltaX != -1 || Math.abs(deltaY) != 1)) ||
                    (teamColor == 'b' && (deltaX != 1 || Math.abs(deltaY) != 1))) {
                if (!isCapture || (Math.abs(deltaX) != 2 || Math.abs(deltaY) != 2)) {
                    return false;
                }
            }
        } else {
            if (Math.abs(deltaX) != 1 || Math.abs(deltaY) != 1) {
                if (!isCapture || (Math.abs(deltaX) != 2 || Math.abs(deltaY) != 2)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean move(CellPosition oldPos, CellPosition newPos) {
        if (board.get(oldPos).getTeam() != currentPlayer) {
            return false; // It's not this player's turn
        }

        if (isLegalMove(oldPos, newPos)) {
            boolean isCapture = Math.abs(newPos.row() - oldPos.row()) == 2
                    && Math.abs(newPos.col() - oldPos.col()) == 2;

            if (isCapture) {
                int capturedRow = oldPos.row() + ((newPos.row() - oldPos.row()) / 2);
                int capturedCol = oldPos.col() + ((newPos.col() - oldPos.col()) / 2);
                board.set(new CellPosition(capturedRow, capturedCol), factory.getNext('-', '-'));
            }

            // Move piece to new position and update turn count
            board.set(newPos, board.get(oldPos));
            board.set(oldPos, factory.getNext('-', '-'));

            // Check if another capture is available after the current move

            checkIfGameOver();
            promoteToKing();
            boolean anotherCaptureAvailable = isCapture && hasCaptureAvailableFromPosition(newPos);
            if (!anotherCaptureAvailable) {
                // Switch players
                currentPlayer = (currentPlayer == 'w') ? 'b' : 'w';
            }
            return true;
        }
        return false;
    }

    /**
     * 
     * @param team takes in a char that represents the team
     * @return if a ream has a capture avalible
     */
    private boolean hasCaptureAvailable(char team) {
        // Check the entire board for possible captures for the specified team
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                CellPosition pos = new CellPosition(row, col);
                if (board.get(pos).getTeam() == team && hasCaptureAvailableFromPosition(pos)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param pos takes in a cellPosition
     * @return true if there is an avalable move from the position
     */
    private boolean hasCaptureAvailableFromPosition(CellPosition pos) {
        char team = board.get(pos).getTeam();
        if (team == '-') {
            return false;
        }

        boolean isKing = board.get(pos).getPieceType() == 'K';
        int[] rowDeltas = isKing ? new int[] { -2, 2 } : new int[] { team == 'w' ? -2 : 2 };
        int[] colDeltas = { -2, 2 };

        for (int rowDelta : rowDeltas) {
            for (int colDelta : colDeltas) {
                CellPosition newPos = new CellPosition(pos.row() + rowDelta, pos.col() + colDelta);
                if (board.positionIsOnGrid(newPos) && isLegalMove(pos, newPos)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * checks if a piece is at the further most rank from its start, then promotes
     * it to a king if true
     */
    private void promoteToKing() {
        for (int i = 0; i < board.cols(); i++) {
            if (board.get(new CellPosition(0, i)).getTeam() == 'w') {
                board.set(new CellPosition(0, i), factory.getNext('K', 'w'));
            }
        }

        for (int i = 0; i < board.cols(); i++) {
            if (board.get(new CellPosition(7, i)).getTeam() == 'b') {
                board.set(new CellPosition(7, i), factory.getNext('K', 'b'));
            }
        }
    }

    /**
     * CHECKS if all of the white or black pieces is captured then sets
     * the gamestate to GAME_OVER
     */
    private void checkIfGameOver() {
        boolean whitePieceExist = false;
        boolean blackPieceExist = false;
        boolean whiteLegalMove = false;
        boolean blackLegalMove = false;

        // Check for legal moves for white pieces
        for (GridCell<AbstractPiece> gridCell : board) {
            if (gridCell.value().getTeam() == 'w') {
                whitePieceExist = true;
                for (int i = 0; i < board.rows(); i++) {
                    for (int j = 0; j < board.cols(); j++) {
                        if (isLegalMove(gridCell.pos(), new CellPosition(i, j))) {
                            whiteLegalMove = true;
                        }
                    }
                }
            }
        }

        // Check for legal moves for black pieces
        for (GridCell<AbstractPiece> gridCell : board) {
            if (gridCell.value().getTeam() == 'b') {
                blackPieceExist = true;
                for (int i = 0; i < board.rows(); i++) {
                    for (int j = 0; j < board.cols(); j++) {
                        if (isLegalMove(gridCell.pos(), new CellPosition(i, j))) {
                            blackLegalMove = true;
                        }
                    }
                }
            }
        }

        if (!whitePieceExist || !blackPieceExist || !whiteLegalMove || !blackLegalMove) {
            this.gameState = GameState.GAME_OVER;
        }
    }

    // private void checkIfGameOver() {
    // boolean whitePieceExist = false;
    // boolean blackPieceExist = false;
    // for (GridCell<AbstractPiece> gridCell : board) {
    // if (gridCell.value().getTeam() == 'w') {
    // whitePieceExist = true;
    // }
    // }
    // for (GridCell<AbstractPiece> gridCell : board) {
    // if (gridCell.value().getTeam() == 'b') {
    // blackPieceExist = true;
    // }
    // }
    // if (!whitePieceExist || !blackPieceExist) {
    // this.gameState = GameState.GAME_OVER;
    // }
    // }

    @Override
    public List<CellPosition> getAllLegalNewPositions(CellPosition selectedPosition) {
        List<CellPosition> legalNewPositions = new ArrayList<>();
        AbstractPiece piece = board.get(selectedPosition);
        if ((piece).getTeam() == currentPlayer) {
            for (int i = 0; i < board.rows(); i++) {
                for (int j = 0; j < board.cols(); j++) {
                    if (isLegalMove(selectedPosition, new CellPosition(i, j))) {
                        legalNewPositions.add(new CellPosition(i, j));
                    }
                }
            }
        }
        return legalNewPositions;
    }

    @Override
    public Iterable<GridCell<AbstractPiece>> getTilesOnBoard() {
        return board;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void setGameState(GameState setState) {
        this.gameState = setState;
    }

    @Override
    public char getcurrentPlayer() {
        return currentPlayer;
    }

    public void setSelected(CellPosition selectedPosition) {
        if (currentPlayer == board.get(selectedPosition).getTeam()) {
            this.selectedPosition = selectedPosition;
            getAllLegalNewPositions(selectedPosition);
        }
    }

    @Override
    public CellPosition getSelectedPos() {
        return this.selectedPosition;
    }
}
