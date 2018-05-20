import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormSetAthleteInfo {
    private JFrame frame;
    private JPanel panelSetAthleteInfo;
    private JTextField textFieldAthleteName;
    private JTextField textFieldAthleteID;
    private JRadioButton radioButtonMale;
    private JRadioButton radioButtonFemale;
    private JCheckBox checkBoxEvent1;
    private JCheckBox checkBoxEvent2;
    private JCheckBox checkBoxEvent3;
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
    }


    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormSetAthleteInfo();
    }
}
