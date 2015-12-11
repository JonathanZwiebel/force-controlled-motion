/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Run this class
 */
public class Run {
    public static final float RUNTIME = 5.0f;

    public static void main(String[] args) {
        try {
            ForceDirectedBody body = PIDControlledBodies.sampleDampedPDControlledBody(Float.parseFloat(args[0]), 
                Float.parseFloat(args[1]), Float.parseFloat(args[2]));
            body.open();
            while(!((FitnessScored) body).completed() || body.time_ < RUNTIME) {
                body.step();
                body.log();
            }
            System.out.println("Fitness: " + ((FitnessScored) body).fitness());
            body.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ForceDirectedBody getLimitedPIDController() {
        float mass = 7.0f; // In kg
        float target = 0.03f; // In m
        float kP = 10000.0f;
        float kD = 200.0f;
        float kI = 1.0f;
        float lower_limit = 67.0f;
        float upper_limit = 69.0f;
        float dampingCoefficient = 25.0f; // In N*m/s
        ForceDirectedBody object = new ForceDirectedBody(mass);
        object.addForce(new LimitedPIDController(object, target, kP, kD, kI, lower_limit, upper_limit));
        object.addForce(new DampingForce(object, dampingCoefficient));
        object.addForce(new GravitationalForce(object));
        return object;
    }

    public static ForceDirectedBody getLifterPDController() {
        float mass = 7.0f; // In kg
        float target = 0.03f; // In m
        float kP = 60.0f;
        float kD = 12.0f;
        float dampingCoefficient = 25.0f; // In N*m/s
        float staticFrictionForce = -120.0f; // In N
        ForceDirectedBody lifter = new ForceDirectedBody(mass);
        lifter.addForce(new PDController(lifter, target, kP, kD));
        lifter.addForce(new DampingForce(lifter, dampingCoefficient));
        lifter.addForce(new GravitationalForce(lifter));
        lifter.addForce(new StaticFriction(staticFrictionForce));
        return lifter;
    }
}
