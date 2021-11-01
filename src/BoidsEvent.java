public class BoidsEvent extends Event {
    private BoidsSimulator boidsSimulator;

    /**
     * Constructor of an event.
     *
     * @param date
     */
    public BoidsEvent(long date, BoidsSimulator boidsSimulator) {
        super(date);
        this.boidsSimulator = boidsSimulator;
    }

    @Override
    public void execute() {
        this.boidsSimulator.getGui().reset();
        this.boidsSimulator.getBoidsBackend().step();
        this.boidsSimulator.draw();
        this.boidsSimulator.getEventManager().addEvent(new BoidsEvent(this.getDate() + 1, this.boidsSimulator));
    }
}
