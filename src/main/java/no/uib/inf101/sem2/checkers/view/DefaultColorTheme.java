package no.uib.inf101.sem2.checkers.view;
import java.awt.Color;


public class DefaultColorTheme implements ColorTheme {


    @Override
    public Color getCellColor(char c) {
        Color color = switch (c){
            //CheckersPieceColors
            case '-' -> new Color(0, 0, 0,0); 
            default -> throw new IllegalArgumentException("No available color for '" + c + "'");            
            };
        return color;
    }
    
    @Override
    public Color getFrameColor() {
        return Color.white;
    }

   
    @Override
    public Color getBackgroundColor(){       
        return Color.pink;
    }

    @Override
    public Color getGameoverColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color getHoverColor() {
        return new Color(255, 255, 0, 200);
    }

    @Override
    public Color getLegalPosColor() {
        return new Color(173, 216, 230);
    }
}
