package main;

/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Simulates a PD controller with a max output force
 */
public class LimitedPDController extends PDController{
    float limit_;

    public LimitedPDController(ForceDirectedBody body, float target, float kP, float kD, float limit) {
        super(body, target, kP, kD);
        limit_ = limit;
    }

    public void step() {
        super.step();
        if(force_ > limit_) {
            force_ = limit_;
        }
        else if(force_ < -limit_) {
            force_ = -limit_;
        }
    }
}
