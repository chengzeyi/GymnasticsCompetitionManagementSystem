
import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import javax.swing.table.TableModel;
        import javax.xml.crypto.Data;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.nio.channels.NonWritableChannelException;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.util.Vector;
public class FormQueryTeamRanking {
    private JButton OKButton;
    private JTable tableTeamRanking;
    private JScrollPane JScrollPaneTeamRanking;
    private JPanel JPanelTeamRanking;
    private JFrame frame;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames;

    public FormQueryTeamRanking() {
        frame = new JFrame("Team Ranking");
        frame.setLocationByPlatform(true);
        frame.setContentPane(this.JScrollPaneTeamRanking);
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

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

        public static void main (String[]args){
            SQLiteJDBC.openConnection();
            new FormAdminSetTeamInfo();
        }
    }
