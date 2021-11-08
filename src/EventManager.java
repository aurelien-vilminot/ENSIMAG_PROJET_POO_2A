import java.util.ArrayList;

/**
 * Create a class to manage events.
 */
public class EventManager {
    private long currentDate = 0;
    private ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<Event> eventsInit = new ArrayList<>();
    private ArrayList<Event> eventsToAdd = new ArrayList<>();
    private ArrayList<Event> eventsToRemove = new ArrayList<>();
    private boolean isStarted = false;

    public long getCurrentDate() {
        return currentDate;
    }

    /**
     * Add Events to a list of Events
     * @param e : an Event
     */
    public void addEvent(Event e) {
        eventsToAdd.add(e);
    }

    /**
     * Move to the next event of the list.
     */
    public void next() {
        if (!isStarted) {
            this.eventsInit.addAll(this.eventsToAdd);
            isStarted = true;
        }
        // Add events that were added before loop
        this.eventList.addAll(eventsToAdd);

        eventsToAdd.clear();
        eventsToRemove.clear();

        // Execute all events which date is lower or equal than currentDate
        for (Event e : this.eventList) {
            if (e.getDate() <= this.currentDate) {
                e.execute();
                eventsToRemove.add(e);
            }
        }

        // Remove events executed from the eventList
        for (Event e: eventsToRemove) {
            this.eventList.remove(e);
        }

        this.currentDate++;
    }

    public boolean isFinished() {
        return (this.eventList.isEmpty() && this.eventsToAdd.isEmpty());
    }

    public void restart() {
        if (!isStarted) {
            return;
        }
        isStarted = false;
        this.currentDate = 0;
        this.eventList.clear();
        this.eventsToAdd.clear();
        this.eventsToRemove.clear();
        this.eventList.addAll(this.eventsInit);
    }
}
