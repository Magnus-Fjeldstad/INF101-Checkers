package no.uib.inf101.sem2.model.checkerspiece;

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
     * @return true if piece is White false is piece is Black
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
