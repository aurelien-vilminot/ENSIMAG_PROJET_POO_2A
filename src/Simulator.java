import gui.GUISimulator;
import gui.Simulable;

public abstract class Simulator implements Simulable {
    protected GUISimulator gui;
    protected Backend backend;
    protected EventManager eventManager = new EventManager();

    public Simulator(GUISimulator gui) {
        this.gui = gui;
    }

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

    @Override
    public void next() {
        this.eventManager.next();
    }

    @Override
    public void restart() {
        this.eventManager.restart();
        this.backend.reInit();
        this.gui.reset();
        this.draw();
    }
}
