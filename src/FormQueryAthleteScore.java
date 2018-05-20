import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormQueryAthleteScore {
    private JFrame frame;
    private JButton buttonQuery;
    private JTextField textFieldEventName;
    private JTextField textFieldAthleteID;
    private JPanel JpanelQueryScore;
    private JRadioButton PRERadioButton;
    private JRadioButton FINALRadioButton;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;
String   Score;
Boolean ChoosePre;
   // ResultSet Score;

    public FormQueryAthleteScore() {
        frame = new JFrame("Query Score");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.JpanelQueryScore);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ButtonGroup PreOrFinal= new ButtonGroup();
        PreOrFinal.add(FINALRadioButton);
        PreOrFinal.add(PRERadioButton);

        FINALRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChoosePre = false;
            }
        });
        PRERadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChoosePre = true;
            }
        });


        buttonQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String AthleteID = textFieldAthleteID.getText();
                if(ChoosePre)
                Score = SQLiteJDBC.queryPreScore(eventName, AthleteID);
                else
                    Score = SQLiteJDBC.queryFinalScore(eventName, AthleteID);
                JOptionPane.showMessageDialog(frame, "your Score is" + Score);
                frame.dispose();
            }
        });
    }
    public static void main (String args[]){
        new FormQueryAthleteScore();
    }

}
