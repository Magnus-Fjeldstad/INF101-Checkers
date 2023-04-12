package no.uib.inf101.sem2;

import no.uib.inf101.sem2.model.CheckersModel;
import no.uib.inf101.sem2.model.ChekersBoard;
import no.uib.inf101.sem2.view.CheckersView;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    ChekersBoard board = new ChekersBoard(8, 8);
    CheckersModel model = new CheckersModel(board);
    
    board.drawCheckersBoard();
    board.setInitalBoard();
    

    CheckersView view = new CheckersView(model);
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setTitle("Checkers");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
