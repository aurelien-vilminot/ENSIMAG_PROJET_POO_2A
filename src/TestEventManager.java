/**
 * Class to test the class EventManager.
 */
public class TestEventManager {
    public static void main(String[] args) throws InterruptedException {
        EventManager manager = new EventManager();

        for (int i = 2 ; i <= 10 ; i += 2) {
            manager.addEvent(new MessageEvent(i, " [PING]"));
        }

        for (int i = 3 ; i <= 9 ; i += 3) {
            manager.addEvent(new MessageEvent(i, " [PONG]"));
        }

        while (!manager.isFinished()) {
            manager.next();
            Thread.sleep(100);
        }
        manager.restart();
        manager.addEvent(new MessageEvent(0, " [I am restarting !]"));
        while (!manager.isFinished()) {
            manager.next();
            Thread.sleep(100);
        }
        manager.restart();
        manager.addEvent(new MessageEvent(0, " [I am restarting once again, sorry]"));
        while (!manager.isFinished()) {
            manager.next();
            Thread.sleep(100);
        }
    }
}
