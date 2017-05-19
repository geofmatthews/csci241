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
Geoffrey Matthews modified this code to show how to make
an image pixel by pixel.
13 April 2017
**/

import java.awt.*;
import java.awt.event.*;

public class Example01 extends Frame {
    
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 1024;

    public static Quadtree myWorld;
    public static Vector3 light;
    public static int numSpheres = 100000;
    public static int quadtreeDepth = 5;
    
    public static void main(String args[]) {

	System.out.printf("nSpheres: %d\n", numSpheres);
	System.out.printf("qTree depth: %d\n", quadtreeDepth);
	myWorld = new Quadtree(-10,-10,10,10,20,quadtreeDepth);
	for (int i = 0; i < numSpheres; i++) {
	    myWorld.addSphere(randomSphere());
	}
	light = new Vector3(0.577, 0.577, 0.577);
	new Example01();
    }

    public static Sphere randomSphere() {
	double x = Math.random() * 16.0 - 8.0;
	double y = Math.random() * 16.0 - 8.0;
	double z = Math.random() * 16.0 - 8.0;
	double radius = Math.random() * 0.05 + 0.05;
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
	Sphere whichSphere = null;
	SphereList spheres = myWorld.getSpheres(imagePlanePoint.getX(),
						imagePlanePoint.getY());
	while (spheres != null) {
	    Sphere s = spheres.getSphere();
	    double tempHit = r.intersect(s);
	    if (tempHit > 0.01 && tempHit < minHit) {
		minHit = tempHit;
		whichSphere = s;
	    };
	    spheres = spheres.getNext();
	};
	if (whichSphere != null) {
	    Vector3 p = r.pointOnRay(minHit);
	    return whichSphere.shade(p, light);
	} else {
	    return new Color(0.5f, 0.75f, 1.0f);
	}
    }
    /**
     * Our Example01 constructor sets the frame's size, adds the
     * visual components, and then makes them visible to the user.
     * It uses an adapter class to deal with the user closing
     * the frame.
     **/
    public Example01() {
	//Title our frame.
	super("Java 2D Example01");
	
	//Set the size for the frame.
	setSize(WIDTH, HEIGHT); 
	
	//We need to turn on the visibility of our frame
	//by setting the Visible parameter to true.
	setVisible(true);
	
	//Now, we want to be sure we properly dispose of resources 
	//this frame is using when the window is closed.  We use 
	//an anonymous inner class adapter for this.
	addWindowListener(new WindowAdapter() 
	    {public void windowClosing(WindowEvent e) 
		{dispose(); System.exit(0);}  
	    }
	    );
    }
    /**
     * The paint method provides the real magic.  Here we
     * cast the Graphics object to Graphics2D to illustrate
     * that we may use the same old graphics capabilities with
     * Graphics2D that we are used to using with Graphics.
     **/
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
 
