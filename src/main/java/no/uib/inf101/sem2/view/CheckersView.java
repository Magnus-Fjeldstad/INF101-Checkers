package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;


import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.checkerspiece.AbstractPiece;


public class CheckersView extends JPanel {
    // Feltvariabler
    ViewableCheckersModel view;
    ColorTheme colorTheme;
    
    //CheckersBoard size
    private static int SIZE = 8;
    private int SQUARE_SIZE = 800/8;
    
    

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
    public void drawCheckersBoard(Graphics g){
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
     * @param g2
     */
    public void drawGame(Graphics2D g2) {
        Rectangle2D rectangle = new Rectangle2D.Double(0 , 0, this.getWidth() , this.getHeight() );
        drawCells(g2, view.getTilesOnBoard(), new CellPositionToPixelConverter(rectangle, view.getDimension(), 0), colorTheme);
    }

    /**
     * 
     * @param g         "Pencil"
     * @param cell      Each cell in the iterable list GridCell<Charaters> used to
     *                  draw different colors based on a char
     * @param converter Converts a CellPosition to a Pixel
     * @param CT        Color Theme set in DefualtColorTheme
     */
    private static void drawCells(Graphics2D g, Iterable<GridCell<AbstractPiece>> cell, CellPositionToPixelConverter converter, ColorTheme CT) {
        for (GridCell<AbstractPiece> gridCell : cell) {
            Color color = CT.getCellColor(gridCell.value().getTeam());
            Rectangle2D rect = converter.getBoundsForCell(gridCell.pos());
            //Variabler for å tegne en sirkel brikke
            double centerX = rect.getCenterX();
            double centerY = rect.getCenterY();
            double radius = rect.getWidth()/2;
            //Converts the rectangle to a circle
            Ellipse2D circle = new Ellipse2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2);
            g.setColor(color);
            g.fill(circle);
        }
    }
}