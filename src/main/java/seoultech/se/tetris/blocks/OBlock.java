package seoultech.se.tetris.blocks;

import java.awt.Color;
import java.io.IOException;

public class OBlock extends Block {

	public OBlock() throws IOException {
		shape = new int[][] { 
			{1, 1}, 
			{1, 1}
		};
		item_shape = new int[][][]{
				{
						{3,1},
						{1,1}
				},
				{
						{1,3},
						{1,1}
				},
				{
						{1,1},
						{3,1}
				},
				{
						{1,1},
						{1,3}
				}
		};
		if(color_weak)
			color= new Color(0xf2e245);
		else
			color = Color.YELLOW;
	}
}
