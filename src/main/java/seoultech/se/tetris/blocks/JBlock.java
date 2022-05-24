package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class JBlock extends Block {
	
	public JBlock() throws IOException {
		shape = new int[][] { 
				{1, 2, 1},
				{0, 0, 1}
		};
		item_shape = new int[][][]{
				{
						{3, 2, 1},
						{0, 0, 1}
				},
				{
						{1, 2, 3},
						{0, 0, 1}
				},
				{
						{1, 2, 1},
						{0, 0, 3}
				}
		};
		if(color_weak)
			color= new Color(0x0072b1);
		else
			color = Color.BLUE;
	}
}
