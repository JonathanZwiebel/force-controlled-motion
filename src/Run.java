/**
 * Created by Jonathan Zwiebel on 11/15/15.
 * Run this class
 */
public class Run {
    public static final float RUNTIME = 10;

    public static void main(String[] args) {
        try {
            ForceDirectedBody lifter = getDampedMassSpringSystem();
            lifter.open();
            while(lifter.time_ < RUNTIME) {
                lifter.step();
                lifter.log();
            }
            lifter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ForceDirectedBody getDampedMassSpringSystem() {
        ForceDirectedBody lifter = new ForceDirectedBody(100.0f);
        lifter.addForce(new ElasticForce(lifter, 100.0f, 0.0f));
        lifter.addForce(new DampingForce(lifter, 50.0f));
        lifter.position_ = 15.0f;
        return lifter;
    }
}
