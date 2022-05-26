package seoultech.se.tetris.component;

import seoultech.se.tetris.blocks.*;
import seoultech.se.tetris.component.endGame.VsModeEndGame;
import seoultech.se.tetris.component.model.DataManager;
import seoultech.se.tetris.component.pause.PauseVsMode;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

public class VSmode extends JFrame {
    private Player p1, p2;
    private int sirialp1=987654321, sirialp2=123456789;
    private CompoundBorder border;

    private static boolean ispaused = false;
    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
    public static final int NEXT_WIDTH = 6;
    public static final int NEXT_HEIGHT = 4;
    public static final int ATTACK_HEIGHT = 10;
    public static final int ATTACK_WIDTH = 10;
    public static String BORDER_CHAR = "X";
    public static String BLOCK_CHAR = "O";
    public static String BLANK_CHAR = " ";

    //ready_game vs_mode
    private KeyListener playerKeyListener;
    private SimpleAttributeSet styleSet, styleSet2;
    private Timer timer,timeattack;
    private int time = 0;
    private int time_limit = 30;

    //key setted
    private int display_width = 1000;
    private int display_height = 840;
    private int key_left1, key_left2;
    private int key_right1, key_right2;
    private int key_rotate1, key_rotate2;
    private int key_harddrop1, key_harddrop2;
    private int key_pause1;
    private int key_down1, key_down2;
    private String mode;

    public VSmode(int x, int y, String mode) throws IOException{
        super("SeoulTech SE Tetris VS Mode");

        this.mode = mode;

        this.setLocation(x, y);
        this.setLayout(new BorderLayout(10,10));
        JPanel mainPanel = new JPanel(new GridLayout(1,2,10,10));
        JPanel timePanel = new JPanel(new GridLayout(1,1,10,10));
        timePanel.setBorder(new EmptyBorder(0,80,0,80));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(display_width, display_height);
        playerKeyListener = new VSmode.PlayerKeyListener();
        addKeyListener(playerKeyListener);
        setFocusable(true);
        setting();

        p1 = new Player();
        p2 = new Player();

        p1.sirial = sirialp1;
        p2.sirial = sirialp2;

        setPlayer(p1);
        setPlayer(p2);

        mainPanel.add(p1.player);
        mainPanel.add(p2.player);
        timePanel.add(p1.time_pane);

        this.add(timePanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, display_height/34);
        StyleConstants.setFontFamily(styleSet, "Courier");
        StyleConstants.setBold(styleSet, true);
        StyleConstants.setForeground(styleSet, Color.WHITE);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        styleSet2 = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet2, display_height/34/2);
        StyleConstants.setFontFamily(styleSet2, "Courier");
        StyleConstants.setBold(styleSet2, true);
        StyleConstants.setForeground(styleSet2, Color.WHITE);
        StyleConstants.setAlignment(styleSet2, StyleConstants.ALIGN_CENTER);

        timeattack = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw_time(p1);
                draw_time(p2);
                time++;
                if(mode == "TimeAttack" && time > time_limit)
                {
                    String name = whoiswin(p1.total_score, p2.total_score, "timeover", mode);
                    end_game(name);
                }
            }
        });


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    moveDown(p1);
                    moveDown(p2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                timer.setDelay(1000);
            }
        });

        timer.start();
        timeattack.start();
        setTitle("VSMode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setting()  {
        int code = DataManager.getInstance().getLeft();
        //1p
        key_left1 = code;
        code = DataManager.getInstance().getRight();
        key_right1 = code;
        code = DataManager.getInstance().getRotate();
        key_rotate1 = code;
        code = DataManager.getInstance().getHarddrop();
        key_harddrop1 = code;
        code = DataManager.getInstance().getPause();
        key_pause1 = code;
        code = DataManager.getInstance().getDown();
        key_down1 = code;

        //2p
        code = DataManager.getInstance().getLeft2();
        key_left2 = code;
        code = DataManager.getInstance().getRight2();
        key_right2 = code;
        code = DataManager.getInstance().getRotate2();
        key_rotate2 = code;
        code = DataManager.getInstance().getHarddrop2();
        key_harddrop2 = code;
        code = DataManager.getInstance().getDown2();
        key_down2 = code;
    }

    private void setMain_panel(Player p){
        p.pane.setEditable(false);
        p.pane.setBackground(Color.BLACK);
        border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        p.pane.setBorder(border);
        p.main_panel.add(p.pane);
        p.player.setBoardPane(p.main_panel);
    }

    private void setSide_panel(Player p){
        p.next_pane.setEditable(false);
        p.next_pane.setBackground(Color.BLACK);
        p.next_pane.setBorder(border);

        p.score_pane.setEditable(false);
        p.score_pane.setBackground(Color.BLACK);
        p.score_pane.setBorder(border);

        p.attack_pane.setEditable(false);
        p.attack_pane.setBackground(Color.BLACK);
        p.attack_pane.setBorder(border);

        p.time_pane.setEditable(false);
        p.time_pane.setBackground(Color.BLACK);
        p.time_pane.setBorder(border);

//        p.player.setSidePane(p.time_pane);
        p.player.setSidePane(p.next_pane);
        p.player.setSidePane(p.score_pane);
        p.player.setSidePane(p.attack_pane);
    }

    private void setPlayer(Player p) throws IOException {
        p.player = new BoardLayout(this.getX(), this.getY());
        p.main_panel = new JPanel();
        p.pane = new JTextPane();
        setMain_panel(p);
        p.next_pane = new JTextPane();
        p.score_pane = new JTextPane();
        p.attack_pane = new JTextPane();
        p.time_pane = new JTextPane();
        setSide_panel(p);

        p.board = new int[HEIGHT][WIDTH];
        p.next_board = new int[NEXT_HEIGHT][NEXT_WIDTH];
        p.attack_board = new int[ATTACK_HEIGHT][ATTACK_WIDTH];
        p.color_board = new Color[HEIGHT][WIDTH];
        for(int i=0; i<HEIGHT; i++)
            for(int j=0; j<WIDTH; j++)
                p.color_board[i][j] = Color.WHITE;
        p.curr = getRandomBlock();
        p.next_block = getRandomBlock();
    }

    private Block getRandomBlock() throws IOException {
        //testRandomBlock();
        Random rnd = new Random();
        int block = rnd.nextInt(70);
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

    public void drawBoard(Player p) {
        int extra_border = 2;

        JTextPane pane = p.pane;
        int[][] board = p.board;
        Color[][] color_board = p.color_board;

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
                        doc.insertString(doc.getLength(), BLOCK_CHAR, styleSet);
                        //sb.append(BLOCK_CHAR);
                        StyleConstants.setForeground(styleSet, Color.WHITE);
                    } else {
                        doc.insertString(doc.getLength(), BLANK_CHAR, styleSet);
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

        pane.setStyledDocument(doc);
        draw_next(p);
        draw_score(p);
    }

    public void draw_score(Player p) {
        JTextPane score_pane = p.score_pane;
        int total_score = p.total_score;

        StyledDocument doc = score_pane.getStyledDocument();
        StyleConstants.setForeground(styleSet, Color.WHITE);
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StringBuffer sb = new StringBuffer();
        sb.append("\nScore : ");
        sb.append(total_score);
        score_pane.setText(sb.toString());
        score_pane.setStyledDocument(doc);
    }

    public void draw_time(Player p) {
        JTextPane time_pane = p.time_pane;
        int final_time = time;
        if(mode == "TimeAttack")
            final_time = time_limit-final_time;

        StyledDocument doc = time_pane.getStyledDocument();
        StyleConstants.setForeground(styleSet, Color.WHITE);
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StringBuffer sb = new StringBuffer();
        sb.append("Time : ");
        sb.append(final_time);
        time_pane.setText(sb.toString());
        time_pane.setStyledDocument(doc);
    }

    public void draw_next(Player p){
        JTextPane next_pane = p.next_pane;
        Block next_block = p.next_block;
        int[][] next_board = p.next_board;
        StringBuffer sb = new StringBuffer();
        StyledDocument doc = next_pane.getStyledDocument();
        StyleConstants.setForeground(styleSet, next_block.getColor());
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        for(int i=0; i < NEXT_HEIGHT; i++) {
            for(int j=0; j < NEXT_WIDTH; j++) {
                if(next_board[i][j] != 0) {
                    sb.append(BLOCK_CHAR);
                } else {
                    sb.append(BLANK_CHAR);
                }
            }
            sb.append("\n");
        }
        next_pane.setText(sb.toString());
        next_pane.setStyledDocument(doc);
    }

    private void placeBlock(Player p) {
        //System.out.println("width : " + curr.width() + " height : " + curr.height());
        Block curr = p.curr;
        int[][] board = p.board;
        Color[][] color_board = p.color_board;
        int x = p.x;
        int y = p.y;

        for(int j=0; j<curr.height(); j++) {
            int rows = j;
            int offset = x;
            for(int i=0; i<curr.width(); i++) {
                if(board[y+j][x+i] == 0) {
                    board[y + j][x + i] = curr.getShape(i, j);
                    color_board[y+j][x+i] = curr.getColor();
                }
            }
        }
        placeNextBlock(p);
    }

    private void placeNextBlock(Player p) {
        int[][] next_board = p.next_board;
        Block next_block = p.next_block;

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

    private void placeAttackBlock(Player p, int[][] attack) {
        p.attack_board = upblock(p.attack_board, attack);
//        for(int i=0; i<attack.length; i++) {
//            for (int j = 0; j < attack[i].length; j++)
//                System.out.print(attack[i][j]);
//            System.out.println();
//        }
//        System.out.println();
        drawAttackBoard(p);
    }
    private int[][] upblock(int[][] ori_board, int[][] new_board){
        int up_num = 0;
        int attack_num = 0;

        for(int i=ori_board.length-1 ; i>=0; i--){
            int j;
            for(j=0; j<ori_board[0].length; j++){
                if(ori_board[i][j] != 0) break;
            }
            if(j == ori_board[0].length) break;
            else up_num++;
        }

        for(int i= new_board.length-1; i>=0; i--){
            int j;
            for(j=0; j<new_board[0].length; j++){
                if(new_board[i][j] != 0) break;
            }
            if(up_num + attack_num >= ori_board.length) break;
            if(j == new_board[0].length) break;
            else attack_num++;
        }
        //System.out.println(up_num + " " + attack_num);

        for(int i = ori_board.length - up_num; i< ori_board.length; i++){
            for(int j=0; j<ori_board[0].length; j++){
                ori_board[i-attack_num][j] = ori_board[i][j];
            }
        }
        for(int i= ori_board.length- attack_num; i < ori_board.length; i++){
            for(int j=0; j<ori_board[0].length; j++) {
                ori_board[i][j] = new_board[(new_board.length-attack_num) + i - (ori_board.length-attack_num)][j];
            }
        }
        return ori_board;
    }

    private void drawAttackBoard(Player p){
        JTextPane attack_pane = p.attack_pane;
        int[][] attack_board = p.attack_board;
        StringBuffer sb = new StringBuffer();
        StyledDocument doc = attack_pane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet2, false);
        for(int i=0; i < ATTACK_HEIGHT; i++) {
            for(int j=0; j < ATTACK_WIDTH; j++) {
                if(attack_board[i][j] != 0) {
                    sb.append(BLOCK_CHAR);
                } else {
                    sb.append(BLANK_CHAR);
                }
            }
            sb.append("\n");
        }
        attack_pane.setText(sb.toString());
        attack_pane.setStyledDocument(doc);
    }

    public void pause() {
        if(!ispaused){
            ispaused = true;
            timer.stop();
            timeattack.stop();
            new PauseVsMode(this);
        }
        else{
            this.setVisible(true);
            ispaused = false;
            timer.start();
            timeattack.start();
        }
    }

    private void eraseCurr(Player p) {
        int x = p.x;
        int y = p.y;
        Block curr = p.curr;
        int[][] board = p.board;
        for (int i = y; i < y + curr.height(); i++) {
            for (int j = x; j < x + curr.width(); j++)
                if(curr.getShape(j-x,i-y) != 0) // 요것도 히트 무작정 지우면 안됨
                    board[i][j] = 0;
        }
    }

    protected void moveRight(Player p) {
        eraseCurr(p);
        int x = p.x;
        Block curr = p.curr;

        if(x < WIDTH - curr.width() && !isBlocked('r', p)) p.x++;
        placeBlock(p);
    }

    protected void moveLeft(Player p) {
        eraseCurr(p);
        int x = p.x;

        if(x > 0 && !isBlocked('l', p)) p.x--;
        placeBlock(p);
    }

    private void rotateblock(Player p) {
        eraseCurr(p);
        Block curr = p.curr;

        if(!isBlocked('t', p)) {
            curr.rotate();
            moveCenter(p);
        }
        placeBlock(p);
    }

    protected void moveCenter(Player p) {
        Block curr = p.curr;

        p.x = p.x + curr.getCentermovedX();
        p.y = p.y + curr.getCentermovedY();
    }

    protected void harddrop(Player p) throws IOException {
        eraseCurr(p);

        while(!isBlocked('d',p))
            p.y++;
        placeBlock(p);
        drawBoard(p);
        moveDown(p);
    }

    protected void moveDown(Player p) throws IOException { //구조를 조금 바꿈 갈수잇는지 먼저 확인후에 갈수있으면 지우고 이동
        int total_score = p.total_score;
        String loser;

        if(p.sirial == sirialp1) loser = "Player1";
        else loser = "Player2";

        if(!isBlocked('d',p)) {
            eraseCurr(p);
            p.y++;
        }
        else {
            placeBlock(p);
            eraseRow(p);
            at(p);
            ready_next(p);
            p.x = 3;
            p.y = 0;
            if(isBlocked('d', p)){
                String name = whoiswin(p1.total_score, p2.total_score, loser, mode);
                end_game(name);
            }
        }
        placeBlock(p);
        drawBoard(p);
    }
    protected String whoiswin(int score1, int score2, String loser, String mode){
        String ans;
        if(loser == "Player1")
            ans = "Player 2";
        else ans = "Player 1";
        if(mode == "TimeAttack")
        {
            if(loser == "timeover")
            {
                if(score1 < score2)
                    ans = "Player 2";
                else if(score1 > score2) {
                    ans = "Player 1";
                }
                else {
                    ans = "Draw";
                }
            }
            else if(loser == "Player1")
                ans = "Player 2";
            else ans = "Player 1";
        }
        return ans;
    }

    protected void end_game(String name){
        timer.stop();
        timeattack.stop();
        new VsModeEndGame(this.getLocation().x, this.getLocation().y, name, mode);
        this.dispose();
    }

    protected void at(Player p){
        p.board=upblock(p.board, p.attack_board);
        drawBoard(p);
        p.attack_board = new int[ATTACK_HEIGHT][ATTACK_WIDTH];
        drawAttackBoard(p);
    }


    protected void ready_next(Player p) throws IOException {

        p.curr = p.next_block;
        p.next_block = getRandomBlock();
    }

    protected void eraseRow(Player p) {
        int x = p.x, y = p.y;
        Block curr = p.curr;
        int board[][] = p.board;

        int lowest = y + curr.height() -1;
        int cnt = 0;

//        for(int i = lowest; i>=y; i--){
        int[] erase_list = new int[4];
        for(int i=0; i<curr.height(); i++){
            boolean canErase = true;
            for(int j = 0; j < WIDTH; j++){
                if(board[y+i][j] == 0)
                {
                    canErase = false;
                    break;
                }
            }
            if(canErase) {
                erase_list[cnt] = i;
                cnt += 1;
                for(int j = 0; j<WIDTH; j++) {
                    p.board[y+i][j] = 0;
                }
            }
        }
        if(cnt > 1)
            make_attack(erase_list, cnt, p);

        cal_score(cnt,p);
        for(int i = lowest; i>=0; i--){
            down(i,p);
        }
    }
    private void make_attack(int[] erase_list, int cnt, Player p) {
        int y = p.y, x = p.x;
        int[][] attack = new int[cnt][ATTACK_WIDTH];
        for(int i=0; i<cnt; i++)
            for(int j=0; j<ATTACK_WIDTH; j++)
                attack[i][j] = 1;
        for(int i=0; i<cnt; i++)
        {
            for(int j=0; j<p.curr.width(); j++){
                if(p.curr.getShape(j, erase_list[i]) != 0)
                    attack[i][x+j] = 0;
            }
        }
        if(p.sirial == sirialp1)
            placeAttackBlock(p2, attack);
        else placeAttackBlock(p1, attack);
    }


    protected void down(int row, Player p) {
        boolean canDown = true;
        boolean haveBlock = false;
        int swapRow = row + 1;

        while(swapRow < HEIGHT) {
            for (int j = 0; j < WIDTH; j++) {
                if (p.board[swapRow][j] != 0) {
                    canDown = false;
                    break;
                }
                if(p.board[row][j] != 0)	haveBlock = true; // D없애도 되는 지 확인
            }
            if(canDown && haveBlock) swapRow++;
            else break;
        }

        swapRow--;
        for(int j = 0; j<WIDTH; j++){
            int temp = p.board[row][j];
            p.board[row][j] = p.board[swapRow][j];
            p.board[swapRow][j] = temp;
        }
    }

    protected void cal_score(int combo, Player p){
        if(combo > 0) {
            p.total_score = p.total_score + combo + combo - 1;
        }
        p.num_eraseline += combo;
    }

    private boolean isBlocked(char move, Player p){ //블럭이 갈 수 있는지 확인하는 함수('d' : 아래, 'r' : 오른쪽, 'l' : 왼쪽)
        int y = p.y, x = p.x;
        Block curr = p.curr;
        int[][] board = p.board;

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

    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                if(e.getKeyCode() == key_left1) {
                    moveLeft(p2);
                    drawBoard(p2);
                }
                if(e.getKeyCode() == key_right1) {
                    moveRight(p2);
                    drawBoard(p2);
                }
                if(e.getKeyCode() == key_rotate1) {
                    rotateblock(p2);
                    //System.out.println("width : " + curr.width() + " height : " + curr.height());
                    drawBoard(p2);
                }
                if(e.getKeyCode() == key_harddrop1) {
                    harddrop(p2);
                }
                if(e.getKeyCode() == key_pause1) {
                    pause();
                }
                if(e.getKeyCode() == key_down1) {
                    moveDown(p2);
                    drawBoard(p2);
                }
                if(e.getKeyCode() == key_left2) {
                    moveLeft(p1);
                    drawBoard(p1);
                }
                if(e.getKeyCode() == key_right2) {
                    moveRight(p1);
                    drawBoard(p1);
                }
                if(e.getKeyCode() == key_rotate2) {
                    rotateblock(p1);
                    //System.out.println("width : " + curr.width() + " height : " + curr.height());
                    drawBoard(p1);
                }
                if(e.getKeyCode() == key_harddrop2) {
                    harddrop(p1);
                }
                if(e.getKeyCode() == key_down2) {
                    moveDown(p1);
                    drawBoard(p1);
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



    class Player {
        // 변수
        public int[][] board;
        public int[][] next_board;
        public int[][] attack_board;
        public Color[][] color_board;
        public Block curr;
        public Block next_block;
        public int x = 3, y = 0;
        public int total_score = 0;
        public int num_eraseline = 0;

        // layout
        public BoardLayout player;
        public JTextPane pane;
        public JPanel main_panel;
        public JTextPane next_pane;
        public JTextPane score_pane;
        public JTextPane attack_pane;
        public JTextPane time_pane;

        public int sirial;
    }
}
