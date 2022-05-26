package seoultech.se.tetris.component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.*;

import seoultech.se.tetris.blocks.*;
import seoultech.se.tetris.component.endGame.EndGame;
import seoultech.se.tetris.component.model.DataManager;
import seoultech.se.tetris.component.pause.Pause;


public class Board extends JFrame {

	private static final long serialVersionUID = 2434035659171694595L;
	
	public static final int HEIGHT = 20;
	public static final int WIDTH = 10;
	public static final int NEXT_WIDTH = 6;
	public static final int NEXT_HEIGHT = 4;
	public static String BORDER_CHAR = "X";
	public static String BLOCK_CHAR = "O";
	public static String BLANK_CHAR = " ";
	public static final String win_BORDER_CHAR = "X";
	public static final String win_BLOCK_CHAR = "O";
	public static final String win_BLANK_CHAR = "     ";
	public static final String mac_BORDER_CHAR = "X";
	public static final String mac_BLOCK_CHAR = "O";
	public static final String mac_BLANK_CHAR = " ";
	public static final String BLOCK_CHAR_LIST = " OOLEDOXXXXX";

	public static String os;
	public static final int animate_idx = 6;

	private BoardLayout mainPanel;
	private JTextPane pane;
	private JPanel main_panel;
	private JTextPane next_pane;
	private JTextPane score_pane;
	private CompoundBorder border;


	private int[][] board;
	private int[][] next_board;
	private Color[][] color_board;
	private KeyListener playerKeyListener;
	private SimpleAttributeSet styleSet;
	private Timer timer;
	private Timer press_timer;
	private Timer erase_timer;
	private Block curr;
	private Block next_block;
	private static boolean ispaused = false;
	int x = 3; //Default Position.
	int y = 0;
	private static int num_eraseline = 0;
	private static int item_rotate = 0;
	private static int total_score = 0;

	private static final int initInterval = 1000;
	int sprint=0;
	private static final int SPMAX=900;

	private int timerflag;
	private int erase_line_check=0;

	private static final int EASY = 72;
	private static final int NORMAL = 70;
	private static final int HARD = 68;
	private static int lev_block = NORMAL; //난이도. easy 72 normal 70 hard 68
	private int display_width;
	private int display_height;
	private int key_left;
	private int key_right;
	private int key_rotate;
	private int key_harddrop;
	private int key_pause;
	private int key_down;
	private String mode;
	private String item_mode = "itemScore";
	private String normal_mode = "normalScore";


	public Board(int x, int y, String mode) throws IOException {
		super("SeoulTech SE Tetris");
		this.mode = mode;
		//read setting
		setting();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(display_width, display_height);
		mainPanel = new BoardLayout(this.getWidth(), this.getHeight());
		this.setLocation(x, y);

		num_eraseline = 0;
		item_rotate = 0;
		total_score = 0;
		sprint =0;

		// readOS
		os = System.getProperty("os.name").toLowerCase();
		//System.out.println(os);
		if(os.contains("win")){
			BORDER_CHAR = win_BORDER_CHAR;
			BLOCK_CHAR = win_BLOCK_CHAR;
			BLANK_CHAR = win_BLANK_CHAR;
		}
		else
		{
			BORDER_CHAR = mac_BORDER_CHAR;
			BLOCK_CHAR = mac_BLOCK_CHAR;
			BLANK_CHAR = mac_BLANK_CHAR;
		}

		//Document default style.
		styleSet = new SimpleAttributeSet();
		StyleConstants.setFontSize(styleSet, display_height/34);
		StyleConstants.setFontFamily(styleSet, "Courier");
		StyleConstants.setBold(styleSet, true);
		StyleConstants.setForeground(styleSet, Color.WHITE);
		StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

		//Set timer for block drops.
		timer = new Timer(initInterval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					moveDown();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				drawBoard();
				//System.out.println(timer.getDelay());
				if(sprint>SPMAX){
					sprint=SPMAX;
				}
				timer.setDelay(initInterval-sprint);
			}
		});

		press_timer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					pressDown();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				drawBoard();
			}
		});

		erase_timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eraseLine();
				drawBoard();
			}
		});

		board = new int[HEIGHT][WIDTH];

		next_board = new int[NEXT_HEIGHT][NEXT_WIDTH];
		color_board = new Color[HEIGHT][WIDTH];
		for(int i=0;i<HEIGHT;i++){
			for(int j=0;j<WIDTH;j++){
				color_board[i][j]=Color.white;
			}
		}//color_board 초기화
		playerKeyListener = new PlayerKeyListener();
		addKeyListener(playerKeyListener);
		setFocusable(true);
		requestFocus();

		setMain_panel();
		setSide_panel();
		this.add(mainPanel);
		this.setVisible(true);

		//Create the first block and draw.
		curr = getRandomBlock();
		next_block = getRandomBlock();
		placeBlock();
		drawBoard();
		timer.start();
		timerflag=1;
	}

	private void setMain_panel(){
		main_panel = new JPanel();

		pane = new JTextPane();
		pane.setEditable(false);
		pane.setBackground(Color.BLACK);
		border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		pane.setBorder(border);
		main_panel.add(pane);
		mainPanel.setBoardPane(main_panel);

	}

	private void setSide_panel(){
		next_pane = new JTextPane();
		next_pane.setEditable(false);
		next_pane.setBackground(Color.BLACK);
		next_pane.setBorder(border);

		score_pane = new JTextPane();
		score_pane.setEditable(false);
		score_pane.setBackground(Color.BLACK);
		score_pane.setBorder(border);
//
//		side_panel = new JPanel();
//		side_panel.add(score_pane, new GridLayout(4,1));
		mainPanel.setSidePane(next_pane);
		mainPanel.setSidePane(score_pane);
	}

	private void setting() throws IOException {
		String lv = DataManager.getInstance().getLevel();
		DataManager.getInstance().setMode(mode);
		switch(lv){
			case "normal":
				lev_block = NORMAL;
				break;
			case "hard":
				lev_block = HARD;
				break;
			case "easy":
				lev_block = EASY;
				break;
		}

		display_width = DataManager.getInstance().getDisPlayWidth();
		display_height = DataManager.getInstance().getDisPlayWidth();

		int code = DataManager.getInstance().getLeft();
		key_left = code;
		code = DataManager.getInstance().getRight();
		key_right = code;
		code = DataManager.getInstance().getRotate();
		key_rotate = code;
		code = DataManager.getInstance().getHarddrop();
		key_harddrop = code;
		code = DataManager.getInstance().getPause();
		key_pause = code;
		code = DataManager.getInstance().getDown();
		key_down = code;
	}


	private Block getRandomBlock() throws IOException {
		//testRandomBlock();
		Random rnd = new Random();
		int block = rnd.nextInt(lev_block);//68 70 72 34 35 36
		if(block<10)
			return new OBlock();
		else if(block<20)
			return new JBlock();
		else if(block<30)
			return new LBlock();
		else if(block<40)
			return new ZBlock();
		else if(block<50)
			return new SBlock();
		else if(block<60)
			return new TBlock();
		else
			return new IBlock();
	}

	private void placeBlock() {
		//System.out.println("width : " + curr.width() + " height : " + curr.height());
		for(int j=0; j<curr.height(); j++) {
			int rows = j;//y+j == 0 ? 0 : y+j-1;
			int offset = x;//rows * (WIDTH+3) + x + 1;
			for(int i=0; i<curr.width(); i++) {
				if(board[y+j][x+i] == 0) {//요게 히트!!! 보드에 0이 아니면 그대로 유지해야함
					board[y + j][x + i] = curr.getShape(i, j);
					color_board[y+j][x+i] = curr.getColor();
				}
				else if(curr.getClass().getName().contains("Press")){
					board[y + j][x + i] = curr.getShape(i, j);
					color_board[y+j][x+i] = curr.getColor();
				}
			}
		}
		placeNextBlock();
	}

	private void placeNextBlock() {

		//System.out.println("width : " + curr.width() + " height : " + curr.height());
		for(int j=0; j<NEXT_HEIGHT; j++){
			for(int i=0; i<NEXT_WIDTH; i++){
				next_board[j][i] = 0;
			}
		}
		for(int j=1; j<next_block.height() + 1; j++){
			for(int i=1; i<next_block.width() + 1; i++){
				next_board[j][i] = next_block.getShape(i-1,j-1);
			}
		}
	}

	private void eraseCurr() {
		for (int i = y; i < y + curr.height(); i++) {
			for (int j = x; j < x + curr.width(); j++)
				if(curr.getShape(j-x,i-y) != 0) // 요것도 히트 무작정 지우면 안됨
					board[i][j] = 0;
		}
	}

	private boolean isBlocked(char move){ //블럭이 갈 수 있는지 확인하는 함수('d' : 아래, 'r' : 오른쪽, 'l' : 왼쪽)
		if(timerflag==0)
			return true;
		if(move == 'd') { //down
			if(y + curr.height() < HEIGHT) {
				for (int i = x; i < x + curr.width(); i++) {
					int lowest = y + curr.height() - 1;
					while(curr.getShape(i-x, lowest-y) == 0)
						lowest--;
					//System.out.println();
					if (board[lowest+1][i] != 0 && curr.getShape(i - x, lowest-y) != 0) {
						return true;
					}
				}
			}
			else return true;
		}
		else if(move == 'l') { //왼쪽으로 갈수있는지 확인
			if(x > 0) {
				for (int i = y; i < y + curr.height(); i++) {
					//System.out.print(x + " " + y + " ");
					int mostLeft = x;
					while(curr.getShape(mostLeft-x, i-y) == 0)
							mostLeft++;
						//if (board[i][x - 1] == 1 && curr.getShape(0, i - y) == 1) {
					if (board[i][mostLeft-1] != 0 && curr.getShape(mostLeft-x, i - y) != 0) {
						return true;
					}
				}
			}
			else return true;
		}
		else if(move == 'r') { //오른쪽으로 갈 수 있는지 확인
			if(x + curr.width() < WIDTH) {
				for (int i = y; i < y + curr.height(); i++) {
					//System.out.print(x + " " + y + " ");
					int mostRight = x + curr.width() - 1;
					while(curr.getShape(mostRight-x, i-y) == 0) mostRight--;
					if(board[i][mostRight + 1] != 0 && curr.getShape(mostRight-x, i-y) != 0){
						return true;
					}
//					if (board[i][x + curr.width()] == 1 && curr.getShape(curr.width() - 1, i - y) == 1) {
//						return true;
//					}
				}
			}
			else return true;
		}
		else if(move == 't') { //돌릴 수 있는지 확인
			if(curr.getClass().getName().contains("Press"))
				return true;
			curr.rotate();
			int tmpX = x + curr.getCentermovedX();
			int tmpY = y + curr.getCentermovedY();
			if(tmpX >= 0 && tmpX + curr.width()-1 < WIDTH && tmpY >= 0 && tmpY + curr.height() < HEIGHT){
				//System.out.println("IN!!");
				for(int i=tmpY; i<tmpY+curr.height(); i++) {
					for (int j = tmpX; j < tmpX + curr.width(); j++) {
						if (board[i][j] != 0 && curr.getShape(j - tmpX, i - tmpY) != 0) {
							curr.rotate();
							curr.rotate();
							curr.rotate();
							return true;
						}
					}
				}
				curr.rotate();
				curr.rotate();
				curr.rotate();
				return false;
			}
			else{
				curr.rotate();
				curr.rotate();
				curr.rotate();
				return true;
			}
		}
		return false;
	}

	private void rotateblock() {
		eraseCurr();
		if(!isBlocked('t')) {
			curr.rotate();
			moveCenter();
		}
		placeBlock();
	}

	protected void eraseLine(){
		int lowest = y + curr.height() -1;
		if(erase_line_check<2){
			erase_line_check++;
			for(int i=lowest;i>=y;i--){
				for(int j=0;j<WIDTH;j++){
					if(board[i][j]>6){
						board[i][j]-=animate_idx;
					}
				}
			}
		}
		else{
			erase_timer.stop();
			timer.start();
		}
		placeBlock();
		drawBoard();
	}

	protected void cal_score(int combo, boolean double_score){
		if(combo > 0) {
			total_score = total_score + combo + combo - 1;
			if(double_score == true)
				total_score *= 2;
		}
		num_eraseline += combo;
		if(mode == item_mode)
			item_rotate += combo;

	}
	protected void animate(int i) {
		for(int j=0; j<WIDTH; j++){
			board[i][j] = animate_idx;
		}
		drawBoard();
	}

	protected void eraseRow() {

		int lowest = y + curr.height() -1;
		boolean double_score = false;
		int curr_erase = 0;

		for(int i = lowest; i>=y; i--){
			boolean canErase = true;
			for(int j = 0; j < WIDTH; j++){
				if(board[i][j] == 0)
				{
					canErase = false;
					break;
				}
			}
			if(mode == item_mode) {
				for(int j = 0; j < WIDTH; j++) {
					if (BLOCK_CHAR_LIST.charAt(board[i][j]) == 'L') {
						canErase = true;
					}
				}
				for(int j=0; j<WIDTH; j++)
				{
					if(BLOCK_CHAR_LIST.charAt(board[i][j]) == 'E'){
						for(int ii = 0; ii<HEIGHT; ii++)
						{
							for(int jj =0; jj<WIDTH; jj++)
								board[ii][jj] = 0;
						}
						canErase = false;
						break;
					}
				}
				for(int j=0; j<WIDTH; j++)
				{
					if(BLOCK_CHAR_LIST.charAt(board[i][j]) == 'D')
					{
						double_score = true;
					}
				}
			}
			if(canErase) {
				curr_erase += 1;
				sprint+=20;
				//animate(i);
				for(int j = 0; j<WIDTH; j++) {
					board[i][j] = 0;
				}
			}
		}
		cal_score(curr_erase, double_score);

//		System.out.println("------------------------");
		for(int i = lowest; i>=0; i--){
			down(i);
//			System.out.println(i);
		}
//		if(earse)  System.out.println(lowest);
	}

	protected boolean checkEraseRow(){
		if(erase_timer.isRunning()||erase_line_check!=0){
			erase_line_check=0;
			return false;
		}
		int lowest = y + curr.height() -1;
		boolean erase=false;
		for(int i = lowest; i>=y; i--){
			boolean canErase = true;
			for(int j = 0; j < WIDTH; j++){
				if(BLOCK_CHAR_LIST.charAt(board[i][j]) == 'L'){//line erase
					canErase=true;
					break;
				}
				if(board[i][j] == 0)
				{
					canErase = false;
				}
			}
			if(canErase) {
				for(int j = 0; j<WIDTH; j++) {
					if(board[i][j]!=0) {
						board[i][j] += animate_idx;
						erase = true;
					}
				}
			}
		}
		return erase;
	}

	protected void down(int row) {
		boolean canDown = true;
		boolean haveBlock = false;
		int swapRow = row + 1;

		while(swapRow < HEIGHT) {
			for (int j = 0; j < WIDTH; j++) {
				if (board[swapRow][j] != 0) {
					canDown = false;
					break;
				}
				if(board[row][j] != 0)	haveBlock = true; // D없애도 되는 지 확인
			}
			if(canDown && haveBlock) swapRow++;
			else break;
		}

		swapRow--;
		for(int j = 0; j<WIDTH; j++){
			int temp = board[row][j];
			board[row][j] = board[swapRow][j];
			board[swapRow][j] = temp;
		}
	}

	protected void ready_next() throws IOException {
		curr = next_block;
		if(mode == item_mode)
		{
			if(true || item_rotate > 4) {
				//item_rotate -= 5;
				next_block = getRandomBlock();
				Random rnd = new Random();
				if(rnd.nextInt(100) < 80)
					next_block.make_item();
				else next_block = new Press();
			}
			else next_block = getRandomBlock();
		}
		else next_block = getRandomBlock();
	}

	protected void pressDown() throws IOException {
		if(y + curr.height() < HEIGHT) {
			eraseCurr();
			y++;
		}
		else{
			press_timer.stop();
			placeBlock();
			for(int i = y; i<y+curr.height(); i++) {
				for (int j = 0; j < WIDTH; j++) {
					if(board[i][j]==6){
						board[i][j] = 0;
					}
				}
			}
			drawBoard();
			timer.start();
			timerflag=1;
			ready_next();
			x = 3;
			y = 0;

		}
		placeBlock();
		drawBoard();
	}

	protected void moveDown() throws IOException { //구조를 조금 바꿈 갈수잇는지 먼저 확인후에 갈수있으면 지우고 이동
		if(!isBlocked('d')) {
			eraseCurr();
			y++;
		}
		else if(isBlocked('d')&&curr.getClass().getName().contains("Press")){
			placeBlock();
			timer.stop();
			timerflag=0;
			press_timer.start();
		}
		else if(checkEraseRow()){
			placeBlock();
			timer.stop();
			timerflag=0;
			erase_timer.start();
		}
		else {
			timerflag=1;
			placeBlock();
			eraseRow();
			ready_next();
			x = 3;
			y = 0;
			if(isBlocked('d')){
				timer.stop();
				new EndGame(this.getLocation().x, this.getLocation().y, total_score, mode);
				this.dispose();
			}
		}
		placeBlock();
		drawBoard();
	}

	protected void moveRight() { //갈수있는지 함수 추가해줌
		eraseCurr();
		if(x < WIDTH - curr.width() && !isBlocked('r')) x++;
		placeBlock();
	}

	protected void moveLeft() { //똑같음
		eraseCurr();
		if(x > 0 && !isBlocked('l')) {
			x--;
		}
		placeBlock();
	}

	public void pause() {
		if(!ispaused){
			ispaused = true;
			timer.stop();
			new Pause(this);

		}
		else{
			this.setVisible(true);
			ispaused = false;
			timer.start();
		}
	}
	protected void harddrop() throws IOException {
		eraseCurr();
		while(!isBlocked('d'))
			y++;
		placeBlock();
		drawBoard();
		moveDown();
	}

	public void drawBoard() {
		int win_extra_border = 4;
		int mac_extra_border = 2;
		int extra_border;
		if(os.contains("win")) {
			extra_border = win_extra_border;
		}
		else {
			extra_border = mac_extra_border;
		}
		StringBuffer sb = new StringBuffer();
		StyledDocument doc = pane.getStyledDocument();
		StyleConstants.setForeground(styleSet, Color.WHITE);
		pane.setText("");
		try {
			for (int t = 0; t < WIDTH + extra_border; t++) {
				doc.insertString(doc.getLength(), BORDER_CHAR, styleSet);//sb.append(BORDER_CHAR);
			}
			//sb.append("\n");
			doc.insertString(doc.getLength(), "\n", styleSet);
			for (int i = 0; i < board.length; i++) {
				//sb.append(BORDER_CHAR);
				doc.insertString(doc.getLength(), BORDER_CHAR, styleSet);
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] != 0) {
						StyleConstants.setForeground(styleSet, color_board[i][j]);
						doc.insertString(doc.getLength(), Character.toString(BLOCK_CHAR_LIST.charAt(board[i][j])), styleSet);
						//sb.append(BLOCK_CHAR);
						StyleConstants.setForeground(styleSet, Color.WHITE);
					} else {
						doc.insertString(doc.getLength(), BLANK_CHAR, styleSet);//머지 조심
						//sb.append(BLANK_CHAR);
					}
				}
				doc.insertString(doc.getLength(), BORDER_CHAR + "\n", styleSet);
			}
			for (int t = 0; t < WIDTH + extra_border; t++) {
				doc.insertString(doc.getLength(), BORDER_CHAR, styleSet);
			}
		} catch (BadLocationException e) {
			System.out.println(e);
		}
		//pane.setText(sb.toString());
		pane.setStyledDocument(doc);
		draw_next();
		draw_score();
	}

	public void draw_score() {
		StyledDocument doc = score_pane.getStyledDocument();
		StyleConstants.setForeground(styleSet, Color.WHITE);
		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		StringBuffer sb = new StringBuffer();
		sb.append("\nScore : ");
		sb.append(total_score);
		score_pane.setText(sb.toString());
		score_pane.setStyledDocument(doc);
	}

	public void draw_next(){
		StringBuffer sb = new StringBuffer();
		StyledDocument doc = next_pane.getStyledDocument();
		StyleConstants.setForeground(styleSet, next_block.getColor());
		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);

		for(int i=0; i < NEXT_HEIGHT; i++) {

			for(int j=0; j < NEXT_WIDTH; j++) {
				if(next_board[i][j] != 0) {
					sb.append(Character.toString(BLOCK_CHAR_LIST.charAt(next_board[i][j])));
				} else {
					sb.append(BLANK_CHAR);
				}
			}
			sb.append("\n");
		}
		next_pane.setText(sb.toString());
		next_pane.setStyledDocument(doc);
	}

	protected void moveCenter() {
		x = x + curr.getCentermovedX();
		y = y + curr.getCentermovedY();
	}

	public void reset() {
		this.board = new int[20][10];
		num_eraseline = 0;
		sprint = 0;
		drawBoard();
	}

	public class PlayerKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(timerflag==0)
				return;
			try {
				if(e.getKeyCode() == key_left) {
					moveLeft();
					drawBoard();
				}
				else if(e.getKeyCode() == key_right) {
					moveRight();
					drawBoard();
				}
				else if(e.getKeyCode() == key_rotate) {
					rotateblock();
					//System.out.println("width : " + curr.width() + " height : " + curr.height());
					drawBoard();
				}
				else if(e.getKeyCode() == key_harddrop) {
					harddrop();
				}
				else if(e.getKeyCode() == key_pause) {
					pause();
				}
				else if(e.getKeyCode() == key_down) {
					moveDown();
					drawBoard();
				}
			}
			catch(IOException ex) {
				System.out.println(ex);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}
	
}
