import java.sql.*;

public class SQLiteJDBC {
    //====================================
    private static String driver = "org.sqlite.JDBC";
    private static String url = "jdbc:sqlite:GymnasticsCompetition.db";
    private static Connection conn;
    //====================================

    public static void openConnection(){
        try {
            Class.forName(driver);
             conn = DriverManager.getConnection(url);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void closeConnection(){
        try {
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void adminSetTeamInfo(String teamName, String teamAccount, String teamPassword) {
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamName = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamName);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(find){
            try {
                executeSql = "UPDATE TeamEntry SET TeamAccount = ?, TeamPassword = ? WHERE TeamName = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, teamAccount);
                preparedStatement.setString(2, teamPassword);
                preparedStatement.setString(3, teamName);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            try {
                executeSql = "INSERT INTO TeamEntry (TeamName, TeamAccount, TeamPassword) VALUES (?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, teamName);
                preparedStatement.setString(2, teamAccount);
                preparedStatement.setString(3, teamPassword);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void adminSetEventInfo(String eventName, String maxPeopleNumberPerTeam, String maxOnCourtPeopleNumberPerGame, String teamScoreThresholdPeopleNumber, int athleteSex) {
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM EventInfo WHERE EventName = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(find){
            try {
                executeSql = "UPDATE EventInfo SET MaxPeopleNumberPerTeam = ?, MaxOnCourtPeopleNumberPerGame = ?, TeamScoreThresholdPeopleNumber = ?, AthleteSex = ? WHERE EventName = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, maxPeopleNumberPerTeam);
                preparedStatement.setString(2, maxOnCourtPeopleNumberPerGame);
                preparedStatement.setString(3, teamScoreThresholdPeopleNumber);
                preparedStatement.setInt(4, athleteSex);
                preparedStatement.setString(5, eventName);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            try {
                executeSql = "INSERT INTO EventInfo (EventName, MaxPeopleNumberPerTeam, MaxOnCourtPeopleNumberPerGame, TeamScoreThresholdPeopleNumber) VALUES (?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, maxPeopleNumberPerTeam);
                preparedStatement.setString(3, maxOnCourtPeopleNumberPerGame);
                preparedStatement.setString(4, teamScoreThresholdPeopleNumber);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static boolean authenticateTeam(String teamAccount, String teamPassword){
        boolean ok = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamAccount = ? AND TeamPassword = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamAccount);
            preparedStatement.setString(2, teamPassword);
            rs = preparedStatement.executeQuery();
            ok = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    public static void teamSignUpCompulsive(
            String teamAccount,
            String teamPassword,
            String leaderName,
            String leaderID,
            String leaderTel,
            String doctorName,
            String doctorID,
            String doctorTel,
            String coachName,
            String coachID,
            String coachTel,
            int coachSex){
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamAccount = ? AND TeamPassword = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamAccount);
            preparedStatement.setString(2, teamPassword);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(!find) {
        } else {
            try {
                executeSql = "UPDATE TeamEntry SET LeaderName = ?, LeaderID = ?, LeaderTel = ?, DoctorName = ?, DoctorID = ?, DoctorTel = ?, CoachName = ?, CoachID = ?, CoachTel = ?, CoachSex = ? WHERE TeamAccount = ? AND TeamPassword = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, leaderName);
                preparedStatement.setString(2, leaderID);
                preparedStatement.setString(3, leaderTel);
                preparedStatement.setString(4, doctorName);
                preparedStatement.setString(5, doctorID);
                preparedStatement.setString(6, doctorTel);
                preparedStatement.setString(7, coachName);
                preparedStatement.setString(8, coachID);
                preparedStatement.setString(9, coachTel);
                preparedStatement.setInt(10, coachSex);
                preparedStatement.setString(11, teamAccount);
                preparedStatement.setString(12, teamPassword);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void teamSignUpOptional(
            String teamAccount,
            String teamPassword,
            String refereeName,
            String refereeID,
            String refereeTel){
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        System.out.println("Checking if TeamAccount and TeamPassword are right");
        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamAccount = ? AND TeamPassword = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamAccount);
            preparedStatement.setString(2, teamPassword);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(!find) {
        } else {
            try {
                executeSql = "UPDATE TeamEntry SET RefereeName = ?, RefereeID = ?, RefereeTel = ? WHERE TeamAccount = ? AND TeamPassword = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, refereeName);
                preparedStatement.setString(2, refereeID);
                preparedStatement.setString(3, refereeTel);
                preparedStatement.setString(4, teamAccount);
                preparedStatement.setString(5, teamPassword);
                preparedStatement.executeQuery();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // teamName must be registered before.
    public static void athleteSignUpCompulsive(
            String athleteName,
            String athleteID,
            int athleteAge,
            int athleteSex,
            String ageGroup,
            String teamName){
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM TeamEntry WHERE TeamName = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, teamName);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(!find) {
        } else {
            try {
                executeSql = "INSERT INTO AthleteEntry (AthleteName, AthleteID, AthleteAge, AthleteSex, AgeGroup) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, athleteName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setInt(3, athleteAge);
                preparedStatement.setInt(4, athleteSex);
                preparedStatement.setString(5, ageGroup);
                preparedStatement.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void athleteSignUpEvent(String athleteID, String eventName) {
        String executeSql;
        PreparedStatement preparedStatement;

        try {
            executeSql = "INSERT INTO PreAthleteEvent (AthleteID, EventName) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, athleteID);
            preparedStatement.setString(2, eventName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet adminQueryTeamAccountInfo(){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT TeamName, TeamAccount, TeamPassword FROM TeamEntry";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet adminQueryEventInfo(){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT EventName, MaxPeopleNumberPerTeam, MaxOnCourtPeopleNumberPerGame, TeamScoreThresholdPeopleNumber, AthleteSex FROM EventInfo";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet queryAthleteEntry(){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT AthleteName, AthleteID, AthleteAge, AthleteSex, AgeGroup, TeamName, AthleteNumber FROM AthleteEntry";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet queryAthleteEvent(){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT AthleteName, AthleteID, EventName FROM AthleteEntry NATURAL JOIN PreAthleteEvent";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static String queryPreScore(String eventName, String athleteID){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;
        String rse = "1";

        try {
            executeSql = "SELECT Score FROM PreAthleteScore WHERE EventName = ? and AthleteID = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2,athleteID);
            rs = preparedStatement.executeQuery();
            rse=rs.getString(1);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rse;
    }

    public static String queryFinalScore(String eventName, String athleteID){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;
        String rse = "1";

        try {
            executeSql = "SELECT Score FROM FinalAthleteScore WHERE EventName = ? and AthleteID = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2,athleteID);
            rs = preparedStatement.executeQuery();
            rse = rs.getString(1);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rse;
    }

    public static String queryGroup(String eventName, String athleteID){
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;
        String rse = "1";

        try {
            executeSql = "SELECT EventGroup FROM FinalAthleteEvent WHERE EventName = ? and AthleteID = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2,athleteID);
            rs = preparedStatement.executeQuery();
            rse = rs.getString(1);
        } catch (Exception e){
            e.printStackTrace();
        }
        return rse;
    }

    public static void assignGroup(String eventName, String athleteID, String group) {
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM FinalAthleteEvent WHERE EventName = ? and AthleteID=?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, athleteID);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (find) {
            try {
                executeSql = "UPDATE FinalAthleteEvent SET EventName = ?, AthleteID = ?, EventGroup = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setString(3, group);
                // preparedStatement.setString(4, eventName);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                executeSql = "INSERT INTO FinalAthleteEvent (EventName, AthleteID,EventGroup) VALUES (?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setString(3, group);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet queryTeamRanking() {
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT TeamName, SUM(Score) FROM FinalAthleteScore NATURAL JOIN AthleteEntry group by TeamName order by sum(Score) DESC";
            preparedStatement = conn.prepareStatement(executeSql);
            //  preparedStatement.setString(1, eventName);
            rs = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void judgeFinalScore(String eventName, String athleteID, String score) {
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM FinalAthleteScore WHERE EventName = ? and AthleteID = ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, athleteID);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (find) {
            try {
                executeSql = "UPDATE FinalAthleteScore SET EventName = ?, AthleteID = ?, Score = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setString(3, score);
                // preparedStatement.setString(4, eventName);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                executeSql = "INSERT INTO FinalAthleteScore (EventName, AthleteID, Score) VALUES (?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setString(3, score);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void judgePreScore(String eventName, String athleteID, String score) {
        boolean find = false;
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT * FROM PreAthleteScore WHERE EventName = ? and AthleteID= ?";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, athleteID);
            rs = preparedStatement.executeQuery();
            find = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (find) {
            try {
                executeSql = "UPDATE PreAthleteScore SET EventName = ?, AthleteID= ?, Score = ?";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setString(3, score);
                // preparedStatement.setString(4, eventName);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                executeSql = "INSERT INTO PreAthleteScore(EventName, AthleteID,Score) VALUES (?, ?, ?)";
                preparedStatement = conn.prepareStatement(executeSql);
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, athleteID);
                preparedStatement.setString(3, score);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getAllTeamName() {
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT TeamName FROM TeamEntry";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getAllEventName() {
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            executeSql = "SELECT EventName From EventInfo";
            preparedStatement = conn.prepareStatement(executeSql);
            rs = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void dumpFromPreToFinal(String eventName, String ageGroup) {
        String executeSql;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            executeSql = "SELECT AthleteID, Score FROM PreAthleteScore AS P1 WHERE P1.EventName = ? AND P1.AthleteID IN (SELECT AthleteID FROM AthleteEntry WHERE AgeGroup = ?) AND (SELECT COUNT(*) FROM PreAthleteScore AS P2 WHERE P2.EventName = ? AND P2.Score > P1.Score AND P2.AthleteID IN (SELECT P1.AthleteID FROM AthleteEntry WHERE AgeGroup = ?)) < (SELECT MAX(MaxOnCourtPeopleNumberPerGame) FROM EventInfo WHERE EventInfo.EventName = ?)";
            preparedStatement = conn.prepareStatement(executeSql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, ageGroup);
            preparedStatement.setString(3, eventName);
            preparedStatement.setString(4, eventName);
            rs = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
