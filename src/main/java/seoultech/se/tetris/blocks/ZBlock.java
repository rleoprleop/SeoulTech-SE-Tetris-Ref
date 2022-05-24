package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class ZBlock extends Block {
	
	public ZBlock() throws IOException {
		shape = new int[][] { 
			{1, 1, 0},
			{0, 1, 1}
		};
		item_shape = new int[][][]{
				{
						{3, 1, 0},
						{0, 1, 1}
				},
				{
						{1, 3, 0},
						{0, 1, 1}
				},
				{
						{1, 1, 0},
						{0, 3, 1}
				},
				{
						{1, 1, 0},
						{0, 1, 3}
				}
		};
		if(color_weak)
			color= new Color(0xcc79a7);
		else
			color = Color.RED;
	}
}
