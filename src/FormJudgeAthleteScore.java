import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class FormJudgeAthleteScore {
    private JFrame frame;
    private JTextField textFieldAthleteID;
    private JTextField textFieldScore1;
    private JTextField textFieldEventName;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelFormJudgeScore;
    private JTextField textFieldScore2;
    private JTextField textFieldScore3;
    private JTextField textFieldScore4;
    private JTextField textFieldScore5;
    private JTextField textFieldScoreD;
    private JTextField textFieldP;
    private JRadioButton radioButtonPre;
    private JRadioButton radioButtonFinal;
    private boolean choosePre;
    private int scoreInt;

    // String Score;
    public FormJudgeAthleteScore(){
        frame = new JFrame("Judge score");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelFormJudgeScore);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ButtonGroup preOrFinal= new ButtonGroup();
        preOrFinal.add(radioButtonFinal);
        preOrFinal.add(radioButtonPre);

        radioButtonFinal.addActionListener(new ActionListener() {
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

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String athleteID = textFieldAthleteID.getText();
                int score1 = Integer.parseInt(textFieldScore1.getText());
                int score2 = Integer.parseInt(textFieldScore2.getText());
                int score3 = Integer.parseInt(textFieldScore3.getText());
                int score4 = Integer.parseInt(textFieldScore4.getText());
                int score5 = Integer.parseInt(textFieldScore5.getText());
                int scored = Integer.parseInt(textFieldScoreD.getText());
                int scorep = Integer.parseInt(textFieldP.getText());
                int [] a = {score1, score2, score3, score4, score5};
                Arrays.sort(a);
                scoreInt = (a[1]+a[2]+a[3])/3+scored-scorep;
                String score = Integer.toString(scoreInt);
                if(choosePre)
                    SQLiteJDBC.judgePreScore(eventName, athleteID, score);
                else
                    SQLiteJDBC.judgeFinalScore(eventName, athleteID, score);
                JOptionPane.showMessageDialog(frame,"Succeed");
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
