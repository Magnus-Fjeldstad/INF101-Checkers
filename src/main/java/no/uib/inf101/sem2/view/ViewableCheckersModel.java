package no.uib.inf101.sem2.view;

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
     * @return the tiles of the board
     */

    Iterable<GridCell<Character>> getTilesOnBoard();
}
