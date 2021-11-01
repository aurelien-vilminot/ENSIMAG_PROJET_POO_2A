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

    /**
     * Add Events to a list of Events
     * @param e : an Event
     */
    public void addEvent(Event e) {
        eventList.put(e, e.getDate());
    }

    /**
     * Move to the next event of the list.
     */
    public void next() {
        this.currentDate++;
        Set<Map.Entry<Event, Long>> couples = this.eventList.entrySet();
        ArrayList<Event> eventsToRemove = new ArrayList<>();

        // Execute all events which date is lower or equal than currentDate
        for (Map.Entry<Event, Long> couple : couples) {
            if (couple.getValue() <= this.currentDate) {
                couple.getKey().execute();
                eventsToRemove.add(couple.getKey());
            }
        }

        // Remove these events from the eventList
        for (Event eventToRemove: eventsToRemove) {
            this.eventList.remove(eventToRemove);
        }
    }

    public boolean isFinished() {
        return this.eventList.isEmpty();
    }

    public void restart() {}
}
