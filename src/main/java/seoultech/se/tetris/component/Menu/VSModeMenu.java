package seoultech.se.tetris.component;

import seoultech.se.tetris.component.Menu.TetrisMenu;
import seoultech.se.tetris.component.setting.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;

public class VSModeMenu extends JFrame {
    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton normalMode, itemMode, timeAttack;
    private ImageIcon background = new ImageIcon("img/Background.png");

    public VSModeMenu(int x, int y) {

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
        menuPanel = new JPanel(new GridLayout(3, 1, 5, 0));
        ImageIcon normal_activate = new ImageIcon(changeImgSize("img/VSMode/Normal_ac.png"));
        ImageIcon normal_disabled = new ImageIcon(changeImgSize("img/VSMode/Normal_de.png"));
        ImageIcon itemMode_activate = new ImageIcon(changeImgSize("img/VSMode/ItemMode_ac.png"));
        ImageIcon itemMode_disabled = new ImageIcon(changeImgSize("img/VSMode/ItemMode_de.png"));
        ImageIcon timeAttack_activate = new ImageIcon(changeImgSize("img/VSMode/TimeAttack_ac.png"));
        ImageIcon timeAttack_disabled = new ImageIcon(changeImgSize("img/VSMode/TimeAttack_de.png"));


        JPanel normalPanel = new JPanel();
        normalMode = new JButton(normal_disabled);
        normalMode.setRolloverIcon(normal_activate);
        normalMode.setBorderPainted(false);
        normalMode.setPreferredSize(new Dimension(180, 60));
        normalMode.addActionListener(listner);
        normalMode.setOpaque(false);
        normalPanel.add(normalMode);

        JPanel ItemPanel = new JPanel();
        itemMode = new JButton(itemMode_disabled);
        itemMode.setRolloverIcon(itemMode_activate);
        itemMode.setBorderPainted(false);
        itemMode.setPreferredSize(new Dimension(180, 60));
        itemMode.addActionListener(listner);
        itemMode.setOpaque(false);
        ItemPanel.add(itemMode);

        JPanel timeAttackPanel = new JPanel();
        timeAttack = new JButton(timeAttack_disabled);
        timeAttack.setRolloverIcon(timeAttack_activate);
        timeAttack.setBorderPainted(false);
        timeAttack.setPreferredSize(new Dimension(180, 60));
        timeAttack.addActionListener(listner);
        timeAttack.setOpaque(false);
        timeAttackPanel.add(timeAttack);

        menuPanel.add(normalPanel);
        menuPanel.add(ItemPanel);
        menuPanel.add(timeAttackPanel);
    }

    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) { //terminateButton pressed
                new TetrisMenu(getThis().getLocation().x, getThis().getLocation().y);
                disPose();
            }
            else if (normalMode.equals(e.getSource())) { // restartButton pressed
                try {
                    new VSmode(getThis().getLocation().x, getThis().getLocation().y, "NormalMode");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                disPose();
            }
            else if (itemMode.equals(e.getSource())) { // restartButton pressed
                try {
                    new VSmode(getThis().getLocation().x, getThis().getLocation().y, "ItemMode");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                disPose();
            }
            else if (timeAttack.equals(e.getSource())) { // restartButton pressed
                try {
                    new VSmode(getThis().getLocation().x, getThis().getLocation().y, "TimeAttack");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                disPose();
            }
        }
    };

    private void disPose() {
        this.dispose();
    }
    private JFrame getThis() {return this;}



}
