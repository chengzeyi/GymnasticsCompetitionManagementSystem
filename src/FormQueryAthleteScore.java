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
    private JRadioButton radioButtonPre;
    private JRadioButton radioButtonFianl;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;
    private String score;
    private boolean choosePre;
   // ResultSet Score;

    public FormQueryAthleteScore() {
        frame = new JFrame("Query Score");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelQueryScore);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ButtonGroup preOrFinal= new ButtonGroup();
        preOrFinal.add(radioButtonFianl);
        preOrFinal.add(radioButtonPre);

        radioButtonFianl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosePre = false;
            }
        });
        radioButtonPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosePre = true;
            }
        });


        buttonQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String AthleteID = textFieldAthleteID.getText();
                if(choosePre)
                    score = SQLiteJDBC.queryPreScore(eventName, AthleteID);
                else
                    score = SQLiteJDBC.queryFinalScore(eventName, AthleteID);
                JOptionPane.showMessageDialog(frame, "your Score is" + score);
                frame.dispose();
            }
        });
    }
    public static void main (String args[]){
        new FormQueryAthleteScore();
    }

}
