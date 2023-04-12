package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;

public class ChekersBoard extends Grid<Character>{
    CheckersModel model;
    public ChekersBoard(int rows, int cols) {
        super(rows, cols,  '-');
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

    public void drawCheckersBoard(){
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.cols(); j++) {        
                if(i % 2 == 0 && j % 2 != 0){
                    this.set(new CellPosition(i, j), 'w');
                } else if(i % 2 != 0 && j % 2 == 0){
                    this.set(new CellPosition(i, j), 'w');
                } else {
                    this.set(new CellPosition(i, j), 'b');
                }
            }          
        }
    }
    
    public void setInitalBoard() {
        this.set(new CellPosition(0, 0), 'P');
    }
}
