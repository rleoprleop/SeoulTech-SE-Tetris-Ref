package seoultech.se.tetris.component.setting;

import seoultech.se.tetris.component.Setting;
import seoultech.se.tetris.component.TetrisMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplaySetting extends JFrame {
    private Container container;
    private JPanel backButtonPanel, menuPanel;
    private JButton backButton;
    private JButton big, normal, small;

    public DisplaySetting(int x, int y) {
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

        JPanel bigPanel = new JPanel();
        big = new JButton("big");
        big.setPreferredSize(new Dimension(180, 60));
        big.addActionListener(listner);
        bigPanel.add(big);

        JPanel normalPanel = new JPanel();
        normal = new JButton("normal");
        normal.setPreferredSize(new Dimension(180, 60));
        normal.addActionListener(listner);
        normalPanel.add(normal);

        JPanel smallPanel  = new JPanel();
        small = new JButton("small");
        small.setPreferredSize(new Dimension(180, 60));
        small.addActionListener(listner);
        smallPanel.add(small);

        menuPanel.add(bigPanel);
        menuPanel.add(normalPanel);
        menuPanel.add(smallPanel);

    }
    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (backButton.equals(e.getSource())) {
                new Setting(getThis().getLocation().x, getThis().getLocation().y);
                getThis().dispose();
            }
            else if (big.equals(e.getSource())) { // restartButton pressed

            }
            else if (normal.equals(e.getSource())) { // restartButton pressed

            }
            else { // restartButton pressed

            }
        }
    };

    private JFrame getThis() {return this;}
}
