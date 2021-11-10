/**
 * A simple event that prints its date and a message when executed.
 */
public class MessageEvent extends Event {
    private String message;

    /**
     * Constructor of a MessageEvent
     * @param date The date when the event will be executed.
     * @param message Message that will be printed.
     */
    public MessageEvent(int date, String message) {
        super(date);
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(this.getDate() + this.message);
    }
}
