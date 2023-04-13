package no.uib.inf101.sem2.view;
import java.awt.Color;

import no.uib.inf101.sem2.model.CheckersBoard;

public class DefaultColorTheme implements ColorTheme {
    CheckersBoard board;

    @Override
    public Color getCellColor(char c) {
        Color color = switch (c){
            //BoardColor
            case '-' -> Color.lightGray;
            case 'w' -> Color.white;
            case 'b' -> Color.black;

            //CheckersPieces
            case 'P' -> Color.blue;
            case 'K' -> Color.pink;
        
        
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
    
}
