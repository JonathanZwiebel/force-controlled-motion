/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Run this class
 */
public class Run {
    public static final float RUNTIME = 10.0f;

    public static void main(String[] args) {
        try {
            ForceDirectedBody body = getSamplePDControlledSystem();
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
        ForceDirectedBody dampedOscilator = new ForceDirectedBody(100.0f);
        dampedOscilator.addForce(new ElasticForce(dampedOscilator, 100.0f, 0.0f));
        dampedOscilator.addForce(new DampingForce(dampedOscilator, 50.0f));
        dampedOscilator.position_ = 15.0f;
        return dampedOscilator;
    }

    public static ForceDirectedBody getSamplePDControlledSystem() {
        ForceDirectedBody system = new ForceDirectedBody(10.0f);
        system.addForce(new PDController(system, 15.0f, 2.5f, 0.0f));
        return system;
    }
}
