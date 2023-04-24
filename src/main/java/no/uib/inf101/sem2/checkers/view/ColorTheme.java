package no.uib.inf101.sem2.checkers.view;
import java.awt.Color;

public interface ColorTheme {
    /**
   * Gets the color of the cell.
   *
   * @param c Converts the char "c" to a color
   * @return the color of the cell
   */
  Color getCellColor(char c);



  /**
 * Sets the color of the frame of the board.
 *
 * @return the color of the frame
 */
  Color getFrameColor();


  /**
 * Sets the color of the frame of the background.
 *
 * @return the color of the background
 */
  Color getBackgroundColor();

  /**
   * 
   * @return gameover color
   */
  Color getGameoverColor();

  /**
   * 
   * @return hover Color;
   */
  Color getHoverColor();

  
}
