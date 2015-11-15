/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Run this class
 */
public class Run {
    public static final float RUNTIME = 5.0f;

    public static void main(String[] args) {
        try {
            ForceDirectedBody body = getSamplePDControlledLifter();
            body.open();
            while(body.time_ < RUNTIME) {
                body.step();
                body.log();
            }
            body.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ForceDirectedBody getSampleDampedMassSpringSystem() {
        float mass = 100.0f; // In kg
        float elastic_coefficient = 100.0f; // In N/m
        float rest_position = 0.0f; // In m
        float damping_coefficient = 50.0f; //In N*m/s
        float initial_position = 15.0f;

        ForceDirectedBody dampedOscilator = new ForceDirectedBody(mass);
        dampedOscilator.addForce(new ElasticForce(dampedOscilator, elastic_coefficient, rest_position));
        dampedOscilator.addForce(new DampingForce(dampedOscilator, damping_coefficient));
        dampedOscilator.position_ = initial_position;
        return dampedOscilator;
    }


    public static ForceDirectedBody getSamplePDControlledLifter() {
        float mass = 5.0f; // In kg
        float target = 0.03f; // In m
        float kP = 140.0f;
        float kD = 500.0f;
        float dampingCoefficient = 25.0f; // In N*m/s
        ForceDirectedBody lifter = new ForceDirectedBody(mass);
        lifter.addForce(new PDController(lifter, target, kP, kD));
        lifter.addForce(new DampingForce(lifter, dampingCoefficient));
        //lifter.addForce(new GravitationalForce(lifter));
        return lifter;
    }
}
