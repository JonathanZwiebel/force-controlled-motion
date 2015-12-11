package forces;

/**
 * Constant force in 1D space
 */
public class ConstantForce extends OneSpaceForce {
    /**
     * Contucts a constant force
     * @param val force in N
     */
    public ConstantForce(float val) {
        force_ = val;
    }

    public void step() {

    }
}
