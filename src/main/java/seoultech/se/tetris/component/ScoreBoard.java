package seoultech.se.tetris.component;
import seoultech.se.tetris.component.Menu.TetrisMenu;
import seoultech.se.tetris.component.model.ScoreDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScoreBoard  extends JFrame{
    private JButton backButton;
    private JPanel backButtonPane;
    public static JPanel tablePane;

    public  ScoreBoard(int x, int y){
       this.setLocation(x, y);
       this.setTitle("SeoulTech SE Tetris");
       this.setSize(500, 600);
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setBackButtonPane();
        setScoreTablePane();

       this.add(backButtonPane, BorderLayout.NORTH);
       this.add(tablePane,BorderLayout.CENTER);

       this.setVisible(true);
   }

   private void setBackButtonPane() {
       backButton =  new JButton("Back");
       backButton.addMouseListener(new MyMouseListener());

       backButtonPane = new JPanel();
       backButtonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
       backButtonPane.add(backButton);
   }

   private void setScoreTablePane () {
//       JTable scoreTable = new JTable(ScoreDataManager.getInstance().getTable());
       JLabel label = new JLabel("Normal Mode");
       JLabel label2 = new JLabel("Item Mode");
       JScrollPane scrollPane = new JScrollPane(ScoreDataManager.getInstance().getTable(ScoreDataManager.getInstance().getNormKey()));
       JScrollPane itemScrollPane = new JScrollPane(ScoreDataManager.getInstance().getTable(ScoreDataManager.getInstance().getItemKey()));
       scrollPane.setPreferredSize(new Dimension(this.getWidth() - 10, this.getHeight() / 3));
       itemScrollPane.setPreferredSize(new Dimension(this.getWidth() - 10, this.getHeight() / 3));


       tablePane = new JPanel();
       tablePane.setLayout(new FlowLayout());
       tablePane.add(label);
       tablePane.add(scrollPane);
       tablePane.add(label2);
       tablePane.add(itemScrollPane);
   }

   private void disPose() {
       this.dispose();
   }

   private JFrame getThis(){
        return this;
   }

   class MyMouseListener extends MouseAdapter {
	    public void mouseClicked(MouseEvent evt) {
	    	new TetrisMenu(getThis().getLocation().x,getThis().getLocation().y);
            disPose();
	    }
	}
}
