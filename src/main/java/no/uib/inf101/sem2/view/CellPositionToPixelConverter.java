package no.uib.inf101.sem2.view;

import java.awt.geom.Rectangle2D;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.grid.CellPosition;

public class CellPositionToPixelConverter {
    private final Rectangle2D rect;
    private final GridDimension gd;
    private final double margin;

    /**
     * 
     * @param rect   rectangle
     * @param gd     gridDimension
     * @param margin margin
     */
    public CellPositionToPixelConverter(Rectangle2D rect, GridDimension gd, double margin) {
        this.rect = rect;
        this.gd = gd;
        this.margin = margin;
    }

    // Metode som finner posisjoneringen til en celle
    public Rectangle2D getBoundsForCell(CellPosition cellPos) {
        double rectWidth = rect.getWidth();
        double rectHeight = rect.getHeight();
        double rectY = rect.getY();
        double rectX = rect.getX();
        double cpCol = cellPos.col();
        double cpRow = cellPos.row();
        double gdCols = gd.cols();
        double gdRows = gd.rows();
        double cellMargin = margin;
        double cellWidth = (rectWidth - (cellMargin * (gdCols + 1))) / gdCols;
        double cellHeight = (rectHeight - (cellMargin * (gdRows + 1))) / gdRows;
        double cellX = (rectX + (cellMargin * (cpCol + 1)) + (cellWidth * cpCol));
        double cellY = (rectY + (cellMargin * (cpRow + 1)) + (cellHeight * cpRow));

        // Lager et rektangel ut ifra verdiene som ble regnet ut uvenfor
        return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
    }
}
