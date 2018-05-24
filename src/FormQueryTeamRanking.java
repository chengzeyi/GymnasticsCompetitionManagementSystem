
import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FormQueryTeamRanking {
    private JTable tableTeamRanking;
    private JScrollPane scrollPaneTeamRanking;
    private JPanel panelTeamRanking;
    private JFrame frame;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;

    public FormQueryTeamRanking() {
        frame = new JFrame("Team Ranking");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.panelTeamRanking);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        columnNames = new String[]{"Team Name", "Total Score"};
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnNames);
        try {
            ResultSet resultSet = SQLiteJDBC.queryTeamRanking();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                Object[] objects = new Object[columnCount];
                for (int i = 0; i < objects.length; i++) {
                    objects[i] = resultSet.getObject(i + 1);
                }
                defaultTableModel.addRow(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableTeamRanking.setModel(defaultTableModel);
    }

        public static void main (String[]args){
            SQLiteJDBC.openConnection();
            new FormQueryTeamRanking();
        }
    }
