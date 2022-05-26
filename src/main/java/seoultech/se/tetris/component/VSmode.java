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
    public static final String win_BORDER_CHAR = "X";
    public static final String win_BLOCK_CHAR = "O";
    public static final String win_BLANK_CHAR = "     ";
    public static final String mac_BORDER_CHAR = "X";
    public static final String mac_BLOCK_CHAR = "O";
    public static final String mac_BLANK_CHAR = " ";
    public static final String BLOCK_CHAR_LIST = " OOLEDTOXXXXXXX";

    public static String os;
    public static final int animate_idx = 7;

    private String mode;
    private String item_mode = "ItemMode";
    private String normal_mode = "NormalMode";
    private String attack_mode = "TimeAttack";

    //ready_game vs_mode
    private KeyListener playerKeyListener;
    private SimpleAttributeSet styleSet, styleSet2;
    private Timer timer,timeattack;
    private int time = 0;
    private int time_limit = 100;

    //key setted
    private int display_width = 1000;
    private int display_height = 840;
    private int key_left1, key_left2;
    private int key_right1, key_right2;
    private int key_rotate1, key_rotate2;
    private int key_harddrop1, key_harddrop2;
    private int key_pause1;
    private int key_down1, key_down2;

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
                if(mode == attack_mode && time > time_limit)
                {
                    String name = whoiswin(p1.total_score, p2.total_score, "timeover", mode);
                    end_game(name);
                }
            }
        });


        p1.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    moveDown(p1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                drawBoard(p1);
                //System.out.println(timer.getDelay());
                if(p1.num_eraseline >= 5){
                    p1.num_eraseline -=5;
                    p1.sprint += 30;
                    p1.total_score += 100;
                }
                if(p1.sprint>p1.SPMAX){
                    p1.sprint=p1.SPMAX;
                }
                p1.timer.setDelay(p1.initInterval-p1.sprint);
            }
        });

        p2.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    moveDown(p2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                drawBoard(p2);
                //System.out.println(timer.getDelay());
                if(p2.num_eraseline >= 5){
                    p2.num_eraseline -=5;
                    p2.sprint += 30;
                    p2.total_score += 300;
                }
                if(p2.sprint>p2.SPMAX){
                    p2.sprint=p2.SPMAX;
                }
                p2.timer.setDelay(p2.initInterval-p2.sprint);
            }
        });

        p1.press_timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pressDown(p1);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                drawBoard(p1);
            }
        });

        p2.press_timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pressDown(p2);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                drawBoard(p2);
            }
        });

        p1.erase_timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraseLine(p1);
                drawBoard(p1);
            }
        });

        p2.erase_timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraseLine(p2);
                drawBoard(p2);
            }
        });

        p1.timer.start();
        p1.timerflag=1;
        p2.timer.start();
        p2.timerflag=1;
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
        int win_extra_border = 4;
        int mac_extra_border = 2;
        int extra_border;
        if(os.contains("win")) {
            extra_border = win_extra_border;
        }
        else {
            extra_border = mac_extra_border;
        }

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
                        doc.insertString(doc.getLength(), Character.toString(BLOCK_CHAR_LIST.charAt(board[i][j])), styleSet);
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
        if(mode == attack_mode)
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
                else if(curr.getClass().getName().contains("Press")){
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
            p1.timer.stop();
            p2.timer.stop();
            timeattack.stop();
            new PauseVsMode(this);
        }
        else{
            this.setVisible(true);
            ispaused = false;
            p1.timer.start();
            p2.timer.start();
            timeattack.start();
        }
    }

    private void eraseCurr(Player p) {
        int x = p.x;
        int y = p.y;
        Block curr = p.curr;
        for (int i = y; i < y + curr.height(); i++) {
            for (int j = x; j < x + curr.width(); j++)
                if(curr.getShape(j-x,i-y) != 0) // 요것도 히트 무작정 지우면 안됨
                    p.board[i][j] = 0;
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

    protected void pressDown(Player p) throws IOException {
        if(p.y + p.curr.height() < HEIGHT) {
            eraseCurr(p);
            p.y++;
//            System.out.println("y++"+p.y);
        }
        else{
            p.press_timer.stop();
            placeBlock(p);
            for(int i = p.y; i<p.y+p.curr.height(); i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if(p.board[i][j]==7){
                        p.board[i][j] = 0;
                    }
                }
            }
            drawBoard(p);
            p.timer.start();
            p.timerflag=1;
            ready_next(p);
            p.x = 3;
            p.y = 0;

        }
        placeBlock(p);
        drawBoard(p);
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
        else if(isBlocked('d',p)&&p.curr.getClass().getName().contains("Press")){
            placeBlock(p);
            p.timer.stop();
            p.timerflag=0;
            p.press_timer.start();
        }
        else if(checkEraseRow(p)){
            placeBlock(p);
            p.timer.stop();
            p.timerflag=0;
            p.erase_timer.start();
        }
        else {
            p.timerflag=1;
            placeBlock(p);
            eraseRow(p);
            at(p);
            ready_next(p);
            p.x = 3;
            p.y = 0;
            if(isBlocked('d', p)){
                p.timer.stop();
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
        p1.timer.stop();
        p2.timer.stop();
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
        if(mode == item_mode)
        {
            if(true || p.item_rotate > 4) {
                //item_rotate -= 5;
                p.next_block = getRandomBlock();
                Random rnd = new Random();
                if(rnd.nextInt(100) < 80)
                    p.next_block.make_item();
                else p.next_block = new Press();
            }
            else p.next_block = getRandomBlock();
        }
        else p.next_block = getRandomBlock();
    }

    protected void eraseLine(Player p){
        int lowest = p.y + p.curr.height() -1;
        if(p.erase_line_check<2){
            p.erase_line_check++;
            for(int i=lowest;i>=p.y;i--){
                for(int j=0;j<WIDTH;j++){
                    if(p.board[i][j]>6){
                        p.board[i][j]-=animate_idx;
                    }
                }
            }
        }
        else{
            p.erase_timer.stop();
            p.timer.start();
        }
        placeBlock(p);
        drawBoard(p);
    }

    protected void eraseRow(Player p) {
        int lowest = p.y + p.curr.height() -1;
        int cnt = 0;

        boolean double_score = false;

//        for(int i = lowest; i>=y; i--){
        int[] erase_list = new int[4];
        for(int i=0; i<p.curr.height(); i++){
            boolean canErase = true;
            for(int j = 0; j < WIDTH; j++){
                if(p.board[p.y+i][j] == 0)
                {
                    canErase = false;
                    break;
                }
            }
            if(mode == item_mode) {
                for(int j = 0; j < WIDTH; j++) {
                    if (BLOCK_CHAR_LIST.charAt(p.board[p.y+i][j]) == 'L') {
                        canErase = true;
                    }
                    if (BLOCK_CHAR_LIST.charAt(p.board[p.y+i][j]) == 'E') {
                        for (int ii = 0; ii < HEIGHT; ii++) {
                            for (int jj = 0; jj < WIDTH; jj++) {
                                p.board[ii][jj] = 0;
                            }
                        }
                        canErase = false;
                        break;
                    }
                    if (BLOCK_CHAR_LIST.charAt(p.board[p.y+i][j]) == 'D') {
                        double_score = true;
                    }
                }

            }
            if(canErase) {
                erase_list[cnt] = i;
                cnt += 1;
                for(int j = 0; j<WIDTH; j++) {
                    p.board[p.y+i][j] = 0;
                }
            }
        }
        if(cnt > 1)
            make_attack(erase_list, cnt, p);

        cal_score(cnt,double_score,p);
        for(int i = lowest; i>=0; i--){
            down(i,p);
        }
    }

    protected boolean checkEraseRow(Player p){
        if(p.erase_timer.isRunning()||p.erase_line_check!=0){
            p.erase_line_check=0;
            return false;
        }
        int lowest = p.y + p.curr.height() -1;
        boolean erase=false;
        for(int i = lowest; i>=p.y; i--){
            boolean canErase = true;
            for(int j = 0; j < WIDTH; j++){
                if(BLOCK_CHAR_LIST.charAt(p.board[i][j]) == 'L'){//line erase
                    canErase=true;
                    break;
                }
                if(p.board[i][j] == 0)
                {
                    canErase = false;
                }
            }
            if(canErase) {
                for(int j = 0; j<WIDTH; j++) {
                    if(p.board[i][j]!=0) {
                        p.board[i][j] += animate_idx;
                        erase = true;
                    }
                }
            }
        }
        return erase;
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

    protected void cal_score(int combo,boolean double_score, Player p){
        if(combo > 0) {
            p.total_score = p.total_score + (combo + combo - 1) * 30;
            if(double_score == true)
                p.total_score *= 2;
        }
        p.num_eraseline += combo;
        if(mode == item_mode)
            p.item_rotate += combo;
    }

    private boolean isBlocked(char move, Player p){ //블럭이 갈 수 있는지 확인하는 함수('d' : 아래, 'r' : 오른쪽, 'l' : 왼쪽)
        p.total_score += 1;
        int y = p.y, x = p.x;
        Block curr = p.curr;
        int[][] board = p.board;

        if(p.timerflag==0)
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

    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                if(p2.timerflag!=0) {
                    if (e.getKeyCode() == key_left1) {
                        moveLeft(p2);
                        drawBoard(p2);
                    }
                    if (e.getKeyCode() == key_right1) {
                        moveRight(p2);
                        drawBoard(p2);
                    }
                    if (e.getKeyCode() == key_rotate1) {
                        rotateblock(p2);
                        //System.out.println("width : " + curr.width() + " height : " + curr.height());
                        drawBoard(p2);
                    }
                    if (e.getKeyCode() == key_harddrop1) {
                        harddrop(p2);
                    }

                    if (e.getKeyCode() == key_down1) {
                        moveDown(p2);
                        drawBoard(p2);
                    }
                }
                if(p1.timerflag!=0) {
                    if (e.getKeyCode() == key_left2) {
                        moveLeft(p1);
                        drawBoard(p1);
                    }
                    if (e.getKeyCode() == key_right2) {
                        moveRight(p1);
                        drawBoard(p1);
                    }
                    if (e.getKeyCode() == key_rotate2) {
                        rotateblock(p1);
                        //System.out.println("width : " + curr.width() + " height : " + curr.height());
                        drawBoard(p1);
                    }
                    if (e.getKeyCode() == key_harddrop2) {
                        harddrop(p1);
                    }
                    if (e.getKeyCode() == key_down2) {
                        moveDown(p1);
                        drawBoard(p1);
                    }
                }
                if(e.getKeyCode() == key_pause1) {
                    pause();
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
        public int item_rotate = 0;
        public static final int initInterval = 1000;
        int sprint=0;
        public static final int SPMAX=900;

        public int timerflag;
        public int erase_line_check=0;

        // layout
        public BoardLayout player;
        public JTextPane pane;
        public JPanel main_panel;
        public JTextPane next_pane;
        public JTextPane score_pane;
        public JTextPane attack_pane;
        public JTextPane time_pane;

        public int sirial;

        private Timer timer;
        private Timer press_timer;
        private Timer erase_timer;
    }
}
