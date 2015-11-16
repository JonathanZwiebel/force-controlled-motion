/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Force output dictated by PID control theory with a force limit
 */
public class LimitedPIDController extends PIDController{
    float upper_limit_, lower_limit_;

    public LimitedPIDController(ForceDirectedBody body, float target, float kP, float kD, float kI, float lower_limit, float upper_limit) {
        super(body, target, kP, kD, kI);
        lower_limit_ = lower_limit;
        upper_limit_ = upper_limit;
    }

    public void step() {
        super.step();
        if(force_ > upper_limit_) {
            force_ = upper_limit_;
        }
        if(force_ < lower_limit_) {
            force_ = lower_limit_;
        }

    }
}
