package seoultech.se.tetris.component.setting;

import seoultech.se.tetris.component.Setting;
import seoultech.se.tetris.component.setting.keySetting.P1KeySetting;
import seoultech.se.tetris.component.setting.keySetting.P2KeySetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeySetting extends JFrame{

    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton p1KeySetting, p2KeySetting;
    private ImageIcon background = new ImageIcon("img/Background.png");

    public KeySetting(int x, int y) {

        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.setSize(510, 635);
        this.setLocation(x, y);

        setbackButtonPanel();
        setMenuPanel();

        this.add(backButtonPanel, BorderLayout.NORTH);
        this.add(menuPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void setbackButtonPanel() {
        backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("back");
        backButtonPanel.setBounds(10,10,backButton.getWidth(),backButton.getHeight());
        backButtonPanel.setOpaque(false);

        backButton.addActionListener(listner);
        backButtonPanel.add(backButton);
    }

    private Image changeImgSize(String path){
        ImageIcon img = new ImageIcon(path);
        Image img1 = img.getImage();
        Image changeImg = img1.getScaledInstance(180,60, Image.SCALE_SMOOTH);
        return changeImg;
    }

    private void setMenuPanel() {
        menuPanel = new JPanel(new GridLayout(4,1,0,0));
        ImageIcon level_activate = new ImageIcon(changeImgSize("img/settingMenu/1p_ac.png"));
        ImageIcon level_disabled = new ImageIcon(changeImgSize("img/settingMenu/1p_de.png"));
        ImageIcon colorweak_activate = new ImageIcon(changeImgSize("img/settingMenu/2p_ac.png"));
        ImageIcon colorweak_disabled = new ImageIcon(changeImgSize("img/settingMenu/2p_de.png"));


        JPanel levelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1KeySetting = new JButton(level_disabled);
        p1KeySetting.setRolloverIcon(level_activate);
        p1KeySetting.setBorderPainted(false);
        p1KeySetting.setPreferredSize(new Dimension(180, 60));
        p1KeySetting.addActionListener(listner);
        p1KeySetting.setOpaque(false);
        levelPanel.add(p1KeySetting);

        JPanel colorWeakPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2KeySetting = new JButton(colorweak_disabled);
        p2KeySetting.setRolloverIcon(colorweak_activate);
        p2KeySetting.setBorderPainted(false);
        p2KeySetting.setPreferredSize(new Dimension(180, 60));
        p2KeySetting.addActionListener(listner);
        p2KeySetting.setOpaque(false);
        colorWeakPanel.add(p2KeySetting);


        menuPanel.add(levelPanel);
        menuPanel.add(colorWeakPanel);
    }

    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) { //terminateButton pressed
                new Setting(getThis().getLocation().x, getThis().getLocation().y);
                disPose();
            }
            else if (p1KeySetting.equals(e.getSource())) { // restartButton pressed
                new P1KeySetting(getThis().getLocation().x, getThis().getLocation().y);
                disPose();
            }
            else if (p2KeySetting.equals(e.getSource())) { // restartButton pressed
                new P2KeySetting(getThis().getLocation().x, getThis().getLocation().y);
                disPose();
            }
        }
    };

    private void disPose() {
        this.dispose();
    }
    private JFrame getThis() {return this;}



}