public class SimulatorEvent extends Event {
    private Simulator simulator;
    private int timeStep;
    private String typeToUpdate;

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