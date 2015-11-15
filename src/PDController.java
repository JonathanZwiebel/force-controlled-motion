/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Exerts a force equal to a scaled combination of P and D error
 */
public class PDController extends OneSpaceForce{
    private ForceDirectedBody body_;
    private float target_, kP_, kD_;
    private float last_error;

    public PDController(ForceDirectedBody body, float target, float kP, float kD) {
        body_ = body;
        target_ = target;
        kD_ = kD;
        kP_ = kP;
        last_error = target_;
    }

    public void step() {
        float error = target_ - body_.position_;
        float derivative = last_error - error;

        force_ = error * kP_ + derivative * kD_;
        last_error = error;
    }
}
