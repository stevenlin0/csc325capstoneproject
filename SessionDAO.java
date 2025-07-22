import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * data access object(DAO) for managing Sessions in the sqlite database
 */
public class SessionDAO {
    // holds the connection to the database
    private Connection conn;

    /**
     * opens a connection to the sqlite database file at the provided path
     *
     * @param dbPath path to the sqlite .db file
     * @throws SQLException if connection fails
     */
    public SessionDAO(String dbPath) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    public void createSessionsTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS sessions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "focusDuration INTEGER NOT NULL, " +
                "breakDuration INTEGER NOT NULL, " +
                "startTime TEXT NOT NULL, " +
                "endTime TEXT NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * inserts a new session record into the database.
     *
     * @param session the cab.Session object to save
     * @throws SQLException if the insert fails
     */
    public void insertSession(Session session) throws SQLException {
        //sql statement with placeholders for values
        String sql = "INSERT INTO sessions(focusDuration, breakDuration, startTime, endTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //set each placeholder (?) in order
            pstmt.setInt(1, session.getFocusDuration());
            pstmt.setInt(2, session.getBreakDuration());
            pstmt.setString(3, session.getStartTime());
            pstmt.setString(4, session.getEndTime());
            //insert
            pstmt.executeUpdate();
        }
    }

    /**
     * retrieves all session records from the database.
     *
     * @return a list of cab.Session objects
     * @throws SQLException if the query fails
     */
    public List<Session> getAllSessions() throws SQLException {
        List<Session> sessions = new ArrayList<>();
        //sql query to get all sessions
        String sql = "SELECT * FROM sessions";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            //loop through each row in the ResultSet
            while (rs.next()) {
                //create a new cab.Session object
                Session s = new Session();
                //fill cab.Session fields from columns
                s.setId(rs.getInt("id"));
                s.setFocusDuration(rs.getInt("focusDuration"));
                s.setBreakDuration(rs.getInt("breakDuration"));
                s.setStartTime(rs.getString("startTime"));
                s.setEndTime(rs.getString("endTime"));
                //add to the list
                sessions.add(s);
            }
        }
        return sessions;
    }

    /**
     * closes the database connection.
     *
     * @throws SQLException if closing fails
     */
    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}