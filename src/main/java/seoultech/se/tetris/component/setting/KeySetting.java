package seoultech.se.tetris.component.setting;

import seoultech.se.tetris.component.Setting;
import seoultech.se.tetris.component.model.DataManager;
import seoultech.se.tetris.component.model.KeystrokeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeySetting extends JFrame {
    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton right, left, rotate, hardDrop, pause,down;
    private JLabel currLeft,currDown,currRight,currRotate,currHarddrop,currPause;

    public KeySetting(int x, int y) {
        this.setSize(500, 600);
        this.setLocation(x, y);
        this.setLayout(new BorderLayout(25, 25));

        setbackButtonPanel();
        setMenuPanel();

        this.add(backButtonPanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.WEST);
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

    KeystrokeUtil keyutil = new KeystrokeUtil();
    private void setMenuPanel(){
        menuPanel = new JPanel(new GridLayout(6,1,5,0));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        left = new JButton("Move Left");
        left.setPreferredSize(new Dimension(180, 60));
        left.addActionListener(listner);
        currLeft = new JLabel(keyutil.getKeyText(DataManager.getInstance().getLeft()));
        currLeft.setPreferredSize(new Dimension(180,60));
        leftPanel.add(left);
        leftPanel.add(currLeft);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        right = new JButton("Move Right");
        right.setPreferredSize(new Dimension(180, 60));
        right.addActionListener(listner);
        currRight = new JLabel(keyutil.getKeyText(DataManager.getInstance().getRight()));
        currRight.setPreferredSize(new Dimension(180,60));
        rightPanel.add(right);
        rightPanel.add(currRight);

        JPanel downPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        down = new JButton("Move Down");
        down.setPreferredSize(new Dimension(180, 60));
        down.addActionListener(listner);
        currDown = new JLabel(keyutil.getKeyText(DataManager.getInstance().getDown()));
        currDown.setPreferredSize(new Dimension(180,60));
        downPanel.add(down);
        downPanel.add(currDown);

        JPanel rotatePanel  = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rotate = new JButton("Rotate");
        rotate.setPreferredSize(new Dimension(180, 60));
        rotate.addActionListener(listner);
        currRotate = new JLabel(keyutil.getKeyText(DataManager.getInstance().getRotate()));
        currRotate.setPreferredSize(new Dimension(180,60));
        rotatePanel.add(rotate);
        rotatePanel.add(currRotate);

        JPanel hardDropPanel  = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hardDrop = new JButton("HardDrop");
        hardDrop.setPreferredSize(new Dimension(180, 60));
        hardDrop.addActionListener(listner);
        currHarddrop = new JLabel(keyutil.getKeyText(DataManager.getInstance().getHarddrop()));
        currHarddrop.setPreferredSize(new Dimension(180,60));
        hardDropPanel.add(hardDrop);
        hardDropPanel.add(currHarddrop);

        JPanel pausePanel  = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pause = new JButton("Pause");
        pause.setPreferredSize(new Dimension(180, 60));
        pause.addActionListener(listner);
        currPause = new JLabel(keyutil.getKeyText(DataManager.getInstance().getPause()));
        currPause.setPreferredSize(new Dimension(180,60));
        pausePanel.add(pause);
        pausePanel.add(currPause);

        menuPanel.add(leftPanel);
        menuPanel.add(rightPanel);
        menuPanel.add(downPanel);
        menuPanel.add(rotatePanel);
        menuPanel.add(hardDropPanel);
        menuPanel.add(pausePanel);
    }

    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) { //terminateButton pressed
                go_back();
            }
            else if (left.equals(e.getSource())) { // restartButton pressed
                currLeft.setText(keyutil.getKeyText(DataManager.getInstance().getLeft()));
            }
            else if (right.equals(e.getSource())) { // restartButton pressed
                currRight.setText(keyutil.getKeyText(DataManager.getInstance().getRight()));
            }
            else if (down.equals(e.getSource())) { // restartButton pressed
                currDown.setText(keyutil.getKeyText(DataManager.getInstance().getDown()));
            }
            else if (rotate.equals(e.getSource())) { // restartButton pressed
                currRotate.setText(keyutil.getKeyText(DataManager.getInstance().getRotate()));
            }
            else if (hardDrop.equals(e.getSource())) { // restartButton pressed
                currHarddrop.setText(keyutil.getKeyText(DataManager.getInstance().getHarddrop()));
            }
            else if (pause.equals(e.getSource())) { // restartButton pressed
                currPause.setText(keyutil.getKeyText(DataManager.getInstance().getPause()));
            }
            else { // restartButton pressed
            }
        }
    };

    private void go_back() {
        new Setting(getThis().getLocation().x, getThis().getLocation().y);
        getThis().dispose();
    }

    private JFrame getThis() {return this;}

}
