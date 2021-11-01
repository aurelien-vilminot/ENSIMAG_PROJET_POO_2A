public class BallsEvent extends Event {
    private BallsSimulator ballsSimulator;

    /**
     * Constructor of an event.
     *
     * @param date
     */
    public BallsEvent(long date, BallsSimulator ballsSimulator) {
        super(date);
        this.ballsSimulator = ballsSimulator;
    }

    @Override
    public void execute() {
        this.ballsSimulator.getGui().reset();
        this.ballsSimulator.getBalls().step(5, this.ballsSimulator.getWidth(), this.ballsSimulator.getHeight());
        this.ballsSimulator.draw();
        this.ballsSimulator.getEventManager().addEvent(new BallsEvent(this.getDate() + 1, this.ballsSimulator));
    }
}
