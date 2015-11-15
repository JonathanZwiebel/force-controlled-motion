/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * A negative force with magnitude proportional to the velocity of an object
 */
public class DampingForce extends OneSpaceForce{
    private float k_;
    private ForceDirectedBody body_;

    public DampingForce(ForceDirectedBody body, float damping_coefficient) {
        k_ = damping_coefficient;
        body_ = body;
        force_ = -body_.velocity_ * k_;
    }

    public void step() {
        force_ = - body_.velocity_ * k_;
    }
}