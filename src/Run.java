/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Run this class
 */
public class Run {
    public static final float RUNTIME = 5.0f;

    public static void main(String[] args) {
        try {
            ForceDirectedBody body = PIDControlledBodyGenerator.samplePDControlledBody(Float.parseFloat(args[0]), Float.parseFloat(args[1]), 
                Float.parseFloat(args[2]));
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

    // too jerky
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


    public static ForceDirectedBody getBasicPDController() {
        float mass = 7.0f; // In kg
        float target = 0.03f; // In m
        float kP = 60.0f;
        float kD = 12.0f;
        float dampingCoefficient = 25.0f; // In N*m/s
        ForceDirectedBody object = new ForceDirectedBody(mass);
        object.addForce(new PDController(object, target, kP, kD));
        object.addForce(new DampingForce(object, dampingCoefficient));
        return object;
    }

    public static ForceDirectedBody getLimitedPDController() {
        float mass = 7.0f; // In kg
        float target = 0.075f; // In m
        float kP = 60.0f;
        float kD = 12.0f;
        float dampingCoefficient = 25.0f; // In N*m/s
        float force_limit = 0.25f;
        ForceDirectedBody object = new ForceDirectedBody(mass);
        object.addForce(new LimitedPDController(object, target, kP, kD, force_limit));
        object.addForce(new DampingForce(object, dampingCoefficient));
        return object;
    }

    public static ForceDirectedBody getBasicPIDController() {
        float mass = 7.0f; // In kg
        float target = 0.03f; // In m
        float kP = 3000.0f;
        float kD = 10.0f;
        float kI = 0.01f;
        float dampingCoefficient = 25.0f; // In N*m/s
        ForceDirectedBody object = new ForceDirectedBody(mass);
        object.addForce(new PIDController(object, target, kP, kD, kI));
        object.addForce(new DampingForce(object, dampingCoefficient));
        object.addForce(new GravitationalForce(object));
        return object;
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
