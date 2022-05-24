package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class LBlock extends Block {
	
	public LBlock() throws IOException {
		shape = new int[][] { 
			{1, 2, 1},
			{1, 0, 0}
		};
		item_shape = new int[][][]{
				{
						{3, 2, 1},
						{1, 0, 0}
				},
				{
						{1, 2, 3},
						{1, 0, 0}
				},
				{
						{1, 2, 1},
						{3, 0, 0}
				}
		};
		if(color_weak)
			color= new Color(0x730a71);
		else
			color = Color.ORANGE;
	}
}
