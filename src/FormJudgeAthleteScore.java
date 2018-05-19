import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormJudgeAthleteScore {
    private JFrame frame;
    private JTextField textFieldAthleteID;
    private JTextField textFieldScore;
    private JTextField textFieldEventName;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelFormJudgeScore;

    public FormJudgeAthleteScore(){
        frame = new JFrame("Judge Score");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelFormJudgeScore);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String athleteID = textFieldAthleteID.getText();
                String score = textFieldScore.getText();
                // SQLiteJDBC.JudgeScore(eventName, athleteID, score);
                JOptionPane.showMessageDialog(frame,"Succeeded To Judge");
                frame.dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormJudgeAthleteScore();
    }
}
