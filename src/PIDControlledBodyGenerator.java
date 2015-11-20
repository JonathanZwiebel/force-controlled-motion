public class PIDControlledBodyGenerator {

	public static ForceDirectedBody dampedPDControlledBody(float mass, float damping_coefficient, float kP, float kD, float start_pos, float target) {
        ForceDirectedBody body = new ForceDirectedBody(mass);
        body.position_ = start_pos;
        body.addForce(new PDController(body, target, kP, kD));
        body.addForce(new DampingForce(body, damping_coefficient));
        return body;
	}

	public static ForceDirectedBody samplePDControlledBody (float target, float kP, float kD){
		return dampedPDControlledBody(7.0f, 25.0f, kP, kD, 0.0f, target);
	}
}