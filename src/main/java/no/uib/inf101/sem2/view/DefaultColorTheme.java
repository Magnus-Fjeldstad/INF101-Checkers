package no.uib.inf101.sem2.view;
import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {
    
    @Override
    public Color getCellColor(char c) {
        Color color = switch (c){
            //BoardColor
            case '-' -> Color.black;
            case 'w' -> Color.white;
            case 'b' -> Color.black;
        
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
        return Color.white;
    }
    
}
