package seoultech.se.tetris.component.setting;

import seoultech.se.tetris.component.Setting;
import seoultech.se.tetris.component.model.DataManager;
import seoultech.se.tetris.component.model.ScoreDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resetting extends JFrame {
    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton reset, resetTable, resetItemTable;

    public Resetting(int x, int y) {
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

        JPanel resetPanel = new JPanel();
        this.reset = new JButton("reset");

        this.reset.setPreferredSize(new Dimension(180, 60));
        this.reset.addActionListener(listner);
        this.reset.setFocusPainted(false);
        resetPanel.add(this.reset);

        JPanel resetTablePanel = new JPanel();
        resetTable = new JButton("reset Normal Mode");

        resetTable.setPreferredSize(new Dimension(180, 60));
        resetTable.addActionListener(listner);
        resetTable.setFocusPainted(false);
        resetTablePanel.add(resetTable);

        JPanel resetItemTablePanel = new JPanel();
        resetItemTable = new JButton("reset Item Mode Socre");
        resetItemTable.setPreferredSize(new Dimension(180, 60));
        resetItemTable.addActionListener(listner);
        resetItemTable.setFocusPainted(false);
        resetItemTablePanel.add(resetItemTable);

        menuPanel.add(resetPanel);
        menuPanel.add(resetTablePanel);
        menuPanel.add(resetItemTablePanel);

    }
    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) {
                go_back();
            }
            else if (reset.equals(e.getSource())) { // restartButton pressed
                DataManager.getInstance().resetting();
                reset.setEnabled(false);
            }
            else if (resetTable.equals(e.getSource())) { // restartButton pressed
                String key = ScoreDataManager.getInstance().getNormKey();
                ScoreDataManager.getInstance().resetting(key);
                resetTable.setEnabled(false);
            }
            else if(resetItemTable.equals(e.getSource())) {
                String key = ScoreDataManager.getInstance().getItemKey();
                ScoreDataManager.getInstance().resetting(key);
                resetItemTable.setEnabled(false);
            }
        }
    };

    private void go_back(){
        new Setting(getThis().getLocation().x, getThis().getLocation().y);
        getThis().dispose();
    }


    private JFrame getThis() {return this;}

}
