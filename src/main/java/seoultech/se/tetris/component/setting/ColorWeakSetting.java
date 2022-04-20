package seoultech.se.tetris.component.setting;

import seoultech.se.tetris.component.Setting;
import seoultech.se.tetris.component.model.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ColorWeakSetting extends JFrame {
    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton on, off;

    public ColorWeakSetting(int x, int y) {
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
        String stat = DataManager.getInstance().getColor_weak();

        JPanel onPanel = new JPanel();
        on = new JButton("on");
        if(on.getText().equals(stat)){
            on.setSelected(true);
        }
        on.setPreferredSize(new Dimension(180, 60));
        on.addActionListener(listner);
        on.setFocusPainted(false);
        onPanel.add(on);

        JPanel offPanel = new JPanel();
        off = new JButton("off");
        if (off.getText().equals(stat)){
            off.setSelected(true);
        }
        off.setPreferredSize(new Dimension(180, 60));
        off.addActionListener(listner);
        off.setFocusPainted(false);
        offPanel.add(off);


        menuPanel.add(onPanel);
        menuPanel.add(offPanel);

    }
    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) {
                go_back();
            }
            else if (on.equals(e.getSource())) { // restartButton pressed
                DataManager.getInstance().setColor_weak("on");
                go_back();
            }
            else if (off.equals(e.getSource())) { // restartButton pressed
                DataManager.getInstance().setColor_weak("off");
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
