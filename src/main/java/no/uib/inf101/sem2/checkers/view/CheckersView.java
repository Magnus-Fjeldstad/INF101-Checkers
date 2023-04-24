package no.uib.inf101.sem2.checkers.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import no.uib.inf101.sem2.checkers.model.GameState;
import no.uib.inf101.sem2.checkers.model.checkerspiece.AbstractPiece;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;

public class CheckersView extends JPanel {
    // Feltvariabler
    ViewableCheckersModel view;
    ColorTheme colorTheme;

    public CheckersView(ViewableCheckersModel view) {
        this.view = view;
        this.colorTheme = new DefaultColorTheme();
        this.setFocusable(true);
        this.setPreferredSize((new Dimension(800, 800)));
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
     * Method that draws a checkersboard
     * 
     * @param g draws a checkersBoard
     */
    private void drawCheckersBoard(Graphics g) {
        int sizeX = getWidth();
        int sizeY = getHeight();
        int squareSizeX = sizeX / 8;
        int squareSizeY = sizeY / 8;
        //Makes alternating tiles white/black
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int x = col * squareSizeX;
                int y = row * squareSizeY;
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x, y, squareSizeX, squareSizeY);
            }
        }
    }

    /**
     * Method that draws the game based on gameStates
     * 
     * @param g2
     */
    public void drawGame(Graphics2D g2) {
        if (view.getGameState() == GameState.ACTIVE_GAME) {
            drawCheckersBoard(g2);
            Rectangle2D rectangle = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
            drawHoverPos(g2, view.selectedPos(), new CellPositionToPixelConverter(rectangle, view.getDimension(), 0), colorTheme);
            drawCells(g2, view.getTilesOnBoard(), new CellPositionToPixelConverter(rectangle, view.getDimension(), 0));
        }
        if (view.getGameState() == GameState.GAME_OVER) {
            if(view.getcurrentPlayer()== 'w'){
                try {
                    Image background = ImageIO.read(new File("./src/main/resources/whiteWinn.jpg"));
                    g2.drawImage(background, 0, 0, null);
                } catch (IOException IOException) {
                    System.out.println("Image does not exist");
                }
            }

            if(view.getcurrentPlayer()== 'b'){
                try {
                    Image background = ImageIO.read(new File("./src/main/resources/blackWinn.jpg"));
                    g2.drawImage(background, 0, 0, null);
                } catch (IOException IOException) {
                    System.out.println("Image does not exist");
                }
            }
        }

        if (view.getGameState() == GameState.START_SCREEN) {
            try {
                Image background = ImageIO.read(new File("./src/main/resources/startScreenF1.jpg"));
                g2.drawImage(background, 0, 0, null);
            } catch (IOException IOException) {
                System.out.println("Image does not exist");
            }

        }
    }

    /**
     * Method to draw cells in the grid
     * 
     * @param g         "Pencil"
     * @param cell      Each cell in the iterable list GridCell<Charaters> used to
     *                  draw different colors based on a char
     * @param converter Converts a CellPosition to a Pixel
     */
    private static void drawCells(Graphics2D g, Iterable<GridCell<AbstractPiece>> cell,
            CellPositionToPixelConverter converter) {
        for (GridCell<AbstractPiece> gridCell : cell) {
            Rectangle2D rect = converter.getBoundsForCell(gridCell.pos());

            Image image = getImageForPiece(gridCell.value());
            if (image != null) {
                g.drawImage(image, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(),
                        null);
            }
        }     
    }

    private static void drawHoverPos(Graphics2D g, CellPosition hoverPos, CellPositionToPixelConverter converter, ColorTheme CT) {
        Rectangle2D rect = converter.getBoundsForCell(hoverPos);
        Color hoverColor = CT.getHoverColor();
        g.setColor(hoverColor);
        g.fill(rect);
    }
    /**
     * Method to get the filepath of each piece
     * 
     * @param piece takes in each abstract piece that isnt a blanc piece '-'
     * @return the filepath for the wanted piece
     */
    private static Image getImageForPiece(AbstractPiece piece) {
        if (piece.pieceType != '-') {
            String imageFolder = "./src/main/resources/";
            String imagePath = imageFolder + piece.getTeam() + piece.getPieceType() + ".png/";

            try {
                return ImageIO.read(new File(imagePath));
            } catch (Exception IOException) {
                System.out.println("No file named" + imageFolder);
            }
        }
        return null;
    }
}
