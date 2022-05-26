package seoultech.se.tetris.component.setting.keySetting;
import seoultech.se.tetris.component.BoardLayout;
import seoultech.se.tetris.component.model.DataManager;
import seoultech.se.tetris.component.setting.KeySetting;
import seoultech.se.tetris.component.setting.keySetting.KeySettingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class P2KeySetting extends JFrame {
    private JPanel backButtonPanel, mainPanel;
    private KeySettingPanel p1MenuPanel, p2MenuPanel;
    private JButton backButton;
    private int p1KeyArr[] = new int[6];
    private int p2KeyArr[] = new int[5];
    private int keyArr[];
    private int leftCode, rightCode, downCode, rotateCode, hardDropCode, pauseCode;

    public P2KeySetting(int x, int y) {
        this.setSize(500, 600);
        this.setLocation(x, y);
        this.setLayout(new BorderLayout(25, 25));
        getKeyCode();




        setMainPanel();
        setbackButtonPanel();
        this.add(backButtonPanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }


    private void getKeyCode() {
        p1KeyArr[0] = DataManager.getInstance().getLeft();
        p1KeyArr[1] = DataManager.getInstance().getRight();
        p1KeyArr[2] = DataManager.getInstance().getDown();
        p1KeyArr[3] = DataManager.getInstance().getRotate();
        p1KeyArr[4] = DataManager.getInstance().getHarddrop();
        p1KeyArr[5] = DataManager.getInstance().getPause();

        p2KeyArr[0] = DataManager.getInstance().getLeft2();
        p2KeyArr[1] = DataManager.getInstance().getRight2();
        p2KeyArr[2] = DataManager.getInstance().getDown2();
        p2KeyArr[3] = DataManager.getInstance().getRotate2();
        p2KeyArr[4] = DataManager.getInstance().getHarddrop2();

        for(int i = 0; i<6; i++){
            System.out.println(KeyEvent.getKeyText(p1KeyArr[i]));
        }
        for(int i = 0; i<5; i++){
            System.out.println(KeyEvent.getKeyText(p2KeyArr[i]));
        }
    }
    private void setMainPanel(){
        mainPanel = new JPanel(new BorderLayout());
        JPanel jp = new JPanel(new GridLayout(1,2,10,10));

        JPanel p1LbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel plLb = new JLabel("Player1");
        plLb.setFont(plLb.getFont().deriveFont(15.0f));
        p1LbPanel.add(plLb);

        JPanel p2LbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel p2Lb = new JLabel("Player2");
        p2Lb.setFont(p2Lb.getFont().deriveFont(15.0f));
        p2LbPanel.add(p2Lb);

        jp.add(p1LbPanel);
        jp.add(p2LbPanel);

        JPanel menuPanel = new JPanel(new GridLayout(1,2,25,25));
        p1MenuPanel = new KeySettingPanel(p1KeyArr);
        p2MenuPanel = new KeySettingPanel(p2KeyArr);
        menuPanel.add(p1MenuPanel);
        menuPanel.add(p2MenuPanel);

        mainPanel.add(jp, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
    }

    private void setbackButtonPanel(){
        backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("back");
        backButton.addActionListener(listner);
        backButtonPanel.add(backButton);
    }

    private boolean errorCheck(){
        keyArr = new int[p1KeyArr.length + p2KeyArr.length];
        System.arraycopy(p1KeyArr, 0, keyArr,0, p1KeyArr.length);
        System.arraycopy(p2KeyArr, 0, keyArr, p1KeyArr.length, p2KeyArr.length);
        for(int i = 0; i<keyArr.length; i++){
            System.out.println(KeyEvent.getKeyText(keyArr[i]));
        }

        for(int i = 0; i<keyArr.length; i++){
            for(int j = i +1; j<keyArr.length; j++){
                if(keyArr[i] == keyArr[j]) {
                    System.out.println(KeyEvent.getKeyText(keyArr[i]));
                    System.out.println(KeyEvent.getKeyText(keyArr[j]));
                    System.out.println(i + j);

                    return false;
                }
            }
        }
        return true;
    }

    private void go_back() {
        if(errorCheck()) {
            DataManager.getInstance().setKey(keyArr);
            new KeySetting(getThis().getLocation().x, getThis().getLocation().y);
            getThis().dispose();
        }
        else {
            JOptionPane errorPane = new JOptionPane();
            errorPane.setLocation(this.getLocation().x, this.getLocation().y);
            errorPane.showMessageDialog(null, "중복된 키가 존재합니다.","KEY_ERROR", JOptionPane.WARNING_MESSAGE);

        }


    }

    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) {
                go_back();
            }
            else {

            }
        }
    };



    private JFrame getThis() {return this;}

}