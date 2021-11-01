/**
 * An abstract class to create the events.
 */
public abstract class Event {
    private long date;

    /**
     * Constructor of an event.
     * @param date
     */
    public Event(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    /**
     * Execute an event.
     */
    public abstract void execute();
}
