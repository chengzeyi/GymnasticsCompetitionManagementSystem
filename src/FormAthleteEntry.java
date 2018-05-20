import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FormAthleteEntry {
    private JFrame frame;
    private JPanel panelAthleteEntry;
    private JScrollPane scrollPaneAthleteEntry;
    private JTable tableAthleteEntry;
    private JTable tableAthleteEvent;
    private JScrollPane scrollPaneAthleteEvent;
    private JButton buttonRefresh;
    private JButton buttonNewAthlete;
    private DefaultTableModel defaultTableModelAthleteEntry;
    private DefaultTableModel defaultTableModelAthleteEvent;
    private String[] columnNamesAthleteEntry;
    private String[] columnNamesAthleteEvent;

    public FormAthleteEntry(){
        frame = new JFrame("Athlete Entry");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelAthleteEntry);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        columnNamesAthleteEntry = new String[]{"Athlete Name", "Athlete ID", "Athlete Age", "Athlete Sex", "Age Group", "Team Name", "Athlete Number"};
        columnNamesAthleteEvent = new String[]{"Athlete Name", "Athlete ID", "Event Name"};

        defaultTableModelAthleteEntry = new DefaultTableModel();
        defaultTableModelAthleteEvent = new DefaultTableModel();
        defaultTableModelAthleteEntry.setColumnIdentifiers(columnNamesAthleteEntry);
        defaultTableModelAthleteEvent.setColumnIdentifiers(columnNamesAthleteEvent);
        try {
            ResultSet resultSet = SQLiteJDBC.queryAthleteEntry();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
                Object[] objects = new Object[columnCount];
                for (int i = 0; i < objects.length; i++){
                    objects[i] = resultSet.getObject(i + 1);
                }
                defaultTableModelAthleteEntry.addRow(objects);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        tableAthleteEntry.setModel(defaultTableModelAthleteEntry);
        try {
            ResultSet resultSet = SQLiteJDBC.queryAthleteEvent();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
                Object[] objects = new Object[columnCount];
                for (int i = 0; i < objects.length; i++){
                    objects[i] = resultSet.getObject(i + 1);
                }
                defaultTableModelAthleteEvent.addRow(objects);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        tableAthleteEvent.setModel(defaultTableModelAthleteEvent);

        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModelAthleteEntry.setRowCount(0);
                defaultTableModelAthleteEvent.setRowCount(0);
                try {
                    ResultSet resultSet = SQLiteJDBC.queryAthleteEntry();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    while (resultSet.next()){
                        Object[] objects = new Object[columnCount];
                        for (int i = 0; i < objects.length; i++){
                            objects[i] = resultSet.getObject(i + 1);
                        }
                        defaultTableModelAthleteEntry.addRow(objects);
                    }
                } catch (Exception exception){
                    exception.printStackTrace();
                }
                try {
                    ResultSet resultSet = SQLiteJDBC.queryAthleteEvent();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    while (resultSet.next()){
                        Object[] objects = new Object[columnCount];
                        for (int i = 0; i < objects.length; i++){
                            objects[i] = resultSet.getObject(i + 1);
                        }
                        defaultTableModelAthleteEvent.addRow(objects);
                    }
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        buttonNewAthlete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormSetAthleteInfo();
            }
        });
    }

    public static void main(String[] args) {
        SQLiteJDBC.openConnection();
        new FormAthleteEntry();
    }
}
