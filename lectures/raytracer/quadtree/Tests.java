/**
Unit tests for raytracer modules
**/

import java.awt.*;

public class Tests {
    
    public static void main(String args[]) {

	// Test testing framework
	shouldBe(1.0, 2.0, "SHOULD FAIL");
	shouldBe(1.0, 1.0, "SHOULD PASS");

	// Test vector length
	Vector3 v = new Vector3(1.0, 1.0, 1.0);
	shouldBe(v.length(), Math.sqrt(3.0), "vector length");

	// Test normalization
	v = v.normalize();
	shouldBe(v.length(), 1.0, "vector normalization");

	// Test ray sphere intersection
	Sphere s = new Sphere(0.0, 0.0, 0.0,
			      1.0,
			      0.0, 0.0, 0.0);
	Vector3 p = new Vector3(0.0, 0.0, 20.0);
	Vector3 vec = new Vector3(0.0, 0.0, -1.0);
	Ray r = new Ray(p, vec);
	shouldBe(r.intersect(s), 19.0, "ray sphere intersect");


	// Test quadtree
	int nSpheres = 1000;
	System.out.printf("Quadtree with about %d spheres per leaf:\n",
			  nSpheres/4);
	Quadtree q = new Quadtree(-10.0, -10.0, 10.0, 10.0, 20.0, 1);
	for (int i = 0; i < nSpheres; i++)
	    q.addSphere(randomSphere());
	q.print();
	System.out.printf("Quadtree with about %d spheres per leaf:\n",
			   nSpheres/(4*4));
	q = new Quadtree(-10.0, -10.0, 10.0, 10.0, 20.0, 2);
	for (int i = 0; i < nSpheres; i++)
	    q.addSphere(randomSphere());
	q.print();
	
	
    }

    public static void shouldBe(double a, double b, String msg) {
	if (Math.abs(a - b) < 0.001)
	    System.out.printf("PASSED %s: ", msg);
	else
	    System.out.printf("FAILED %s =========> ", msg);
	System.out.printf(" %f should be %f\n", a, b);
    }


    public static Sphere randomSphere() {
	double x = Math.random() * 20.0 - 10.0;
	double y = Math.random() * 20.0 - 10.0;
	double z = Math.random() * 20.0 - 10.0;
	double radius = 0.1;
	double r = Math.random();
	double g = Math.random();
	double b = Math.random();
	return new Sphere(x, y, z, radius, r, g, b);
    }	
    
}
 
