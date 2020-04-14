package resource;

/**
 * 
 * This is an interface completing the scheme of the visitor pattern.
 *
 */

public interface HealthServiceVisitor {
	
	void serve(Arrival arrival); //Arrival visitor
	void serve(BloodTest bloodTest); //Blood_Test visitor
	void serve(Consultation consultation); //Consultation visitor
	void serve(MRI mri); //MRI visitor
	void serve(Radiography radiography); // Radiography visitor

}
