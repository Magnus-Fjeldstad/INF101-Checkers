package no.uib.inf101.sem2.model;


import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.ViewableCheckersModel;

public class CheckersModel implements ViewableCheckersModel {
    ChekersBoard board;


    public CheckersModel(ChekersBoard board){
        this.board = board;
    }
    @Override
    public GridDimension getDimension() {
       return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;    
    }    
}
