package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

public class TestCheckersBoard {
    @Test
    public void testPrettyString() {
        ChekersBoard board = new ChekersBoard(3, 4);
        board.set(new CellPosition(0, 0), 'g');
        board.set(new CellPosition(0, 3), 'y');
        board.set(new CellPosition(2, 0), 'r');
        board.set(new CellPosition(2, 3), 'b');
        String expected = String.join("\n", new String[] {
                "gwby",
                "wbwb",
                "rwbb"
        });
        assertEquals(expected, board.prettyString());
    }


    @Test
    public void testBoard() {
        ChekersBoard board = new ChekersBoard(8, 8);
        board.drawCheckersBoard();
        String expected = String.join("\n", new String[] {
            "bwbwbwbw",
            "wbwbwbwb",
            "bwbwbwbw",
            "wbwbwbwb",
            "bwbwbwbw",
            "wbwbwbwb",
            "bwbwbwbw",
            "wbwbwbwb"
        });
        assertEquals(expected, board.prettyString());
    }

}
