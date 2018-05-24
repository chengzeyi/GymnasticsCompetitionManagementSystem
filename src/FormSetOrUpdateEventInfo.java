import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormSetOrUpdateEventInfo {
    private JFrame frame;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldEventName;
    private JTextField textFieldMaxPeopleNumberPerTeam;
    private JTextField textFieldMaxOnCourtPeopleNumberPerGame;
    private JTextField textFieldTeamScoreThresholdPeopleNumber;
    private JPanel panelSetOrUpdateEventInfo;
    private JRadioButton radioButtonMale;
    private JRadioButton radioButtonFemale;


    public FormSetOrUpdateEventInfo() {
        frame = new JFrame("Set Or Update Event Info");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelSetOrUpdateEventInfo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String maxPeopleNumberPerTeam = textFieldMaxPeopleNumberPerTeam.getText();
                String maxOnCourtPeopleNumberPerGame = textFieldMaxOnCourtPeopleNumberPerGame.getText();
                String teamScoreThresholdPeopleNumber = textFieldTeamScoreThresholdPeopleNumber.getText();
                int athleteSex;
                if (radioButtonMale.isSelected()) {
                    athleteSex = 0;
                } else if (radioButtonFemale.isSelected()){
                    athleteSex = 1;
                } else {
                    athleteSex = -1;
                }
                if(eventName.isEmpty() || maxPeopleNumberPerTeam.isEmpty() || maxOnCourtPeopleNumberPerGame.isEmpty() || teamScoreThresholdPeopleNumber.isEmpty() || athleteSex == -1){
                    JOptionPane.showMessageDialog(frame, "Event Info Is Not Complete");
                } else {
                    SQLiteJDBC.adminSetEventInfo(eventName, Integer.valueOf(maxPeopleNumberPerTeam), Integer.valueOf(maxOnCourtPeopleNumberPerGame), Integer.valueOf(teamScoreThresholdPeopleNumber), athleteSex);
                    JOptionPane.showMessageDialog(frame,"Set Or Update Event Info: Procedure Finished");
                    frame.dispose();
                }
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        radioButtonMale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonFemale.setSelected(false);
            }
        });
        radioButtonFemale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonMale.setSelected(false);
            }
        });
    }

    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormAdminSetEventInfo();
    }
}
