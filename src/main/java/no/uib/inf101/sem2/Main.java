package no.uib.inf101.sem2;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.CheckersModel;
import no.uib.inf101.sem2.model.ChekersBoard;
import no.uib.inf101.sem2.view.CheckersView;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    ChekersBoard board = new ChekersBoard(8, 8);
    CheckersModel model = new CheckersModel(board);
    model.setInitalBoard();
    model.outPutBoard();
    System.out.println("\n");
    model.move(new CellPosition(5, 0), new CellPosition(4, 1));
    model.move(new CellPosition(2, 3), new CellPosition(3, 2));
    model.outPutBoard();
    // CheckersView view = new CheckersView(model);

    // JFrame frame = new JFrame();
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setResizable(false);
    // frame.setTitle("Checkers");
    // frame.setContentPane(view);
    // frame.pack();
    // frame.setVisible(true);
  }
}
