import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Models an object moving in 1 dimensional space with mass and constant forces
 *
 * Units: s
 * Mass: kg
 * Position: m
 * Velocity: m / s
 * Force: N
 */
public class ForceDirectedBody {
    public static final float UPDATES_PER_SECOND = 200;
    public static final float RUNTIME = 10;
    public static final String FILEPATH = "logs/log.csv";

    public float mass_;
    public float time_, position_, velocity_, acceleration_;
    public ArrayList<OneSpaceForce> forces_;
    public FileWriter writer_;

    public ForceDirectedBody(float mass, ArrayList<OneSpaceForce> forces) throws IOException{
        mass_ = mass;
        forces_ = forces;
        time_ = 0;
        position_ = 0;
        velocity_ = 0;
        acceleration_ = 0;

        File file_ = new File(FILEPATH);
        if(!file_.exists()) {
            file_.createNewFile();
        }
        writer_ = new FileWriter(file_);
        writer_.append("time (s), position (m), velocity (m/s), acceleration(m/s^2)\n");
    }

    public void step() throws IOException {
        time_ += 1 / UPDATES_PER_SECOND;
        float fnet_ = 0;
        for(OneSpaceForce force : forces_) {
            fnet_ += force.force_;
        }
        acceleration_ = fnet_ / mass_;
        velocity_ += acceleration_ / UPDATES_PER_SECOND;
        position_ += velocity_ / UPDATES_PER_SECOND;
    }

    public void log() throws IOException {
        writer_.append(time_ + "," + position_ + "," + velocity_ + "," + acceleration_ + "\n");
    }

    public void close() throws IOException{
        writer_.close();
    }

    public static void main(String[] args) {
        try {
            ArrayList<OneSpaceForce> forces = new ArrayList();
            forces.add(new ConstantForce(0.5f));


            ForceDirectedBody lifter = new ForceDirectedBody(1.0f, forces);
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
}
