package seoultech.se.tetris.blocks;

import seoultech.se.tetris.component.model.DataManager;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

public abstract class Block {
		
	protected int[][] shape;
	protected int[][][] item_shape;
	protected Color color;
	protected int centermoved_x;
	protected int centermoved_y;
	protected static boolean color_weak;
	protected String itemMode;
	protected Random rand = new Random();

	public Block() throws IOException {

		shape = new int[][]{ 
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
		if(DataManager.getInstance().getColor_weak().equals("off"))
			color_weak = false;
		else
			color_weak = true;
		color = Color.YELLOW;
		centermoved_x = 0;
		centermoved_y = 0;
		this.itemMode = DataManager.getInstance().getMode();
	}

	public int get_item_shape_length(){
		return this.item_shape.length;
	}

	public void make_item() {
		int rand_int = rand.nextInt(this.item_shape.length);
		shape = this.item_shape[rand_int];
		int num_item = 4;
		int rand_item = rand.nextInt(num_item) +1 + 2;
		for(int i=0; i<shape.length; i++){
			for(int j=0; j<shape[i].length; j++){
				if(shape[i][j] > 2)
					shape[i][j] = rand_item;
			}
		}
	}

	public String getItemMode() {return itemMode;}

	public int getCentermovedX() {
		return centermoved_x;
	}

	public int getCentermovedY() {
		return centermoved_y;
	}

	public int getShape(int x, int y) {
		return shape[y][x];
	}
	
	public Color getColor() {
		return color;
	}
	
	public void rotate() {
		int[][] rotate;
		rotate = new int[width()][height()];
		for(int i=0; i<rotate.length; i++){
			for(int j=0; j<rotate[i].length; j++)
			{
				rotate[i][j] = shape[height()-1-j][i];
				if(rotate[i][j] == 2){
					centermoved_y = (height() -1 -j -i);
					centermoved_x = (i - j);
				}
			}
		}
		shape = new int[rotate.length][rotate[0].length];
		for(int i=0; i<rotate.length; i++){
			for(int j=0; j<rotate[0].length; j++)
				shape[i][j] = rotate[i][j];
		}
		//System.out.println(centermoved_x + " " + centermoved_y);
		//Rotate the block 90 deg. clockwise.
	}

	public void canRotate() {
		int[][] rotate;
		rotate = new int[width()][height()];
		for(int i=0; i<rotate.length; i++){
			for(int j=0; j<rotate[i].length; j++)
			{
				rotate[i][j] = shape[height()-1-j][i];
				if(rotate[i][j] == 2){
					centermoved_y = (height() -1 -j -i);
					centermoved_x = (i - j);
				}
			}
		}
	}
	
	public int height() {
		return shape.length;
	}
	
	public int width() {
		if(shape.length > 0)
			return shape[0].length;
		return 0;
	}
}
