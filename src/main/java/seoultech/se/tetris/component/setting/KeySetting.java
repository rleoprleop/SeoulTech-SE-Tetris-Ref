package seoultech.se.tetris.component.setting;

import seoultech.se.tetris.component.Setting;
import seoultech.se.tetris.component.model.DataManager;
import seoultech.se.tetris.component.model.KeystrokeUtil;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeySetting extends JFrame {
    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton right, left, rotate, hardDrop, pause,down;
    private JLabel currLeft,currDown,currRight,currRotate,currHarddrop,currPause;
    private int leftCode, rightCode, downCode, rotateCode, hardDropCode, pauseCode;

    public KeySetting(int x, int y) {
        this.setSize(500, 600);
        this.setLocation(x, y);
        this.setLayout(new BorderLayout(25, 25));
        leftCode = DataManager.getInstance().getLeft();
        rightCode = DataManager.getInstance().getRight();
        downCode = DataManager.getInstance().getDown();
        rotateCode = DataManager.getInstance().getRotate();
        hardDropCode = DataManager.getInstance().getHarddrop();
        pauseCode = DataManager.getInstance().getPause();

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

    private void setMenuPanel(){
        menuPanel = new JPanel(new GridLayout(6,1,5,0));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        left = new JButton("Move Left");
        left.setPreferredSize(new Dimension(180, 60));
        left.addActionListener(listner);

        currLeft = new JLabel(KeyEvent.getKeyText(DataManager.getInstance().getLeft()));
        currLeft.setPreferredSize(new Dimension(180,60));
        currLeft.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(left);
        leftPanel.add(currLeft);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        right = new JButton("Move Right");
        right.setPreferredSize(new Dimension(180, 60));
        right.addActionListener(listner);
        currRight = new JLabel(KeyEvent.getKeyText(DataManager.getInstance().getRight()));
        currRight.setPreferredSize(new Dimension(180,60));
        currRight.setHorizontalAlignment(JLabel.CENTER);

        rightPanel.add(right);
        rightPanel.add(currRight);

        JPanel downPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        down = new JButton("Move Down");
        down.setPreferredSize(new Dimension(180, 60));
        down.addActionListener(listner);
        currDown = new JLabel(KeyEvent.getKeyText(DataManager.getInstance().getDown()));
        currDown.setPreferredSize(new Dimension(180,60));
        currDown.setHorizontalAlignment(JLabel.CENTER);
        downPanel.add(down);
        downPanel.add(currDown);

        JPanel rotatePanel  = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rotate = new JButton("Rotate");
        rotate.setPreferredSize(new Dimension(180, 60));
        rotate.addActionListener(listner);
        currRotate = new JLabel(KeyEvent.getKeyText(DataManager.getInstance().getRotate()));
        currRotate.setPreferredSize(new Dimension(180,60));
        currRotate.setHorizontalAlignment(JLabel.CENTER);
        rotatePanel.add(rotate);
        rotatePanel.add(currRotate);

        JPanel hardDropPanel  = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hardDrop = new JButton("HardDrop");
        hardDrop.setPreferredSize(new Dimension(180, 60));
        hardDrop.addActionListener(listner);
        currHarddrop = new JLabel(KeyEvent.getKeyText(DataManager.getInstance().getHarddrop()));
        currHarddrop.setPreferredSize(new Dimension(180,60));
        currHarddrop.setHorizontalAlignment(JLabel.CENTER);
        hardDropPanel.add(hardDrop);
        hardDropPanel.add(currHarddrop);

        JPanel pausePanel  = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pause = new JButton("Pause");
        pause.setPreferredSize(new Dimension(180, 60));
        pause.addActionListener(listner);
        currPause = new JLabel(KeyEvent.getKeyText(DataManager.getInstance().getPause()));
        currPause.setPreferredSize(new Dimension(180,60));
        currPause.setHorizontalAlignment(JLabel.CENTER);
        pausePanel.add(pause);
        pausePanel.add(currPause);

        menuPanel.add(leftPanel);
        menuPanel.add(rightPanel);
        menuPanel.add(downPanel);
        menuPanel.add(rotatePanel);
        menuPanel.add(hardDropPanel);
        menuPanel.add(pausePanel);
    }

    public class MyKeyListner implements KeyListener {
        private JLabel label;
        public MyKeyListner(JLabel label){
            this.label = label;
        }
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            label.setText(KeyEvent.getKeyText(e.getKeyCode()));

            if(label == currLeft) leftCode = e.getKeyCode();
            else if(label == currRight) rightCode = e.getKeyCode();
            else if(label == currDown) downCode = e.getKeyCode();
            else if(label == currHarddrop) hardDropCode = e.getKeyCode();
            else if(label == currPause) pauseCode = e.getKeyCode();
            else rotateCode = e.getKeyCode();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) { //terminateButton pressed
                go_back();
            }
            else if (left.equals(e.getSource())) { // left pressed
                MyKeyListner listner = new MyKeyListner(currLeft);
                left.addKeyListener(listner);

             }
            else if (right.equals(e.getSource())) { // right pressed
                MyKeyListner listner = new MyKeyListner(currRight);
                right.addKeyListener(listner);
            }
            else if (down.equals(e.getSource())) { // down pressed
                MyKeyListner listner = new MyKeyListner(currDown);
                down.addKeyListener(listner);

            }
            else if (rotate.equals(e.getSource())) { // rotate pressed
                MyKeyListner listner = new MyKeyListner(currRotate);
                rotate.addKeyListener(listner);
            }
            else if (hardDrop.equals(e.getSource())) { // hardDrop pressed
                MyKeyListner listner = new MyKeyListner(currHarddrop);
                hardDrop.addKeyListener(listner);
            }
            else if (pause.equals(e.getSource())) { // pause pressed
                MyKeyListner listner = new MyKeyListner(currPause);
                pause.addKeyListener(listner);
            }
            else { // restartButton pressed
            }
        }
    };

    private boolean errorCheck(){
        int []arr = {rotateCode, pauseCode, rightCode, leftCode, hardDropCode, downCode};
        for(int i = 0; i<arr.length; i++){
            for(int j = i +1; j<arr.length; j++){
                if(arr[i] == arr[j]) return false;
            }
        }
        return true;
    }


    private void go_back() {
        if(errorCheck()) {
            DataManager.getInstance().setKey(leftCode, rightCode, downCode, pauseCode, rotateCode, hardDropCode);
            new Setting(getThis().getLocation().x, getThis().getLocation().y);
            getThis().dispose();
        }
        else {
            JOptionPane errorPane = new JOptionPane();
            errorPane.setLocation(this.getLocation().x, this.getLocation().y);
            errorPane.showMessageDialog(null, "중복된 키가 존재합니다.","KEY_ERROR", JOptionPane.WARNING_MESSAGE);

        }
    }

    private JFrame getThis() {return this;}

}
