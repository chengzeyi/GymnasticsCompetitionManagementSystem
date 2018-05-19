import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormAssignAthleteGroup {
    private JFrame frame;
    private JButton buttonCancel;
    private JButton buttonOK;
    private JTextField textFieldAthleteID;
    private JTextField textFieldEventName;
    private JTextField textFieldGroup;
    private JPanel panelAssignGroup;
   // private JPanel panelFormAssignGroup;

    public FormAssignAthleteGroup(){
        frame = new JFrame("Assign Group");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelAssignGroup);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String athleteID = textFieldAthleteID.getText();
                String group = textFieldGroup.getText();
                // SQLiteJDBC.assignGroup(eventName, athleteID,group);
                JOptionPane.showMessageDialog(frame,"SUCCEED");
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
        new FormAssignAthleteGroup();
    }
}
