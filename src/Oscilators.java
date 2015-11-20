public class Oscilators {
	public static ForceDirectedBody sampleDampedOscilator() {
		    float mass = 5.0f; 
	        float elastic_coefficient = 100.0f;
	        float rest_position = 0.0f; 
	        float damping_coefficient = 5.0f;
	        float initial_position = 15.0f;

	        ForceDirectedBody dampedOscilator = new ForceDirectedBody(mass);
	        dampedOscilator.addForce(new ElasticForce(dampedOscilator, elastic_coefficient, rest_position));
	        dampedOscilator.addForce(new DampingForce(dampedOscilator, damping_coefficient));
	        dampedOscilator.position_ = initial_position;
	        return dampedOscilator;
	}

	public static ForceDirectedBody dampedOscilator(float mass, float elastic_coefficient, float damping_coefficient, float rest_position, float initial_position) {
	        ForceDirectedBody dampedOscilator = new ForceDirectedBody(mass);
	        dampedOscilator.addForce(new ElasticForce(dampedOscilator, elastic_coefficient, rest_position));
	        dampedOscilator.addForce(new DampingForce(dampedOscilator, damping_coefficient));
	        dampedOscilator.position_ = initial_position;
	        return dampedOscilator;
	}
}