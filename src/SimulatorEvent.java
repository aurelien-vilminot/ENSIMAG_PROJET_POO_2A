/**
 * The main event that links the Simulator, the Backend and the EventManager
 * @see Event
 */
public class SimulatorEvent extends Event {
    private Simulator simulator;
    private int timeStep;
    private String typeToUpdate;

    /**
     *
     * @param date The date when the event will be executed.
     * @param simulator The Simulator used for the simulation.
     * @param timeStep The time step between the creation of the events.
     * @param typeToUpdate The type of elements that need to be updated.
     */
    public SimulatorEvent(long date, Simulator simulator, int timeStep, String typeToUpdate) {
        super(date);
        this.simulator = simulator;
        this.timeStep = timeStep;
        this.typeToUpdate = typeToUpdate;
    }

    @Override
    public void execute() {
        this.simulator.getGui().reset();
        this.simulator.getBackend().step(this.typeToUpdate);
        this.simulator.draw();
        this.simulator.getEventManager().addEvent(new SimulatorEvent(
                this.getDate() + this.timeStep, this.simulator, this.timeStep, this.typeToUpdate
        ));
    }
}