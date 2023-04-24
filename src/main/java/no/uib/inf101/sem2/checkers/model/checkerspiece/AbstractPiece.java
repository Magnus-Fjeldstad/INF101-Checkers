package no.uib.inf101.sem2.checkers.model.checkerspiece;

import no.uib.inf101.sem2.grid.CellPosition;

public abstract class AbstractPiece {
    public char pieceType;
    public char team;
    public CellPosition pos;


    /**
     * 
     * @return the piecetype
     */
    public char getPieceType() {
        return this.pieceType;
    }

    /**
     * 
     * @return the color represented by a char
     */
    public char getTeam() {
        return this.team;
    }

    /**
     * 
     * @return the cellPosition
     */
    public CellPosition getCellPosition() {
        return pos;
    }
}
