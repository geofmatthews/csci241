import java.awt.*;

public class Ray {

    Vector3 center;
    Vector3 vector;

    Ray(Vector3 center, Vector3 vector) {
	this.center = center;
	this.vector = vector.normalize();
    }

    public double intersect(Sphere s) {
	// returns -1 for no solutions
	Vector3 q = this.center.sub(s.center);
	// double a = 1.0;
	double b = 2.0 * q.dot(this.vector);
	double c = q.dot(q) - s.radius * s.radius;
	double discriminant = b * b - 4 * c;
	if (discriminant < 0.0) return -1.0; 
	return 0.5*(-b - Math.sqrt(discriminant));
    }

    public Vector3 pointOnRay(double t) {
	return this.center.add(this.vector.mult(t));
    }
    
    

}
