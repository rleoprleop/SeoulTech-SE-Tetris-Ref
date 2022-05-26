package seoultech.se.tetris.component.endGame;

import seoultech.se.tetris.component.Board;
import seoultech.se.tetris.component.Menu.TetrisMenu;
import seoultech.se.tetris.component.model.ScoreDataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EndGame extends JFrame {
    private JPanel scorePane, scoreBoardPane, textPane, menuPane;
    private JTextField writeName;
    private JButton restart, terminate, addButton;
    private final long score;
    private JScrollPane scrollPane;
    private JTable scoreTable;
    private boolean isAdd = false;
    private String mode;

    public EndGame(int x, int y, long score, String mode) {
        this.score = score;
        this.mode = mode;
        this.setLocation(x,y);
        this.setSize(600,500);
        this.setLayout(new BorderLayout());
        setScorePane();
        setScoreBoardPane();
        setMenuPane();

        this.add(scorePane, BorderLayout.NORTH);
        this.add(scoreBoardPane, BorderLayout.CENTER);
        this.add(menuPane, BorderLayout.SOUTH);
        setTitle("게임 종료");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    void setScorePane() {
        scorePane = new JPanel(new FlowLayout());
        JLabel scoreLable = new JLabel(score+"점");
        scoreLable.setFont(scoreLable.getFont().deriveFont(30.f));
        scorePane.add(scoreLable);
    }

    void setScoreBoardPane(){
        scoreBoardPane = new JPanel(new FlowLayout());

        scoreTable = ScoreDataManager.getInstance().getTable(mode);

        scrollPane = new JScrollPane(scoreTable);
        scrollPane.setPreferredSize(new Dimension(this.getWidth() - 10, this.getHeight() / 2));
        scoreBoardPane.add(scrollPane);

        if(!isAdd) {
            setTextPane();
        }
    }

    void setTextPane(){
        textPane = new JPanel(new FlowLayout());

        if(score > ScoreDataManager.getInstance().getLastScore(mode)){
            writeName = new JTextField(27);

            addButton = new JButton("등록");
            addButton.addActionListener(listner);

            textPane.add(writeName);
            textPane.add(addButton);
        }
        else {
            JLabel lable = new JLabel("아쉽게도 10위 안에 못들었네요");
            textPane.add(lable);
        }
        scoreBoardPane.add(textPane);
    }

    void setMenuPane() {
        menuPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        terminate = new JButton("메인화면");
        restart = new JButton("다시하기");

        terminate.setPreferredSize(new Dimension(85, 85));
        restart.setPreferredSize(new Dimension(85, 85));

        terminate.addActionListener(listner);
        restart.addActionListener(listner);

        menuPane.add(restart);
        menuPane.add(terminate);
    }

    ActionListener listner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("action performed");
            if(terminate.equals(e.getSource())){ //terminateButton pressed
                new TetrisMenu(getLocation().x, getLocation().y);
                disPose();
            }
            else if(restart.equals(e.getSource())){ // restartButton pressed
                try {
                    new Board(getLocation().x, getLocation().y, mode);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                disPose();
            }
            else if (addButton.equals(e.getSource())){ //addButton pressed
                System.out.println("add button pressed");

                String trimName = writeName.getText().trim();
                if(trimName.equals("")){
                    JOptionPane errorPane = new JOptionPane();
                    errorPane.showMessageDialog(null, "적어도 하나 입력해야 합니다..","KEY_ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    ScoreDataManager.getInstance().addScoreData(writeName.getText(), score, mode);
                    scoreBoardPane.removeAll();
                    isAdd = true;
                    setScoreBoardPane();
                    int idx = ScoreDataManager.getInstance().getRowsFromTable(writeName.getText(), score, mode);
                    // 이름 같고 스코어 같으면 에러 날수 있음..
                    scoreTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
                    getThis().add(scoreBoardPane,BorderLayout.CENTER);
                    scoreBoardPane.revalidate();
                    scoreBoardPane.repaint();
                }
            }
        }

    };

    public class MyTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                int idx = ScoreDataManager.getInstance().getRowsFromTable(writeName.getText(), score, mode);
                if(row==idx) {
                    Font myFont1 = new Font("Serif", Font.BOLD, 15);
                    cell.setFont(myFont1);
                }
            }
            return cell;
        }
    }

    private void disPose() {
        this.dispose();
    }
    private JFrame getThis(){
        return this;
    }

}

