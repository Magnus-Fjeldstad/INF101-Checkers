package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.model.checkerspiece.AbstractPiece;
import no.uib.inf101.sem2.model.checkerspiece.EmptyPiece;

public class CheckersBoard extends Grid<AbstractPiece>{
    CheckersModel model;
    public CheckersBoard(int rows, int cols) {
        super(rows, cols, new EmptyPiece('-'));
    }

    
    /**
     * 
     * @return a string of the board
     */
    public String prettyString() {
        String stringBoard = "";
        for (int i = 0; i < this.rows(); ++i) {  // rows er høyden
            for(int j = 0; j < this.cols(); j++){//cols er bredden                 
                    stringBoard += this.get(new CellPosition(i,j));                                          
            }
            if (i < this.rows() -1){
                stringBoard += ("\n");}   
        }
        return stringBoard;
    }

    

}
