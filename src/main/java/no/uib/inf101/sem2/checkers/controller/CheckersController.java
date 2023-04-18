package no.uib.inf101.sem2.checkers.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.sem2.checkers.model.GameState;
import no.uib.inf101.sem2.checkers.view.CheckersView;
import no.uib.inf101.sem2.grid.CellPosition;

public class CheckersController implements MouseListener, java.awt.event.KeyListener  {

    private final ControllableCheckersPiece controller;
    private final CheckersView checkersView;

    CellPosition oldPos;
    CellPosition newPos;

    public CheckersController(ControllableCheckersPiece controller, CheckersView checkersView) {
        this.controller = controller;
        this.checkersView = checkersView;
        checkersView.addMouseListener(this);
        checkersView.addKeyListener(this);
        checkersView.setFocusable(true);
        this.oldPos = new CellPosition(0, 0);
        this.newPos = new CellPosition(0, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(controller.getGameState() == GameState.ACTIVE_GAME){
            int xCoordinat = e.getX();
            int yCoordinat = e.getY();

            int yCordGrid = (int) Math.ceil(xCoordinat / 100);
            int xCordGrid = (int) Math.ceil(yCoordinat / 100);

            oldPos = new CellPosition(xCordGrid, yCordGrid);
            System.out.println("this is the oldPos" + oldPos.toString());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(controller.getGameState() == GameState.ACTIVE_GAME){
            int xCoordinat = e.getX();
            int yCoordinat = e.getY();

            int yCordGrid = (int) Math.ceil(xCoordinat / 100);
            int xCordGrid = (int) Math.ceil(yCoordinat / 100);
            newPos = new CellPosition(xCordGrid, yCordGrid);
            System.out.println("this is the newPos" + newPos.toString());
            if(controller.isLegalMove(oldPos, newPos)){
                controller.move(oldPos, newPos);
            }
            
            checkersView.repaint();   
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {  
        if(controller.getGameState()== GameState.START_SCREEN){
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                controller.setGameState(GameState.ACTIVE_GAME);
                checkersView.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public CellPosition getHoverPos(){
        return this.oldPos;
    }
    
}
