package seoultech.se.tetris.component;

import javax.swing.*;
import java.awt.*;

public class VSmode extends JFrame {
    private BoardLayout player1;
    private BoardLayout player2;


    public VSmode(int x, int y) {
        this.setLocation(x, y);
        this.setLayout(new GridLayout(2,1,10,10));

        this.add(player1);
        this.add(player2);

        setTitle("VSMode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void setPlayer1(){
        player1 = new BoardLayout(this.getX(), this.getY());
        
        //player1.setBoardPane(); -> board 패널 추가
        //player2.setSidePane();  -> score 랑 다음 나오는 블럭 넣으면됨
    }
    private void setPlayer2(){
        // 동일
    }
}
