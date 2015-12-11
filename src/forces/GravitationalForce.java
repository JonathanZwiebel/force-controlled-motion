package forces;

/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Force on an object due to gravity
 */
public class GravitationalForce extends OneSpaceForce {
    public static final float GRAVITY = 9.80665f;

    /**
     * Constructs a gravitational force
     * @param body body over which this force acts
     */
    public GravitationalForce(ForceDirectedBody body) {
       force_ = -body.mass_ * GRAVITY;
    }

    public void step() {

    }
}
