public class CellEvent extends Event{
    private GridSimulator gridSimulator;

    /**
     * Constructor of an event.
     *
     * @param date
     */
    public CellEvent(long date, GridSimulator gridSimulator) {
        super(date);
        this.gridSimulator = gridSimulator;
    }

    @Override
    public void execute() {
        this.gridSimulator.getGui().reset();
        this.gridSimulator.getGrid().step();
        this.gridSimulator.draw();
        this.gridSimulator.getEventManager().addEvent(new CellEvent(this.getDate() + 1, this.gridSimulator));
    }
}
