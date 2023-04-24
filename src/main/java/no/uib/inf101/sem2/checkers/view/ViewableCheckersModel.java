package no.uib.inf101.sem2.checkers.view;


import java.util.List;

import no.uib.inf101.sem2.checkers.model.GameState;
import no.uib.inf101.sem2.checkers.model.checkerspiece.AbstractPiece;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public interface ViewableCheckersModel {
    /**
     * 
     * @return the gridDimension
     */
    GridDimension getDimension();

    /**
     * 
     * @return returns the tiles on the board
     */
    Iterable<GridCell<AbstractPiece>> getTilesOnBoard();

     /**
     * 
     * @return the gameStare
     */

    GameState getGameState();

    /**
     * 
     * @return a char that represent the currentplayer
     */
    char getcurrentPlayer();

    /**
     * 
     * @return the selected Position
     */
    CellPosition getSelectedPos();


    /**
     * 
     * @param selectedPosition takes in the selected Position
     * @return the legalPositions that the piece can move to
     */
    List<CellPosition> getAllLegalNewPositions(CellPosition selectedPosition);


}
