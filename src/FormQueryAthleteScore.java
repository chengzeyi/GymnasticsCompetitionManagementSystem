import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormQueryAthleteScore {
    private JFrame frame;
    private JButton buttonQuery;
    private JTextField textFieldEventName;
    private JTextField textFieldAthleteID;
    private JPanel panelQueryScore;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;
    private String score;
    // ResultSet Score;

    public FormQueryAthleteScore() {
        frame = new JFrame("Query Score");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelQueryScore);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String AthleteID = textFieldAthleteID.getText();

                // score = SQLiteJDBC.QueryScore(eventName, AthleteID);
                JOptionPane.showMessageDialog(frame, "your Score is" + score);
                frame.dispose();
            }
        });
    }

    public static void main (String args[]){
        SQLiteJDBC.openConnection();
        new FormQueryAthleteScore();
    }
}
