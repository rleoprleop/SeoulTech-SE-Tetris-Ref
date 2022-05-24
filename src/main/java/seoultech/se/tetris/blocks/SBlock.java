package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class SBlock extends Block {

	public SBlock() throws IOException {
		shape = new int[][] { 
			{0, 1, 1},
			{1, 1, 0}
		};
		item_shape = new int[][][]{
				{
						{0, 3, 1},
						{1, 1, 0}
				},
				{
						{0, 1, 3},
						{1, 1, 0}
				},
				{
						{0, 1, 1},
						{3, 1, 0}
				},
				{
						{0, 1, 1},
						{1, 3, 0}
				}
		};
		if(color_weak)
			color= new Color(0x009f73);
		else
			color = Color.GREEN;
	}
}
