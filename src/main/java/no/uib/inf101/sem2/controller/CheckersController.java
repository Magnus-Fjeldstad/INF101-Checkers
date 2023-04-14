package no.uib.inf101.sem2.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.view.CheckersView;

public class CheckersController implements MouseListener {

    private final ControllableCheckersPiece controller;
    private final CheckersView checkersView;

    int moveTurn;
    int yGrid;
    int xGrid;
    CellPosition oldPos;
    CellPosition newPos;

    public CheckersController(ControllableCheckersPiece controller, CheckersView checkersView) {
        this.controller = controller;
        this.checkersView = checkersView;
        checkersView.addMouseListener(this);
        checkersView.setFocusable(true);
        this.moveTurn = 1;
        this.yGrid = 0;
        this.xGrid = 0;
        this.newPos = new CellPosition(0, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        this.moveTurn += e.getClickCount();
        this.yGrid = (int) Math.ceil(x / 100);
        this.xGrid = (int) Math.ceil(y / 100);
        createNewCellPosition();
        controller.move(oldPos, newPos);

        checkersView.repaint();
    }

    public void createNewCellPosition() {
        if (moveTurn % 2 == 0) {
            this.oldPos = new CellPosition(xGrid, yGrid);
        }
        if (moveTurn % 2 == 1) {
            this.newPos = new CellPosition(xGrid, yGrid);
        }
        printCellPositionAsString();
    }

    public void printCellPositionAsString() {
        if (oldPos != null && newPos != null) {
            System.out.println("oldPos =" + oldPos.toString());
            System.out.println("newPos =" + newPos.toString());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
