package no.uib.inf101.sem2.checkers.controller;

import no.uib.inf101.sem2.checkers.model.GameState;
import no.uib.inf101.sem2.grid.CellPosition;

public interface ControllableCheckersPiece {
    /**
     * Method that moves the checkersPiece
     * 
     * @param oldPos the old Position
     * @param newPos the new Position
     * @return true if the move was legal, if true the piece gets moved to the
     *         newPos, if false
     *         the piece stays at the oldPos and the turn is not updated
     */
    public boolean move(CellPosition oldPos, CellPosition newPos);

    /**
     * 
     * @param oldPos
     * @param newPos
     * @return true if the move is legal false if the move is not legal
     */
    public boolean isLegalMove(CellPosition oldPos, CellPosition newPos);

    /**
     * 
     * @return the gamestate
     */
    public GameState getGameState();

    /**
     * 
     * @param setState sets the gamestate
     */
    public void setGameState(GameState setState);


    public void aiMove();

}
