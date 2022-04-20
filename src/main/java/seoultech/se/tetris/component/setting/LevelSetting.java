package seoultech.se.tetris.component.setting;

import seoultech.se.tetris.component.Setting;
import seoultech.se.tetris.component.model.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LevelSetting extends JFrame {
    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton easy, normal, hard;


    public LevelSetting(int x, int y) {
        this.setSize(500, 600);
        this.setLocation(x, y);
        this.setLayout(new BorderLayout(25, 25));

        setbackButtonPanel();
        setMenuPanel();

        this.add(backButtonPanel, BorderLayout.NORTH);
        this.add(menuPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    private void setbackButtonPanel(){
        backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("back");
        backButton.addActionListener(listner);
        backButtonPanel.add(backButton);
    }

    private void setMenuPanel(){
        menuPanel = new JPanel(new GridLayout(5,1,5,0));
        String stat = DataManager.getInstance().getLevel();


        JPanel easyPanel = new JPanel();
        easy = new JButton("easy");
        if(easy.getText().equals(stat)) easy.setSelected(true);
        easy.setPreferredSize(new Dimension(180, 60));
        easy.setFocusPainted(false);
        easy.addActionListener(listner);
        easyPanel.add(easy);

        JPanel normalPanel = new JPanel();
        normal = new JButton("normal");
        if(normal.getText().equals(stat)) normal.setSelected(true);

        normal.setPreferredSize(new Dimension(180, 60));
        normal.addActionListener(listner);
        normal.setFocusPainted(false);
        normalPanel.add(normal);

        JPanel hardPanel  = new JPanel();
        hard = new JButton("hard");
        if(hard.getText().equals(stat)) hard.setSelected(true);

        hard.setPreferredSize(new Dimension(180, 60));
        hard.addActionListener(listner);
        hard.setFocusPainted(false);
        hardPanel.add(hard);



        menuPanel.add(easyPanel);
        menuPanel.add(normalPanel);
        menuPanel.add(hardPanel);
    }

    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) {
                go_back();
            }
            else if (easy.equals(e.getSource())) { // restartButton pressed
                DataManager.getInstance().setLevel("easy");
                go_back();
            }
            else if (normal.equals(e.getSource())) { // restartButton pressed

                DataManager.getInstance().setLevel("normal");
                go_back();
            }
            else { // restartButton pressed

                DataManager.getInstance().setLevel("hard");
                go_back();
            }
        }
    };

    private void go_back(){
        new Setting(getThis().getLocation().x, getThis().getLocation().y);
        getThis().dispose();
    }

    private JFrame getThis() {return this;}
}
