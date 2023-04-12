package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TestCheckersBoard {
    
    @Test
    public void testBoard() {
        ChekersBoard board = new ChekersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        model.setInitalBoard();
        model.outPutBoard();
        String expected = String.join("\n", new String[] {
            "-P-P-P-P",
            "P-P-P-P-",
            "-P-P-P-P",
            "--------",
            "--------",
            "P-P-P-P",
            "-P-P-P-P",
            "P-P-P-P-"
        });
        assertEquals(expected, board.prettyString());
    }

}
