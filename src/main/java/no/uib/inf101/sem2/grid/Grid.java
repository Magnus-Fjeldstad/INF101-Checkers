package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid<E> implements IGrid<E> {
    // fieldVariables
    private final int rows;
    private final int cols;
    private final ArrayList<ArrayList<E>> grid;

    /**
     * 
     * @param rows      the amount of rows in the grid
     * @param cols      the amount of cols in the grid
     * @param initValue the initalvalue '-'
     */
    public Grid(int rows, int cols, E initValue) {
        this.rows = rows;
        this.cols = cols;
        grid = new ArrayList<ArrayList<E>>();

        for (int i = 0; i < rows; i++) {
            ArrayList<E> row = new ArrayList<E>();

            for (int j = 0; j < cols; j++) {
                row.add(initValue);
            }

            grid.add(row);
        }
    }

    /**
     * 
     * @param rows rows in the grid
     * @param cols cols in the grid
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    /**
     * @return the amount of rows in the grid
     */
    @Override
    public int rows() {
        return rows;
    }

    /**
     * @return the amount of cols in the grid
     */
    @Override
    public int cols() {
        return cols;
    }

    /**
     * @return an iterator over a ArrayList of GridCell<E>
     */
    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> listElements = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                CellPosition pos = new CellPosition(row, col);
                GridCell<E> gridItem = new GridCell<E>(pos, get(pos));

                listElements.add(gridItem);
            }
        }
        return listElements.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("The position is out of bounds");
        }

        else {

            grid.get(pos.row()).set(pos.col(), value);

        }
    }

    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("The Postion is out of bounds");
        } else {
            return grid.get(pos.row()).get(pos.col());
        }

    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return pos.row() >= 0 && pos.row() < this.rows() && pos.col() >= 0 && pos.col() < this.cols();
    }

}
