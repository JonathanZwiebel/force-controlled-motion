package main;

/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Force with magnitude equal to distance from starting location
 */
public class ElasticForce extends OneSpaceForce {
    private ForceDirectedBody body_;
    private float elastic_coefficient_, initial_position_;

    /**
     * Constructs an elastic force
     * @param body force over which this force acts
     * @param elastic_coefficient elastic coefficient N / m
     * @param initial_position initial position m
     */
    public ElasticForce(ForceDirectedBody body, float elastic_coefficient, float initial_position) {
        body_ = body;
        elastic_coefficient_ = elastic_coefficient;
        initial_position_ = initial_position;
    }

    public void step() {
        force_ = -initial_position_ - body_.position_ * elastic_coefficient_;
    }
}
