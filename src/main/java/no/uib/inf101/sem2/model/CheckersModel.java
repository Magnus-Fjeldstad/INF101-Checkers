package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.checkerspiece.PieceFactory;
import no.uib.inf101.sem2.view.ViewableCheckersModel;

public class CheckersModel implements ViewableCheckersModel {
    ChekersBoard board;
    PieceFactory factory;
    int turn;

    public CheckersModel(ChekersBoard board) {
        this.board = board;
        this.factory = new PieceFactory();
        this.turn = 0;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

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

    public void outPutBoard() {
        for (int i = 0; i < board.rows(); i++) {
            System.out.print("\n");
            for (int j = 0; j < board.cols(); j++) {
                System.out.print(board.get(new CellPosition(i, j)).getPieceType());
            }
        }
    }

    /**
     * 
     * @param oldPos    oldPosition
     * @param newPos    newPostion
     * @param isCapture checks if capture
     * @return true if legal move
     */
    // public boolean isLegalMove(CellPosition oldPos, CellPosition newPos) {
    //     // Determine if the piece is a king
    //     boolean isKing = board.get(oldPos).getPieceType() == 'K';   
    //     boolean isCapture = false;
    //     int deltaX = Math.abs(newPos.row() - oldPos.row());
    //     int deltaY = Math.abs(newPos.col()- oldPos.col());

        
    //     if(board.get(new CellPosition(newPos.row(), newPos.col())).getPieceType() != '-'){
    //         return false;
    //     }
    //     if(deltaX ==1 && deltaY ==1 && !isCapture){return true;}

    //     return false;
    // }
    
    // public void move(CellPosition oldPos, CellPosition newPos) {
    //     if (isLegalMove(oldPos, newPos)) {
    //         board.set(newPos, board.get(oldPos));
    //         board.set(oldPos, factory.getNext('-', '-'));
    //         this.turn++;
    //     }
    // }
    public boolean isLegalMove(CellPosition oldPos, CellPosition newPos) {
        // Determine if the piece is a king
        boolean isKing = board.get(oldPos).getPieceType() == 'K';   
        boolean isCapture = false;
        int deltaX = newPos.row() - oldPos.row();
        int deltaY = newPos.col() - oldPos.col();
    
        // Check if the move is a capture
        if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
            int captureRow = oldPos.row() + deltaX / 2;
            int captureCol = oldPos.col() + deltaY / 2;
            if (board.get(new CellPosition(captureRow, captureCol)).getPieceType() != '-') {
                isCapture = true;
            }
        }
    
        // Check if the move is legal
        if (!isCapture && board.get(newPos).getPieceType() != '-') {
            return false; // Can't move to a square that's occupied by a friendly piece
        }
        if (!isKing && deltaX * (turn == 0 ? -1 : 1) < 0) {
            return false; // Can't move a non-king piece backwards
        }
        if (Math.abs(deltaX) != Math.abs(deltaY)) {
            return false; // Can only move diagonally
        }
        if (!isCapture && Math.abs(deltaX) > 2) {
            return false; // Can only move two squares if capturing
        }
        if (isCapture && Math.abs(deltaX) != 2) {
            return false; // Must move two squares to capture
        }
    
        return true;
    }
    
    public void move(CellPosition oldPos, CellPosition newPos) {
        if (isLegalMove(oldPos, newPos)) {
            int deltaX = newPos.row() - oldPos.row();
            int deltaY = newPos.col() - oldPos.col();
    
            // Capture the piece if necessary
            if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
                int captureRow = oldPos.row() + deltaX / 2;
                int captureCol = oldPos.col() + deltaY / 2;
                board.set(new CellPosition(captureRow, captureCol), factory.getNext('-', '-'));
            }
    
            // Move the piece
            board.set(newPos, board.get(oldPos));
            board.set(oldPos, factory.getNext('-', '-'));
            this.turn++;
        }
    }
    
}
