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
    public static final float UPDATES_PER_SECOND = 128;
    public static final String FILEPATH = "logs/log.csv";
    // TODO: Allow for more than one body

    public float mass_;
    public float time_, position_, velocity_, acceleration_;
    public ArrayList<OneSpaceForce> forces_;
    public FileWriter writer_;

    public ForceDirectedBody(float mass){
        mass_ = mass;
        time_ = 0;
        position_ = 0;
        velocity_ = 0;
        acceleration_ = 0;
        forces_ = new ArrayList();
    }

    public void addForce(OneSpaceForce force) {
        forces_.add(force);
    }

    public void step() throws IOException {
        time_ += 1 / UPDATES_PER_SECOND;
        float fnet_ = 0;
        for(OneSpaceForce force : forces_) {
            if(!(force instanceof StaticFriction)) {
                force.step();
                fnet_ += force.force_;
            }
        }
        for(OneSpaceForce force : forces_) {
            if(force instanceof StaticFriction) {
                if(Math.abs(force.force_) > Math.abs(fnet_)) {
                    fnet_ = 0;
                }
                else {
                    fnet_ += force.force_;
                }
            }
        }

        acceleration_ = fnet_ / mass_;
        velocity_ += acceleration_ / UPDATES_PER_SECOND;
        position_ += velocity_ / UPDATES_PER_SECOND;
    }

    public void open() throws IOException {
        File file_ = new File(FILEPATH);
        if(!file_.exists()) {
            file_.createNewFile();
        }
        writer_ = new FileWriter(file_);
       // writer_.append("time (s), position (m), velocity (m/s), acceleration(m/s^2)\n");
    }

    public void log() throws IOException {
        writer_.append(time_ + "," + position_ + "," + velocity_ + "," + acceleration_ + "\n");
    }

    public void close() throws IOException{
        writer_.close();
    }
}
