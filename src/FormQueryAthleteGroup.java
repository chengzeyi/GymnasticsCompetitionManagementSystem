import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormQueryAthleteGroup {
    private JFrame frame;
    private JButton buttonQuery;
    private JTextField textFieldEventName;
    private JTextField textFieldAthleteID;
    private JPanel JpanelQueryGroup;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;
    private String group;

    public FormQueryAthleteGroup() {
        frame = new JFrame("Query Group");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.JpanelQueryGroup);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        buttonQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = textFieldEventName.getText();
                String athleteID = textFieldAthleteID.getText();

                // group = SQLiteJDBC.QueryGroup(eventName, athleteID);
                JOptionPane.showMessageDialog(frame, "Your Group Is" + group);
                frame.dispose();
            }
        });
    }

    public static void main (String args[]){
        SQLiteJDBC.openConnection();
        new FormQueryAthleteGroup();
    }
}