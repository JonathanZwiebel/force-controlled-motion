import com.sun.org.apache.bcel.internal.ExceptionConstants;

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
    public float mass;
    public float timestamp, position, velocity, acceleration;
    public ArrayList<Float> forces;
    public FileWriter writer;

    public static final float UPDATES_PER_SECOND = 200;
    public static final float RUNTIME = 10;

    public ForceDirectedBody(float mass) throws IOException{
        this.mass = mass;
        forces = new ArrayList();
        forces.add(1.0f);
        position = 0;
        velocity = 0;
        acceleration = 0;
        timestamp = 0;
        File file_ = new File("logs/log.csv");
        if(!file_.exists()) {
            file_.createNewFile();
        }
        writer = new FileWriter(file_);
        writer.append("Timestamp, Position, Velocity, Acceleration\n");
    }

    public void step() throws IOException {
        timestamp += 1 / UPDATES_PER_SECOND;
        float fnet_ = 0;
        for(Float force : forces) {
            fnet_ += (force / UPDATES_PER_SECOND);
        }
        acceleration = fnet_ / mass;
        velocity += acceleration;
        position += velocity / UPDATES_PER_SECOND;
    }

    public void log() throws IOException {
        writer.append(timestamp + "," + position + "," + velocity + "," + acceleration + "\n");
    }

    public void close() throws IOException{
        writer.close();
    }

    public static void main(String[] args) {
        try {
            ForceDirectedBody lifter = new ForceDirectedBody(1.0f);
            // goes for one second
            while(lifter.timestamp < RUNTIME) {
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
