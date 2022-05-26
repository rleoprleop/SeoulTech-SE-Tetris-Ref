package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class Press extends Block {

    public Press() throws IOException {
        shape = new int[][] {
                {0, 7, 7, 0},
                {7, 7, 7, 7}
        };
        if(color_weak)
            color= new Color(0x56b5e3);
        else
            color = Color.WHITE;
    }
}
