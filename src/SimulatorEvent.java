public class SimulatorEvent extends Event {
    private Simulator simulator;

    /**
     * Constructor of an event.
     *
     * @param date
     */
    public SimulatorEvent(long date, Simulator simulator) {
        super(date);
        this.simulator = simulator;
    }

    @Override
    public void execute() {
        this.simulator.getGui().reset();
        this.simulator.getBackend().step(this.simulator.getEventManager());
        this.simulator.draw();
        this.simulator.getEventManager().addEvent(new SimulatorEvent(this.getDate() + 1, this.simulator));
    }
}