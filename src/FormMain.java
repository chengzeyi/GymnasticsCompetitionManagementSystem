import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

public class FormMain {
    private JPanel panelMain;
    private JButton buttonAdminSetTeamInfo;
    private JButton buttonAdminSetEventInfo;
    private JButton buttonTeamSignUp;
    private JButton buttonAthleteEntry;
    private JButton buttonAssignAthleteGroup;
    private JButton buttonQueryAthleteGroup;
    private JButton buttonJudgeAthleteScore;
    private JButton buttonQueryAthleteScore;
    private JButton buttonQueryTeamRanking;
    private JButton buttonDumpFromPreToFinal;
    private JButton buttonQueryAthleteRanking;
    private JButton buttonAutoNumber;
    private JFrame frame;

    public FormMain() {
        frame = new JFrame("Gymnastics Competition Management System: Main Form");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonAdminSetTeamInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAdminSetTeamInfo();
            }
        });
        buttonAdminSetEventInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAdminSetEventInfo();
            }
        });
        buttonTeamSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormTeamSignUp();
            }
        });
        buttonAthleteEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAthleteEntry();
            }
        });
        buttonAssignAthleteGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAssignAthleteGroup();
            }
        });
        buttonQueryAthleteGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormQueryAthleteGroup();
            }
        });
        buttonJudgeAthleteScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormJudgeAthleteScore();
            }
        });
        buttonQueryAthleteScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormQueryAthleteScore();
            }
        });
        buttonQueryTeamRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormQueryTeamRanking();
            }
        });
        buttonDumpFromPreToFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormDumpFromPreToFinal();
            }
        });
        buttonQueryAthleteRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormQueryAthleteRanking();
            }
        });
        buttonAutoNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLiteJDBC.numberAllAthlete();
                JOptionPane.showMessageDialog(frame, "Finished Numbering");
            }
        });
    }

    public static void main(String[] args){
        SQLiteJDBC.openConnection();
        new FormMain();
    }
}
