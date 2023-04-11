package no.uib.inf101.sem2.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

public class TestDefaultColorTheme {
    @Test
    public void sanityTestDefaultColorTheme() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(Color.white, colors.getBackgroundColor());
        assertEquals(Color.white, colors.getFrameColor());
        assertEquals(Color.darkGray, colors.getCellColor('-'));
        assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));

    }
}
