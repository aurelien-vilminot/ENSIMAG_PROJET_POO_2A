import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Create a class to manage the events.
 */
public class EventManager {
    private long currentDate = 0;
    private HashMap<Event, Long> eventList = new HashMap<>() {};

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

        for (Map.Entry<Event, Long> couple : couples) {
            if (couple.getValue() <= this.currentDate) {
                couple.getKey().execute();
                eventsToRemove.add(couple.getKey());
            }
        }

        for (Event eventToRemove: eventsToRemove) {
            eventList.remove(eventToRemove);
        }
    }

    public boolean isFinished() {
        return this.eventList.isEmpty();
    }

    public void restart() {
    }
}
