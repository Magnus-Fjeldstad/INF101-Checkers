package no.uib.inf101.sem2.view;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.checkers.view.ColorTheme;
import no.uib.inf101.sem2.checkers.view.DefaultColorTheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

public class TestDefaultColorTheme {
    @Test
    public void sanityTestDefaultColorTheme() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(new Color(255,175,175), colors.getBackgroundColor());
        assertEquals(Color.white, colors.getFrameColor());
        assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));

    }
}
