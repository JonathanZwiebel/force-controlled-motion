package controllers;

import forces.OneSpaceForce;

/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Exerts a force equal to a scaled combination of P and D error
 */
public class PDController extends OneSpaceForce implements FitnessScored {
    private final float STOPPED_POSITION_ERROR = 0.01f;
    private final float STOPPED_VELOCITY_ERROR = 0.0001f;
    private final float STOPPED_TIME = 1.0f;

    private float time_stopped_;
    private boolean completed_;

    private ForceDirectedBody body_;
    private float target_, kP_, kD_;
    private float last_error_;

    public PDController(ForceDirectedBody body, float target, float kP, float kD) {
        body_ = body;
        target_ = target;
        kD_ = kD;
        kP_ = kP;
        last_error_ = target_;
        time_stopped_ = 0.0f;
        completed_ = false;
    }

    public void step() {
        if(completed_) {
            assert time_stopped_ >= STOPPED_TIME;
            return;
        }

        float error = target_ - body_.position_;
        float derivative = (error - last_error_) * ForceDirectedBody.UPDATES_PER_SECOND;

        force_ = error * kP_ + derivative * kD_;
        last_error_ = error;

        if(stopped(error, derivative)) {
            time_stopped_ += 1 / ForceDirectedBody.UPDATES_PER_SECOND;
        }
        else {
            time_stopped_ = 0.0f;
        }

        if(time_stopped_ >= STOPPED_TIME) {
            completed_ = true;
        }
    }

    private boolean stopped(float error, float derivative) {
        return Math.abs(error) <= STOPPED_POSITION_ERROR && Math.abs(derivative) < STOPPED_VELOCITY_ERROR;
    }

    // TODO: Exceptions
    public float fitness() {
        if(!completed_) {
            System.out.println("Getting fitness of not done PD Controlled Body");
            return -1.0f;
        }
        else {
            return 1.0f;
        }
    }

    public boolean completed() {
        return completed_;
    }
}
