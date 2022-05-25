package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class Press extends Block {

    public Press() throws IOException {
        shape = new int[][] {
                {0, 6, 6, 0},
                {6, 6, 6, 6}
        };
        if(color_weak)
            color= new Color(0x56b5e3);
        else
            color = Color.WHITE;
    }
}
