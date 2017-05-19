/**
Unit tests for raytracer modules
**/

import java.awt.*;

public class Tests {
    
    public static void main(String args[]) {

	// Test vector length
	Vector3 v = new Vector3(1.0, 1.0, 1.0);
	shouldBe(v.length(), Math.sqrt(3.0));

	// Test ray sphere intersect
	Sphere s = new Sphere(0.0, 0.0, 0.0,
			      1.0,
			      0.0, 0.0, 0.0);
	Vector3 p = new Vector3(0.0, 0.0, 20.0);
	Vector3 vec = new Vector3(0.0, 0.0, -1.0);
	Ray r = new Ray(p, vec);
	shouldBe(r.intersect(s), 19.0);

    }

    public static void shouldBe(double a, double b) {
	if (Math.abs(a - b) < 0.001)
	    System.out.printf("PASSED: ");
	else
	    System.out.printf("FAILED <=========: ");
	System.out.printf(" %f should be %f\n", a, b);
    }

}
 
