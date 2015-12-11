package controllers;

import forces.DampingForce;

public class PIDControlledBodies {

	public static PDController dampedPDControlledBody(float mass, float damping_coefficient, float kP, float kD, float start_pos, float target) {
        ForceDirectedBody body = new ForceDirectedBody(mass);
        body.position_ = start_pos;
        body.addForce(new PDController(body, target, kP, kD));
        body.addForce(new DampingForce(body, damping_coefficient));
        return (PDController) body;
	}

	public static PDController sampleDampedPDControlledBody (float kP, float kD, float target){
		return dampedPDControlledBody(7.0f, 25.0f, kP, kD, 0.0f, target);
	}

	public static ForceDirectedBody dampedPIDControlledBody(float mass, float damping_coefficient, float kP, float kD, float kI, float start_pos, float target) {
		ForceDirectedBody body = new ForceDirectedBody(mass);
		body.position_ = start_pos;
		body.addForce(new PIDController(body, target, kP, kD, kI));
		body.addForce(new DampingForce(body, damping_coefficient));
		return body;
	}

	public static ForceDirectedBody sampleDampedPIDControlledBody(float kP, float kD, float kI, float target) {
		return dampedPIDControlledBody(7.0f, 25.0f, kP, kD, kI, 0.0f, target);
	}

	public static ForceDirectedBody limitedDampedPDControlledBody(float mass, float damping_coefficient, float kP, float kD, float limit, float start_pos, float target) {
		ForceDirectedBody body = new ForceDirectedBody(mass);
		body.position_ = start_pos;
		body.addForce(new LimitedPDController(body, target, kP, kD, limit));
		body.addForce(new DampingForce(body, damping_coefficient));
		return body;
	}

	public static ForceDirectedBody sampleLimitedDampedPDControlledBody(float kP, float kD, float limit, float target) {
		return limitedDampedPDControlledBody(7.0f, 25.0f, kP, kD, limit, 0.0f, 0.3f);
	}
}