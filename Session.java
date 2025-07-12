/**
 * class representing a Pomodoro session record.
 * This class is a simple container for all the session data fields.
 */
public class Session {
    // identifier for the session (assigned by the database)
    private int id;

    // duration of the focus period (in minutes)
    private int focusDuration;

    // break period (in minutes)
    private int breakDuration;

    // start timestamp (as String)
    private String startTime;

    // end timestamp (as String)
    private String endTime;

    /**
     * no-argument constructor.
     * Useful when creating an empty cab.Session to populate manually.
     */
    public Session() {}

    /**
     * constructor to initialize all fields except id
     *
     * @param focusDuration length of the focus period
     * @param breakDuration length of the break period
     * @param startTime start time as String
     * @param endTime end time as String
     */
    public Session(int focusDuration, int breakDuration, String startTime, String endTime) {
        this.focusDuration = focusDuration;
        this.breakDuration = breakDuration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getter and setter for the id field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // getter and setter for the focus duration
    public int getFocusDuration() {
        return focusDuration;
    }

    public void setFocusDuration(int focusDuration) {
        this.focusDuration = focusDuration;
    }

    // getter and setter for the break duration
    public int getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

    // getter and setter for the start time
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // getter and setter for the end time
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}