/**
 * Used for all backend parts to manage and calculate some elements.
 */
public interface Backend {
    /**
     *  Reinitialize the program.
     */
    void reInit();

    /**
     * Process to the change applied on the elements of the backend with the button next of the simulation.
     * @param type The type of elements which need to be updated. Set as null if all elements must be updated.
     */
    void step(String type);
}
