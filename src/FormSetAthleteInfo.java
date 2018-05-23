import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FormSetAthleteInfo {
    private JFrame frame;
    private JPanel panelSetAthleteInfo;
    private JTextField textFieldAthleteName;
    private JTextField textFieldAthleteID;
    private JRadioButton radioButtonMale;
    private JRadioButton radioButtonFemale;
    private JComboBox comboBoxEventName1;
    private JComboBox comboBoxEventName2;
    private JComboBox comboBoxEventName3;
    private JComboBox comboBoxAthleteAge;
    private JComboBox comboBoxTeamName;
    private JButton buttonSubmit;

    public FormSetAthleteInfo(){
        frame = new JFrame("Set Athlete Info");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelSetAthleteInfo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try {
            ResultSet rsTeamNames = SQLiteJDBC.getAllTeamName();
            while (rsTeamNames.next()) {
                Object object = rsTeamNames.getObject(1);
                comboBoxTeamName.addItem(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rsEventNames = SQLiteJDBC.getAllEventName();
            while (rsEventNames.next()) {
                Object object = rsEventNames.getObject(1);
                comboBoxEventName1.addItem(object);
                comboBoxEventName2.addItem(object);
                comboBoxEventName3.addItem(object);
            }
            comboBoxEventName1.addItem("Null");
            comboBoxEventName2.addItem("Null");
            comboBoxEventName3.addItem("Null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 7; i != 13; i++) {
            comboBoxAthleteAge.addItem(Integer.toString(i));
        }
        radioButtonMale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonMale.setSelected(true);
                radioButtonFemale.setSelected(false);
            }
        });
        radioButtonFemale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonFemale.setSelected(true);
                radioButtonMale.setSelected(false);
            }
        });
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String athleteName = textFieldAthleteName.getText();
                String athleteID = textFieldAthleteID.getText();
                int athleteAge = Integer.parseInt(comboBoxAthleteAge.getSelectedItem().toString());
                String ageGroup;
                String teamName = comboBoxTeamName.getSelectedItem().toString();
                String eventName1 = comboBoxEventName1.getSelectedItem().toString();
                String eventName2 = comboBoxEventName2.getSelectedItem().toString();
                String eventName3 = comboBoxEventName3.getSelectedItem().toString();
                if (athleteAge == 7 || athleteAge == 8) {
                    ageGroup = "7-8";
                } else if (athleteAge == 9 || athleteAge == 10) {
                    ageGroup = "9-10";
                } else {
                    ageGroup = "11-12";
                }
                int athleteSex;
                if (radioButtonMale.isSelected()) {
                    athleteSex = 0;
                } else if (radioButtonFemale.isSelected()) {
                    athleteSex = 1;
                } else {
                    athleteSex = -1;
                }
                if (athleteSex == -1) {
                    JOptionPane.showMessageDialog(frame, "Athlete Info Is Not Complete");
                } else {
                    SQLiteJDBC.athleteSignUpCompulsive(athleteName, athleteID, athleteAge, athleteSex, ageGroup, teamName);
                    if (eventName1 != "Null") {
                        SQLiteJDBC.athleteSignUpEvent(athleteID, eventName1);
                    }
                    if (eventName2 != "Null") {
                        SQLiteJDBC.athleteSignUpEvent(athleteID, eventName2);
                    }
                    if (eventName3 != "Null") {
                        SQLiteJDBC.athleteSignUpEvent(athleteID, eventName3);
                    }
                    JOptionPane.showMessageDialog(frame, "Finished Setting Athlete Info");
                }
            }
        });
    }

    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormSetAthleteInfo();
    }
}
