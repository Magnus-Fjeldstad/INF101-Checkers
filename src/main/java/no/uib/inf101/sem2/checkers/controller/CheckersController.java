package no.uib.inf101.sem2.checkers.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.sem2.checkers.model.CheckersModel;
import no.uib.inf101.sem2.checkers.model.GameState;
import no.uib.inf101.sem2.checkers.view.CheckersView;
import no.uib.inf101.sem2.grid.CellPosition;

public class CheckersController implements MouseListener, java.awt.event.KeyListener  {

    ControllableCheckersPiece controller;
    private final CheckersView checkersView;
    CheckersModel model;

    public CellPosition oldPos;
    private CellPosition newPos;

    public CheckersController(ControllableCheckersPiece controller, CheckersView checkersView, CheckersModel model) {
        this.controller = controller;
        this.checkersView = checkersView;
        this.model = model;
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
            int xCordGrid = (int) e.getY()/(checkersView.getHeight()/8);
            int yCordGrid = (int) e.getX()/(checkersView.getWidth()/8);
        
            oldPos = new CellPosition(xCordGrid, yCordGrid);
            this.model.setSelected(oldPos);
            checkersView.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(controller.getGameState() == GameState.ACTIVE_GAME){
            int xCordGrid = (int) e.getY()/(checkersView.getHeight()/8);
            int yCordGrid = (int) e.getX()/(checkersView.getWidth()/8);

            newPos = new CellPosition(xCordGrid, yCordGrid);
            if(controller.isLegalMove(oldPos, newPos)){
                controller.move(oldPos, newPos);   
            }
            this.model.setSelected(newPos);          
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
}
