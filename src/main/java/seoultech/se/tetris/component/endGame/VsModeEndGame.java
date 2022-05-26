package seoultech.se.tetris.component.endGame;

import seoultech.se.tetris.component.Board;
import seoultech.se.tetris.component.TetrisMenu;
import seoultech.se.tetris.component.VSmode;
import seoultech.se.tetris.component.model.ScoreDataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VsModeEndGame extends JFrame {
    private JPanel scorePane, menuPane, winPanel;
    private JButton restart, terminate;
    private final int score1, score2;
    private String mode;

    public VsModeEndGame(int x, int y, int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;

        this.setLocation(x,y);
        this.setSize(600,500);
        this.setLayout(new BorderLayout());

        setWinPanel();
        setScorePane();
        setMenuPane();

        JPanel jp = new JPanel(new GridLayout(3,1,10,10));
        jp.add(new JPanel());
        jp.add(scorePane);

        this.add(winPanel, BorderLayout.NORTH);
        this.add(jp, BorderLayout.CENTER);
        this.add(menuPane, BorderLayout.SOUTH);

        setTitle("게임 종료");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void setWinPanel(){
        winPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel winLb = new JLabel();
        if(score1 > score2){
            winLb.setText("Player1 Win!");
        }
        else if(score1 < score2){
            winLb.setText("Player2 Win!");
        }
        else{
            winLb.setText("Draw!");
        }
        winLb.setFont(winLb.getFont().deriveFont(30.0f));
        winPanel.add(winLb);
    }

    void setScorePane() {
        scorePane = new JPanel(new GridLayout(1,2,10,10));

        JPanel player1 = new JPanel(new BorderLayout());
        JPanel p1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel p1Lb = new JLabel("Player1");
        p1Lb.setFont(p1Lb.getFont().deriveFont(23.0f));
        p1Panel.add(p1Lb);

        JPanel p1ScorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel p1ScoreLb = new JLabel(score1+"점");
        p1ScoreLb.setFont(p1ScoreLb.getFont().deriveFont(23.0f));
        p1ScorePanel.add(p1ScoreLb);

        player1.add(p1Panel, BorderLayout.NORTH);
        player1.add(p1ScorePanel, BorderLayout.CENTER);

        JPanel player2 = new JPanel(new BorderLayout());

        JPanel p2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel p2Lb = new JLabel("Player2");
        p2Lb.setFont(p2Lb.getFont().deriveFont(23.0f));
        p2Panel.add(p2Lb);

        JPanel p2ScorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel p2ScoreLb = new JLabel(score2+"점");
        p2ScoreLb.setFont(p2ScoreLb.getFont().deriveFont(23.0f));
        p2ScorePanel.add(p2ScoreLb);

        player2.add(p2Panel, BorderLayout.NORTH);
        player2.add(p2ScorePanel, BorderLayout.CENTER);

        scorePane.add(player1);
        scorePane.add(player2);
    }

    void setMenuPane() {
        menuPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        terminate = new JButton("메인화면");
        restart = new JButton("다시하기");

        terminate.setPreferredSize(new Dimension(85, 85));
        restart.setPreferredSize(new Dimension(85, 85));

        terminate.addActionListener(e -> {
            new TetrisMenu(getLocation().x, getLocation().y);
            disPose();
        });
        restart.addActionListener(e -> {
            try {
                new VSmode(getLocation().x, getLocation().y);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            disPose();
        });

        menuPane.add(restart);
        menuPane.add(terminate);
    }

    private void disPose() {
        this.dispose();
    }
}

