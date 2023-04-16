package no.uib.inf101.sem2.checkers.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.uib.inf101.sem2.checkers.model.GameState;
import no.uib.inf101.sem2.checkers.model.checkerspiece.AbstractPiece;
import no.uib.inf101.sem2.grid.GridCell;

public class CheckersView extends JPanel {
    // Feltvariabler
    ViewableCheckersModel view;
    ColorTheme colorTheme;

    // CheckersBoard size
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 800 / 8;
    

    public CheckersView(ViewableCheckersModel view) {
        this.view = view;
        this.colorTheme = new DefaultColorTheme();
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 800));
    }
    

    /**
     * @param g draws the game
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawCheckersBoard(g2);
        drawGame(g2);
    }

    /**
     * 
     * @param g draws a checkersBoard
     */
    public void drawCheckersBoard(Graphics g) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int x = col * SQUARE_SIZE;
                int y = row * SQUARE_SIZE;
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    /**
     * DrawsTheGame
     * 
     * @param g2
     */
    public void drawGame(Graphics2D g2) {
        if (view.getGameState() != GameState.GAME_OVER) {
            Rectangle2D rectangle = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
            drawCells(g2, view.getTilesOnBoard(), new CellPositionToPixelConverter(rectangle, view.getDimension(), 0),
                    colorTheme);
        }
        // TODO Draw a screen if gameover
    }

    /**
     * 
     * @param g         "Pencil"
     * @param cell      Each cell in the iterable list GridCell<Charaters> used to
     *                  draw different colors based on a char
     * @param converter Converts a CellPosition to a Pixel
     * @param CT        Color Theme set in DefualtColorTheme
     */
    private static void drawCells(Graphics2D g, Iterable<GridCell<AbstractPiece>> cell,
            CellPositionToPixelConverter converter, ColorTheme CT) {
        for (GridCell<AbstractPiece> gridCell : cell) {
            Color color = CT.getCellColor(gridCell.value().getTeam());
            Rectangle2D rect = converter.getBoundsForCell(gridCell.pos());
            // Variabler for å tegne en sirkel brikke
            double centerX = rect.getCenterX();
            double centerY = rect.getCenterY();
            double radius = rect.getWidth() / 2;
            // Converts the rectangle to a circle

            Ellipse2D circle = new Ellipse2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2);
            g.setColor(color);
            g.fill(circle);
            if ((gridCell.value().getPieceType()) == 'K') {
                color = Color.yellow;
                g.setColor(color);
                g.fill(circle);
            }

        }
    }
}