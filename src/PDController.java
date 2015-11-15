/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Exerts a force equal to a scaled combination of P and D error
 */
public class PDController extends OneSpaceForce{
    private ForceDirectedBody body_;
    private float target_, kP_, kD_;
    private float last_position_;

    public PDController(ForceDirectedBody body, float target, float kP, float kD) {
        body_ = body;
        target_ = target;
        kD_ = kD;
        kP_ = kP;
        last_position_ = body_.position_;
    }

    public void step() {
        float error = target_ - body_.position_;
        float derivative = body_.position_ - last_position_;

        force_ = error * kP_ + derivative * kD_;
    }
}
