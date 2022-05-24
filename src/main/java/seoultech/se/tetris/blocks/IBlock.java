package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class IBlock extends Block {
	
	public IBlock() throws IOException {
		shape = new int[][] { 
			{1, 2, 1, 1}
		};
		item_shape = new int[][][]{
				{
						{3, 2, 1, 1}
				},
				{
						{1, 2, 3, 1}
				},
				{
						{1, 2, 1, 3}
				}
		};
		if(color_weak)
			color= new Color(0x56b5e3);
		else
			color = Color.CYAN;
	}
}
