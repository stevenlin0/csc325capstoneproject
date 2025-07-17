import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // create DAO
            SessionDAO dao = new SessionDAO("focuspulse.db");

            // insert a test session
            Session s = new Session(25, 5, "2025-07-13T09:00", "2025-07-13T09:30");
            dao.insertSession(s);
            System.out.println("Inserted a session.");

            // retrieve  sessions
            List<Session> sessions = dao.getAllSessions();
            System.out.println("Retrieved " + sessions.size() + " sessions:");
            for (Session sess : sessions) {
                System.out.println(
                        sess.getId() + " | " +
                                sess.getFocusDuration() + " min focus | " +
                                sess.getBreakDuration() + " min break | " +
                                sess.getStartTime() + " to " + sess.getEndTime()
                );
            }

            dao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
