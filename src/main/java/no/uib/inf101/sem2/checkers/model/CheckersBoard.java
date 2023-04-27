package no.uib.inf101.sem2.checkers.model;

import no.uib.inf101.sem2.checkers.model.checkerspiece.AbstractPiece;
import no.uib.inf101.sem2.checkers.model.checkerspiece.EmptyPiece;
import no.uib.inf101.sem2.grid.Grid;

public class CheckersBoard extends Grid<AbstractPiece> {
    CheckersModel model;

    public CheckersBoard(int rows, int cols) {
        super(rows, cols, new EmptyPiece('-'));
    }

}
