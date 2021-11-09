import java.util.ArrayList;

/**
 * Manage events by their dates.
 */
public class EventManager {
    private long currentDate = 0;
    private ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<Event> eventsInit = new ArrayList<>();
    private ArrayList<Event> eventsToAdd = new ArrayList<>();
    private ArrayList<Event> eventsToRemove = new ArrayList<>();
    private boolean isStarted = false;

    /**
     * Add Events to a list of events.
     * @param e The event to add.
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

    /**
     * Check if there is no more event to execute.
     * @return True if list of events is empty.
     */
    public boolean isFinished() {
        return (!this.eventList.isEmpty() || !this.eventsToAdd.isEmpty());
    }

    /**
     * Clear event list and reinitialise it with init events.
     */
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
