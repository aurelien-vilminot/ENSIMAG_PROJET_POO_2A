import gui.GUISimulator;
import gui.Simulable;

/**
 * Abstract class for the management of the display for all games.
 * Implements the interface Simulable which takes care of the display.
 */
public abstract class Simulator implements Simulable {
    protected GUISimulator gui;
    protected Backend backend;
    protected EventManager eventManager = new EventManager();

    /**
     * Constructor of a simulator.
     * @param gui An instance of GUISimulator.
     */
    public Simulator(GUISimulator gui) {
        this.gui = gui;
    }

    /**
     * Draw graphical elements.
     */
    public abstract void draw();

    public GUISimulator getGui() {
        return this.gui;
    }

    public Backend getBackend() {
        return this.backend;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Call eventManager to execute all next events.
     * @see EventManager
     */
    @Override
    public void next() {
        this.eventManager.next();
    }

    /**
     * Puts simulator in its initial state.
     */
    @Override
    public void restart() {
        this.eventManager.restart();
        this.backend.reInit();
        this.gui.reset();
        this.draw();
    }
}
