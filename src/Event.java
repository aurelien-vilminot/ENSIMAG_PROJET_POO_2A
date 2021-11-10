/**
 * Abstract class to create events.
 */
public abstract class Event {
    private long date;

    /**
     * Constructor of an event.
     * @param date The date when the event will be executed.
     */
    public Event(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    /**
     * Abstract method which execute something for the event.
     */
    public abstract void execute();
}
