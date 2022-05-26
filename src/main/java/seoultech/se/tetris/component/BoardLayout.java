package seoultech.se.tetris.component;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class BoardLayout extends JPanel {
    private JPanel boardPane;
    private JPanel sidePane;

    public BoardLayout(int width,int height) {
        this.setLayout(new GridLayout(1,3, 10,10));
        this.setSize(width, height);
        sidePane = new JPanel(new GridLayout(4,1,10,15));
        boardPane = new JPanel();
        this.setVisible(true);
        this.setSize(100, 100);
        this.add(boardPane);
        this.add(sidePane);
    }

    public void setBoardPane(JPanel pane) {
        boardPane.add(pane);
    }

    public void setSidePane(JTextPane pane) {
        sidePane.add(pane);
    }
//    public int getBoardWidth() {
//        return this.boardPane.getWidth();
//    }
//    public int getBoardHeight() {
//        return this.boardPane.getHeight();
//
//    }
//    public int getSideWidth() {
//        return this.sidePane.getWidth();
//
//    }
//    public int getSideHeight() {
//        return this.sidePane.getHeight();
//    }
}
