package seoultech.se.tetris.main;

import seoultech.se.tetris.component.Board;

public class Tetris {

	public static void main(String[] args) {
		Board main = new Board();
		main.setSize(500, 600);
		main.setVisible(true);
	}
}