package no.uib.inf101.sem2.checkers.controller;

import no.uib.inf101.sem2.grid.CellPosition;

public interface ControllableCheckersPiece {
    /**
     * 
     * @param oldPos the old Position
     * @param newPos the new Position
     * @return
     */
    public boolean move(CellPosition oldPos, CellPosition newPos);

    public boolean isLegalMove(CellPosition oldPos, CellPosition newPos);
}
