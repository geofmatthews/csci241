
public class Quadtree {
    
    double minX, minY, maxX, maxY;
    SphereList spheres;
    Quadtree ul, ur, ll, lr;
    int level;
    double cameraZ;

    Quadtree(double minX, 
	     double minY,
	     double maxX,
	     double maxY,
	     double cameraZ,
	     int level) {
	this.level = level;
	this.minX = minX;
	this.minY = minY;
	this.maxX = maxX;
	this.maxY = maxY;
	this.spheres = null;
	this.cameraZ = cameraZ;
	if (level == 0) {
	    this.ul = null;
	    this.ur = null;
	    this.ll = null;
	    this.lr = null;
	} else {
	    level = level - 1;
	    double midX = (maxX + minX)*0.5;
	    double midY = (maxY + minY)*0.5;
	    this.ul = new Quadtree(minX, midY, midX, maxY, cameraZ, level);
	    this.ur = new Quadtree(midX, midY, maxX, maxY, cameraZ, level);
	    this.ll = new Quadtree(minX, minY, midX, midY, cameraZ, level);
	    this.lr = new Quadtree(midX, minY, maxX, midY, cameraZ, level);
	}
    }

    // Simple routintes to print a sketch of the quadtree:
    public int nSpheres() {
	if (this.level == 0) {
	    int n;
	    if (this.spheres == null) return 0;
	    else return this.spheres.length();
	} else {
	    return ul.nSpheres() + ur.nSpheres() +
		ll.nSpheres() + lr.nSpheres();
	}
    }

    public void print() {
	for (int i = 0; i < 4-this.level; i++) System.out.print("  ");
	System.out.printf("Quadtree level %d;  nSpheres: %d\n",
			  this.level,
			  this.nSpheres());
	if (this.level > 0) {
	    this.ul.print();
	    this.ur.print();
	    this.ll.print();
	    this.lr.print();
	}
    }
	
    public void printSpheres() {
	if (this.level > 0) {
	    this.ul.printSpheres();
	    this.ur.printSpheres();
	    this.ll.printSpheres();
	    this.lr.printSpheres();
	} else if (this.spheres != null) {
	    this.spheres.print();
	}
    }

    // Find spheres that might intersect a ray through (x,y)
    public SphereList getSpheres(double x, double y) {
	if (this.level == 0) {
	    return this.spheres;
	};
	double midX = (this.maxX + this.minX)*0.5;
	double midY = (this.maxY + this.minY)*0.5;
	if ((this.minX <= x) && (x < midX)) {     // left half
	    if ((this.minY <= y) && (y < midY)) { // bottom half
		return ll.getSpheres(x,y);
	    } else {                              // top half
		return ul.getSpheres(x,y);
	    }
	} else {                                  // right half
	    if ((this.minY <= y) && (y < midY)) { // bottom half
		return lr.getSpheres(x,y);
	    } else {
		return ur.getSpheres(x,y);
	    }
	}
    }


    public static double sqr(double x) {return x*x;};

    // find which quads a sphere overlaps, and insert into each
    public void addSphere(Sphere s) {
	if (this.level == 0) { // leaf
	    if (this.spheres == null) {
		this.spheres = new SphereList(s, null);
	    } else {
		this.spheres = this.spheres.cons(s);
	    }
	} else { // interior node
	    double midX = (this.maxX + this.minX)*0.5;
	    double midY = (this.maxY + this.minY)*0.5;
	    double cx = s.getCenter().getX();
	    double cy = s.getCenter().getY();
	    double cz = s.getCenter().getZ();
	    double r = s.getRadius();
	    double pz = this.cameraZ;
	    // x limits:
	    double ax = Math.sqrt(sqr(cx) + sqr(cz - pz));
	    double thetax = Math.atan(r/ax);
	    double psix = Math.asin(cx/ax);
	    double phix = psix - thetax;
	    double x1 = pz * Math.tan(phix);
	    double x2 = pz * Math.tan(psix + 2*thetax);
	    // Make sure x1 is smaller:
	    if (x1 > x2) {
		double tmp = x1;
		x1 = x2;
		x2 = tmp;
	    }
	    // y limits:
	    double ay = Math.sqrt(sqr(cy) + sqr(cz - pz));
	    double thetay = Math.atan(r/ay);
	    double psiy = Math.asin(cy/ay);
	    double phiy = psiy - thetay;
	    double y1 = pz * Math.tan(phiy);
	    double y2 = pz * Math.tan(psiy + 2*thetay);
	    // Make sure y1 is smaller:
	    if (y1 > y2) {
		double tmp = y1;
		y1 = y2;
		y2 = tmp;
	    }
	    if (y1 < midY) {     // touches bottom half
		if (x1 < midX) { // touches left half
		    ll.addSphere(s);
		}
		if (x2 > midX) { // touches right half
		    lr.addSphere(s);
		}
	    }
	    if (y2 > midY) {     // touches top half
		if (x1 < midX) { // touches left half
		    ul.addSphere(s);
		}
		if (x2 > midX) { // touches right half
		    ur.addSphere(s);
		}
	    }
	}
    }
}
