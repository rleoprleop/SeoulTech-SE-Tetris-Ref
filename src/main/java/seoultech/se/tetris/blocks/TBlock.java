package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class TBlock extends Block {
	
	public TBlock() throws IOException {
		shape = new int[][] { 
			{0, 1, 0},
			{1, 2, 1}
		};
		item_shape = new int[][][]{
				{
						{0, 1, 0},
						{3, 2, 1}
				},
				{
						{0, 3, 0},
						{1, 2, 1}
				},
				{
						{0, 1, 0},
						{1, 2, 3}
				}
		};
		if(color_weak)
			color= new Color(0xd5933e);
		else
			color = Color.MAGENTA;
	}
}
