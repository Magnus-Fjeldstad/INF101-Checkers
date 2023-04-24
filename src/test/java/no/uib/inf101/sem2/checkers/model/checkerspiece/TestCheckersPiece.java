package no.uib.inf101.sem2.checkers.model.checkerspiece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.checkers.model.CheckersBoard;
import no.uib.inf101.sem2.checkers.model.CheckersModel;
import no.uib.inf101.sem2.grid.CellPosition;

public class TestCheckersPiece {
    @Test
    public void testGetNewCheckersPiece() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(3, 2), factory.getNext('K', 'w') );
        board.set(new CellPosition(5, 5), factory.getNext('P', 'w') );
        String expected = String.join("\n", new String[] {
            "--------",
            "--------",
            "--------",
            "--K-----",
            "--------",
            "-----P--",
            "--------",
            "--------"
        });
        assertEquals(expected, model.outPutBoard());
    }

    @Test
    public void testGetTeam() {
        CheckersBoard board = new CheckersBoard(8, 8);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(3, 2), factory.getNext('K', 'w') );
        board.set(new CellPosition(5, 5), factory.getNext('P', 'w') );

        board.get(new CellPosition(3, 2)).getTeam();
        char expected = 'w';
        assertEquals(expected, 'w');
    }

    @Test
    public void testGetTeamAndPieceType() {
        CheckersBoard board = new CheckersBoard(8, 8);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(3, 2), factory.getNext('K', 'w') );
        board.set(new CellPosition(5, 5), factory.getNext('P', 'w') );

        board.get(new CellPosition(3, 2)).getTeam();
        board.get(new CellPosition(3, 2)).getPieceType();
        String expected = "wP";
        assertEquals(expected, "wP");
    }
}
