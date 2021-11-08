public class SimulatorEvent extends Event {
    private Simulator simulator;
    private int step;

    /**
     * Constructor of an event.
     *
     * @param date
     */
    public SimulatorEvent(long date, Simulator simulator, int step) {
        super(date);
        this.simulator = simulator;
        this.step = step;
    }

    @Override
    public void execute() {
        this.simulator.getGui().reset();
        this.simulator.getBackend().step(this.simulator.getEventManager());
        this.simulator.getEventManager().addEvent(new SimulatorEvent(this.getDate() + this.step, this.simulator, this.step));
    }
}