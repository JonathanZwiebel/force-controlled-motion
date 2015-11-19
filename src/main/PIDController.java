package main;

/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Outputs force based on PID control theory
 */
public class PIDController extends OneSpaceForce {
    private ForceDirectedBody body_;
    private float target_, kP_, kD_, kI_;
    private float last_error_, integral_;

    public PIDController(ForceDirectedBody body, float target, float kP, float kD, float kI) {
        body_ = body;
        target_ = target;
        kD_ = kD;
        kP_ = kP;
        kI_ = kI;
        last_error_ = target_;
        integral_ = 0;
    }

    public void step() {
        float error = target_ - body_.position_;
        float derivative = error - last_error_;
        integral_ += error;

        force_ = error * kP_ + derivative * kD_ + integral_ * kI_ / ForceDirectedBody.UPDATES_PER_SECOND;
        last_error_ = error;
    }
}
