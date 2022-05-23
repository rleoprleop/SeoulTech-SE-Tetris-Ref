package seoultech.se.tetris.main;

import seoultech.se.tetris.component.EndGame;
import seoultech.se.tetris.component.TetrisMenu;
import seoultech.se.tetris.component.VSmode;
import seoultech.se.tetris.component.model.ScoreDataManager;

public class Tetris {

	public static void main(String[] args) {
		TetrisMenu main = new TetrisMenu(600,500);
//		VSmode main = new VSmode(600,500);
//	EndGame ed = new EndGame(500, 500, 40, ScoreDataManager.getInstance().getNormKey());
	}
}