package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


import no.uib.inf101.sem2.grid.GridCell;


public class CheckersView extends JPanel {
    // Feltvariabler
    ViewableCheckersModel view;
    ColorTheme colorTheme;

    public CheckersView(ViewableCheckersModel view) {
        this.view = view;
        this.colorTheme = new DefaultColorTheme();
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(600, 600));
    }

    /**
     * @param g draws the game
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    /**
     * DrawsTheGame
     * 
     * @param g2
     * 
     */
    public void drawGame(Graphics2D g2) {
    }

    /**
     * 
     * @param g         "Pencil"
     * @param cell      Each cell in the iterable list GridCell<Charaters> used to
     *                  draw different colors based on a char
     * @param converter Converts a CellPosition to a Pixel
     * @param CT        Color Theme set in DefualtColorTheme
     */
    private static void drawCells(Graphics2D g, Iterable<GridCell<Character>> cell, CellPositionToPixelConverter converter, ColorTheme CT) {
        for (GridCell<Character> gridCell : cell) {
            Color color = CT.getCellColor(gridCell.value());
            Rectangle2D rect = converter.getBoundsForCell(gridCell.pos());
            g.setColor(color);
            g.fill(rect);

            if(gridCell.value()!= 'w' || gridCell.value() != 'b'){
                
            }
        }
    }
}