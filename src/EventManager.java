import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Create a class to manage events.
 */
public class EventManager {
    private long currentDate = 0;
    private HashMap<Event, Long> eventList = new HashMap<>(){};
    private ArrayList<Event> eventsInit = new ArrayList<>();
    private ArrayList<Event> eventsToAdd = new ArrayList<>();
    private ArrayList<Event> eventsToRemove = new ArrayList<>();
    private boolean isStarted = false;

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
        for (Event e : eventsToAdd) {
            this.eventList.put(e, e.getDate());
        }

        eventsToAdd.clear();
        eventsToRemove.clear();

        // Execute all events which date is lower or equal than currentDate
        for (Map.Entry<Event, Long> couple : this.eventList.entrySet()) {
            if (couple.getValue() <= this.currentDate) {
                couple.getKey().execute();
                eventsToRemove.add(couple.getKey());
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
        isStarted = false;
        this.currentDate = 0;
        this.eventList.clear();
        this.eventsToAdd.clear();
        this.eventsToRemove.clear();
        for (Event e : this.eventsInit) {
            eventList.put(e, e.getDate());
        }
    }
}
