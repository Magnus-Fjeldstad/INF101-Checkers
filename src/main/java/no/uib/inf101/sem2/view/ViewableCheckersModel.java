package no.uib.inf101.sem2.view;


import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.checkerspiece.AbstractPiece;

public interface ViewableCheckersModel {
    /**
     * 
     * @return the gridDimension
     */
    GridDimension getDimension();

    Iterable<GridCell<AbstractPiece>> getTilesOnBoard();

}
