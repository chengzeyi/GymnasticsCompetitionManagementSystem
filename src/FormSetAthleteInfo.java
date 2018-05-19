import javax.swing.*;

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
    private JComboBox comboBox5;

    public FormSetAthleteInfo(){
        frame = new JFrame("Set Athlete Info");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelSetAthleteInfo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormSetAthleteInfo();
    }
}
