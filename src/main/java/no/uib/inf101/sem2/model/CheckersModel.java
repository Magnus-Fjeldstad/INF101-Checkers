package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableCheckersPiece;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.checkerspiece.AbstractPiece;
import no.uib.inf101.sem2.model.checkerspiece.Pawn;
import no.uib.inf101.sem2.model.checkerspiece.PieceFactory;
import no.uib.inf101.sem2.view.ViewableCheckersModel;

public class CheckersModel implements ViewableCheckersModel, ControllableCheckersPiece {
    CheckersBoard board;
    PieceFactory factory;
    int turn;

    public CheckersModel(CheckersBoard board) {
        this.board = board;
        this.factory = new PieceFactory();
        this.turn = 0;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    /**
     * Sets the inital position of the board
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

    public String outPutBoard() {
        String outPutBoard = "";
        for (int i = 0; i < board.rows(); i++) {         
            System.out.print("\n");
            for (int j = 0; j < board.cols(); j++) {
                outPutBoard += (board.get(new CellPosition(i, j)).getPieceType());
                System.out.print(board.get(new CellPosition(i, j)).getPieceType());
            }
            if (i < board.rows() -1){
                outPutBoard += ("\n");}   
        }
        return outPutBoard;
    }

    /**
     * 
     * @param oldPos oldPosition
     * @param newPos newPostion
     * @return true if legal move
     */

    // public boolean isLegalMove(CellPosition oldPos, CellPosition newPos) {
    //     char pieceType = board.get(oldPos).getPieceType();
    //     boolean isKing = pieceType == 'K';
    //     boolean isCapture = false;
    //     int deltaX = newPos.row() - oldPos.row();
    //     int deltaY = newPos.col() - oldPos.col();

    //     // Check if capturing move is possible
    //     if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
    //         // Check if there is a piece to capture
    //         int capturedRow = oldPos.row() + (deltaX / 2);
    //         int capturedCol = oldPos.col() + (deltaY / 2);
    //         if (board.get(new CellPosition(capturedRow, capturedCol)).getTeam() != board.get(oldPos).getTeam()) {
    //             isCapture = true;
    //         } else {
    //             return false; // Cannot make non-capturing moves when a capturing move is possible
    //         }
    //     }
    //     if (this.turn % 2 == 0) {
    //         // Check rules based on team color
    //         if (board.get(oldPos).getTeam() == 'w') {
    //             if (board.get(newPos).getPieceType() != '-') {
    //                 return false;
    //             }
    //             if (!isKing) {
    //                 if (deltaX != -1 || Math.abs(deltaY) != 1) {
    //                     if (!isCapture || (Math.abs(deltaX) != 2 || Math.abs(deltaY) != 2)) {
    //                         return false;
    //                     }
    //                 }
    //             } else {
    //                 if (Math.abs(deltaX) != 1 || Math.abs(deltaY) != 1) {
    //                     if (!isCapture || (Math.abs(deltaX) != 2 || Math.abs(deltaY) != 2)) {
    //                         return false;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     if (this.turn % 2 == 1) {
    //          if (board.get(oldPos).getTeam() == 'b') {
    //             if (board.get(newPos).getPieceType() != '-') {
    //                 return false;
    //             }
    //             if (!isKing) {
    //                 if (deltaX != 1 || Math.abs(deltaY) != 1) {
    //                     if (!isCapture || (Math.abs(deltaX) != 2 || Math.abs(deltaY) != 2)) {
    //                         return false;
    //                     }
    //                 }
    //             } else {
    //                 if (Math.abs(deltaX) != 1 || Math.abs(deltaY) != 1) {
    //                     if (!isCapture || (Math.abs(deltaX) != 2 || Math.abs(deltaY) != 2)) {
    //                         return false;
    //                     }
    //                 }
    //             }
    //         } else {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // /**
    //  * 
    //  * @param oldPos moves the pieces based on oldPos
    //  * @param newPos moves the pieces based on newPos
    //  */
    // public boolean move(CellPosition oldPos, CellPosition newPos) {
    //     if (isLegalMove(oldPos, newPos)) {
    //         // Capture piece if possible
    //         if (Math.abs(newPos.row() - oldPos.row()) == 2 && Math.abs(newPos.col() - oldPos.col()) == 2) {
    //             int capturedRow = oldPos.row() + ((newPos.row() - oldPos.row()) / 2);
    //             int capturedCol = oldPos.col() + ((newPos.col() - oldPos.col()) / 2);
    //             board.set(new CellPosition(capturedRow, capturedCol), factory.getNext('-', '-'));
    //             //Kanskje dette fakker koden
    //             board.set(newPos, factory.getNext(board.get(oldPos).getPieceType(),board.get(oldPos).getTeam()));
    //             board.set(oldPos, factory.getNext('-', '-') );
    //             this.turn += 1;
    //             return true;             
    //         }
    //         if (isLegalMove(oldPos, newPos)) {
    //             // Move piece to new position and update turn count
    //             board.set(newPos, board.get(oldPos));
    //             board.set(oldPos, factory.getNext('-', '-'));                
    //         }
            
    //         this.turn += 1;
    //         System.out.println(this.turn);
    //         return true;        
    //     }
    //     return false;
    // }
    /**
     * 
     * @param oldPos oldPosition
     * @param newPos newPostion
     * @return true if legal move
     */

    public boolean isLegalMove(CellPosition oldPos, CellPosition newPos) {
        char pieceType = board.get(oldPos).getPieceType();
        boolean isKing = pieceType == 'K';
        boolean isCapture = false;
        int deltaX = newPos.row() - oldPos.row();
        int deltaY = newPos.col() - oldPos.col();

        // Check if capturing move is possible
        if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
            // Check if there is a piece to capture
            int capturedRow = oldPos.row() + (deltaX / 2);
            int capturedCol = oldPos.col() + (deltaY / 2);
            if (board.get(new CellPosition(capturedRow, capturedCol)).getTeam() != board.get(oldPos).getTeam()) {
                isCapture = true;
            } else {
                return false; // Cannot make non-capturing moves when a capturing move is possible
            }
        }
        if (this.turn % 2 == 0) {
            // Check rules based on team color
            if (board.get(oldPos).getTeam() == 'w') {
                if (board.get(newPos).getPieceType() != '-') {
                    return false;
                }
                if (!isKing) {
                    if (deltaX != -1 || Math.abs(deltaY) != 1) {
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
            }
        }
        if (this.turn % 2 == 1) {
             if (board.get(oldPos).getTeam() == 'b') {
                if (board.get(newPos).getPieceType() != '-') {
                    return false;
                }
                if (!isKing) {
                    if (deltaX != 1 || Math.abs(deltaY) != 1) {
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
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param oldPos moves the pieces based on oldPos
     * @param newPos moves the pieces based on newPos
     */
    public boolean move(CellPosition oldPos, CellPosition newPos) {
        if (isLegalMove(oldPos, newPos)) {
            // Capture piece if possible
            if (Math.abs(newPos.row() - oldPos.row()) == 2 && Math.abs(newPos.col() - oldPos.col()) == 2) {
                int capturedRow = oldPos.row() + ((newPos.row() - oldPos.row()) / 2);
                int capturedCol = oldPos.col() + ((newPos.col() - oldPos.col()) / 2);
                board.set(new CellPosition(capturedRow, capturedCol), factory.getNext('-', '-'));
            }
            // Move piece to new position and update turn count
            board.set(newPos, board.get(oldPos));
            board.set(oldPos, factory.getNext('-', '-'));
            this.turn += 1;
            System.out.println(this.turn);
        }
        return false;
    }

    @Override
    public Iterable<GridCell<AbstractPiece>> getTilesOnBoard() {
        return board;
    }
}
