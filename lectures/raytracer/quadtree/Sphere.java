import java.awt.*;

public class Sphere {

    double radius;
    Vector3 center;
    Vector3 color;

    Sphere(double x, double y, double z,
	   double radius,
	   double r, double g, double b) {
        this.radius = radius;
        this.center = new Vector3(x, y, z);
        this.color = new Vector3(r, g, b);
    }

    public void print() {
	System.out.printf("sphere: ");
	System.out.printf("%3.3f ", this.center.getX());
	System.out.printf("%3.3f ", this.center.getY());
	System.out.printf("%3.3f ", this.center.getZ());
	System.out.printf("%3.3f ", this.radius);
	System.out.printf("%3.3f ", this.color.getX());
	System.out.printf("%3.3f ", this.color.getY());
	System.out.printf("%3.3f ", this.color.getZ());
	System.out.printf("\n");
    }

    public Color shade(Vector3 point, Vector3 light) {
	Vector3 pointNorm = point.sub(this.center).normalize();
	float falloff = (float)pointNorm.dot(light);
	if (falloff < 0.0f) falloff = 0.0f;
	return new Color(falloff * (float)this.color.getX(),
			 falloff * (float)this.color.getY(),
			 falloff * (float)this.color.getZ());
    }

    public Vector3 getColor() {
	return this.color;
    }

    public double getRadius() {
	return this.radius;
    }
    public Vector3 getCenter() {
	return this.center;
    }
}
