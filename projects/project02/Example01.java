
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
    
    public static final int WIDTH = 512;
    public static final int HEIGHT = 512;
    /**
     * Instantiates an Example01 object.
     **/
    public static void main(String args[]) {
	new Example01();
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
     * You are to replace this method with one which does
     * raytracing in line with the description in the
     * proejct document.
     **/
    public Color getColor(int u, int v) {
	float uf = (float)u/WIDTH;
	float vf = (float)v/HEIGHT;
	return new Color(uf, vf, 1.0f-uf);
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
 
