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
        this.oldPos = new CellPosition(0, 0);
        this.newPos = new CellPosition(0, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO
    }


    @Override
    public void mousePressed(MouseEvent e) {
        int xCoordinat = e.getX();
        int yCoordinat = e.getY();

        int yCordGrid = (int) Math.ceil(xCoordinat / 100);
        int xCordGrid = (int) Math.ceil(yCoordinat / 100);

        oldPos = new CellPosition(xCordGrid, yCordGrid);
        System.out.println("this is the oldPos" + oldPos.toString());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int xCoordinat = e.getX();
        int yCoordinat = e.getY();

        int yCordGrid = (int) Math.ceil(xCoordinat / 100);
        int xCordGrid = (int) Math.ceil(yCoordinat / 100);
        newPos = new CellPosition(xCordGrid, yCordGrid);
        System.out.println("this is the newPos" + newPos.toString());
        controller.move(oldPos, newPos);
        checkersView.repaint();   
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
