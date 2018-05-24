
import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FormQueryAthleteRanking {
    private JButton buttonOk;
    private JTable tableAthleteRanking;
    private JScrollPane scrollPaneAthleteRanking;
    private JPanel panelAthleteRanking;
    private JComboBox comboBoxAgeGroup;
    private JComboBox comboBoxEventName;
    private JFrame frame;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;

    public FormQueryAthleteRanking() {
        frame = new JFrame("Athlete Ranking");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelAthleteRanking);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        columnNames = new String[]{"Athlete Name", "Athlete ID", "Athlete Score"};
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnNames);
        tableAthleteRanking.setModel(defaultTableModel);
        try {
            ResultSet resultSet = SQLiteJDBC.getAllEventName();
            while (resultSet.next()) {
                comboBoxEventName.addItem(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comboBoxAgeGroup.addItem("7-8");
        comboBoxAgeGroup.addItem("9-10");
        comboBoxAgeGroup.addItem("11-12");

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = comboBoxEventName.getSelectedItem().toString();
                String ageGroup = comboBoxAgeGroup.getSelectedItem().toString();
                try {
                    defaultTableModel.setRowCount(0);
                    ResultSet resultSet = SQLiteJDBC.queryAthleteRanking(eventName, ageGroup);
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    while (resultSet.next()) {
                        Object[] objects = new Object[columnCount];
                        for (int i = 0; i < objects.length; i++) {
                            objects[i] = resultSet.getObject(i + 1);
                        }
                        defaultTableModel.addRow(objects);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

        public static void main (String[]args){
            SQLiteJDBC.openConnection();
            new FormQueryAthleteRanking();
        }
    }
