
/**
 * Example01 illustrates some basics of Java 2D.
 * This version is compliant with Java 1.2 Beta 3, Jun 1998.
 * Please refer to: <BR>
 * http://www.javaworld.com/javaworld/jw-07-1998/jw-07-media.html
 * <P>
 * @author Bill Day <bill.day@javaworld.com>
 * @version 1.0
 * @see java.awt.Graphics2D
**/

/**
Geoffrey Matthews modified this code to 
make a simple raytracer
13 May 2017
**/

import java.awt.*;
import java.awt.event.*;

public class Example01 extends Frame {
    
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 1024;

    public static Sphere[] myWorld;
    public static Vector3 light;
    public static int numSpheres = 2000;
    
    public static void main(String args[]) {

	System.out.printf("nSpheres: %d\n", numSpheres);
	myWorld = new Sphere[numSpheres];
	for (int i = 0; i < numSpheres; i++) {
	    myWorld[i] = randomSphere();
	}
	light = new Vector3(0.577, 0.577, 0.577);
	new Example01();
    }

    public static Sphere randomSphere() {
	double x = Math.random() * 16.0 - 8.0;
	double y = Math.random() * 16.0 - 8.0;
	double z = Math.random() * 16.0 - 8.0;
	double radius = Math.random() * 0.2 + 0.2;
	double r = Math.random();
	double g = Math.random();
	double b = Math.random();
	return new Sphere(x, y, z, radius, r, g, b);
    }	

    public Vector3 imagePlane(int u, int v) {
	double imagePlaneSize = 10.0;
	double x = 2*u/(float)WIDTH - 1.0;
	double y = 2*v/(float)WIDTH - 1.0;
	return new Vector3(imagePlaneSize*x, -imagePlaneSize*y, 0.0);
    }

    /**
     * Raytracing!
     **/
    public Color getColor(int u, int v) {
	Vector3 imagePlanePoint = imagePlane(u,v);
	Vector3 camera = new Vector3(0.0, 0.0, 20.0);
	Ray r = new Ray(camera, imagePlanePoint.sub(camera));
	double minHit = 1.0e10;
	int whichSphere = -1;
	for (int i = 0; i < myWorld.length; i++) {
	    double tempHit = r.intersect(myWorld[i]);
	    if (tempHit > 0.01 && tempHit < minHit) {
		minHit = tempHit;
		whichSphere = i;
	    };
	};
	if (whichSphere >= 0) {
	    Vector3 p = r.pointOnRay(minHit);
	    return myWorld[whichSphere].shade(p, light);
	} else {
	    return new Color(0.5f, 0.75f, 1.0f);
	}
    }

    public Example01() {
	super("Raytracing!");
	setSize(WIDTH, HEIGHT); 
	setVisible(true);
	addWindowListener(new WindowAdapter() 
	    {public void windowClosing(WindowEvent e) 
		{dispose(); System.exit(0);}  
	    }
	    );
    }

    public void paint(Graphics g) {
	for (int u = 0 ; u < WIDTH; u++) {
	    for (int v = 0 ; v < HEIGHT; v++) {
		g.setColor(getColor(u,v));
		/* For some reason the designers of Java 
		   graphics didn't include a primitive to 
		   draw a single pixel.  So... */
		g.drawLine(u,v,u,v);
	    }
	}
    }
}
 
