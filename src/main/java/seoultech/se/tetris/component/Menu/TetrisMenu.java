package seoultech.se.tetris.component.Menu;

import seoultech.se.tetris.component.*;
import seoultech.se.tetris.component.model.ScoreDataManager;
import seoultech.se.tetris.component.VSModeMenu;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class TetrisMenu extends JFrame {
    private ImageIcon menuList[] = new ImageIcon[6];
    private Container c;
    private int menuNum = 0;
    //private JPanel mainPanel;
    private JPanel gameNamePane;
    private JPanel menuPane;
    private JLabel menuButton;
    private KeyListener keylistner;
    private int frameWidth = 510;
    private int frameHeight = 635;
    private JLabel upLabel,downLabel;
    private ImageIcon background = new ImageIcon("img/tetrisMenu/Start_background.png");


    public TetrisMenu(int x, int y) {
        JPanel back = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(background.getImage(),0,0,null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        this.setSize(frameWidth, frameHeight);
        this.setLocation(x, y);
//        this.setLayout(new GridLayout(2,1,10,0));
        //mainPanel = new JPanel();
        //displayGameName();
        setButton();
        keylistner = new MenuKeyListener();
        this.addKeyListener(keylistner);
        this.setFocusable(true);
        menuButton.addKeyListener(keylistner);
        menuButton.setFocusable(true);

        menuPane.setBounds(100,280,300,300);
        this.add(menuPane);

        this.getContentPane().add(back);

        setTitle("StartScreen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

//    void displayGameName(){
//        gameNamePane = new JPanel();
//        JLabel gameLable = new JLabel("SeoulTech SE Tetris");
//        gameLable.setFont(gameLable.getFont().deriveFont(40.0f));
////        gameLable.setHorizontalAlignment(JLabel.CENTER);
////        gameLable.setVerticalAlignment(JLabel.CENTER);
//        gameNamePane.add(gameLable);
//    }

    private Image changeImgSize(String path){
        ImageIcon img = new ImageIcon(path);
        Image img1 = img.getImage();
        Image changeImg = img1.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        return changeImg;
    }

    void setButton() {
        ImageIcon upImg = new ImageIcon(changeImgSize("img/tetrisMenu/up.png"));
        ImageIcon downImg = new ImageIcon(changeImgSize("img/tetrisMenu/down.png"));
        ImageIcon menuImg = new ImageIcon("img/tetrisMenu/Start_activate.png");
        ImageIcon settingImg = new ImageIcon("img/tetrisMenu/Setting_ac.png");
        ImageIcon leaderImg = new ImageIcon("img/tetrisMenu/Leader_ac.png");
        ImageIcon endgameImg = new ImageIcon("img/tetrisMenu/End_ac.png");
        ImageIcon itemmodeImg = new ImageIcon("img/tetrisMenu/Item_Mode_ac.png");
        ImageIcon vsmodeImg = new ImageIcon("img/tetrisMenu/VsMode_ac.png");

        menuList[0] = menuImg;
        menuList[1] = leaderImg;
        menuList[2] = settingImg;
        menuList[3] = endgameImg;
        menuList[4] = itemmodeImg;
        menuList[5] = vsmodeImg;

        upLabel = new JLabel(upImg);
        downLabel = new JLabel(downImg);

        menuPane = new JPanel();
        menuPane.setLayout(new GridLayout(3,1));

        //menuButton = new JButton((menuList[menuNum]));
        menuButton = new JLabel(menuImg);
        menuButton.setOpaque(false);
        menuButton.setSize(new Dimension(180,60));
        menuButton.setBounds(160,300,menuButton.getWidth(),menuButton.getHeight());

        //menuButton.setFont(menuButton.getFont().deriveFont(30.0f));
        menuPane.add(upLabel);
        menuPane.add(menuButton);
        menuPane.add(downLabel);
        menuPane.setOpaque(false);
    }

    void enterPressed() throws IOException {
        switch (menuNum){

            case 0: //board class
                new Board(this.getLocation().x, this.getLocation().y, ScoreDataManager.getInstance().getNormKey());
                this.dispose();
                break;
            case 1: //score board class
                new ScoreBoard(this.getLocation().x, this.getLocation().y);
//                new EndGame(this.getLocation().x, this.getLocation().y, 70, ScoreDataManager.getInstance().getNormKey());
                this.dispose();
                break;
            case 2: //setting class
                new Setting(this.getLocation().x, this.getLocation().y);
                this.dispose();
                break;
            case 3://exit game
                System.exit(0);
                break;
            case 4: //item mode
                new Board(this.getLocation().x, this.getLocation().y, ScoreDataManager.getInstance().getItemKey());
                this.dispose();
                break;
            case 5: //item mode
                new VSModeMenu(this.getLocation().x, this.getLocation().y);
                this.dispose();
                break;
            default:
        }
    }

    private class MenuKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int button_size = menuList.length;

            switch(e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    //해당 class로 이동
                    try {
                        enterPressed();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    menuNum = (menuNum + 1)%button_size;
                    menuButton.setIcon(menuList[menuNum]);
                    break;

                case KeyEvent.VK_UP:
                    if(menuNum == 0) menuNum = button_size-1;
                    else   menuNum--;
                    menuButton.setIcon(menuList[menuNum]);

                    break;

                default:
                    JOptionPane errorPane = new JOptionPane();
                    errorPane.showMessageDialog(null, "up, down, enter만 누를수 있습니다.","KEY_ERROR", JOptionPane.WARNING_MESSAGE);


                    /*
                    경고창 class 호출

                    content : u can press up down enter

                    -> button(확인)
                     */

            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
