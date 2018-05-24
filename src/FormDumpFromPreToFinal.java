import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FormDumpFromPreToFinal {
    private JFrame frame;
    private JPanel panelDumpFromPreToFinal;
    private JButton buttonDump;
    private JComboBox comboBoxEventName;
    private JComboBox comboBoxAgeGroup;

    public FormDumpFromPreToFinal() {
        frame = new JFrame("Dump From Pre To Final");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelDumpFromPreToFinal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        try {
            ResultSet rsEventName = SQLiteJDBC.getAllEventName();
            while (rsEventName.next()) {
                comboBoxEventName.addItem(rsEventName.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboBoxAgeGroup.addItem("7-8");
        comboBoxAgeGroup.addItem("9-10");
        comboBoxAgeGroup.addItem("11-12");

        buttonDump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = comboBoxEventName.getSelectedItem().toString();
                String ageGroup = comboBoxAgeGroup.getSelectedItem().toString();
                SQLiteJDBC.dumpFromPreToFinal(eventName, ageGroup);
                JOptionPane.showMessageDialog(frame, "Finished Dumping");
            }
        });
    }

    public static void main(String [] args) {
        new FormDumpFromPreToFinal();
    }
}
