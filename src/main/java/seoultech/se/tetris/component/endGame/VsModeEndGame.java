package seoultech.se.tetris.component.endGame;

import seoultech.se.tetris.component.Menu.TetrisMenu;
import seoultech.se.tetris.component.VSmode;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class VsModeEndGame extends JFrame {
    private JPanel scorePane, menuPane, winPanel;
    private JButton restart, terminate;
    private String name, mode;

    public VsModeEndGame(int x, int y, String name, String mode) {
        this.name = name;
        this.mode = mode;

        this.setLocation(x,y);
        this.setSize(600,500);
        this.setLayout(new GridLayout(4,1,10,10));

        setWinPanel();
        setMenuPane();

        this.add(new JPanel());
        this.add(winPanel);

        this.add(new JPanel());
        this.add(menuPane);
        setTitle("게임 종료");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void setWinPanel(){
        winPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel winLb = new JLabel();

        if(name == "Draw"){
            winLb.setText(name + "!");
        }
        else {
            winLb.setText(name + " Win!");
        }
        winLb.setFont(winLb.getFont().deriveFont(40.0f));
        winPanel.add(winLb);
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
                new VSmode(getLocation().x, getLocation().y, mode);
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

