public class Vector3 {

    double x, y, z; 

    Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void print() {
	System.out.printf("%3.3f %3.3f %3.3f\n", this.x, this.y, this.z);
    }

    double getX() { return this.x; }
    double getY() { return this.y; }
    double getZ() { return this.z; }

    public Vector3 add(Vector3 vector) {
	double newx, newy, newz;
        newx = this.x + vector.x;
        newy = this.y + vector.y;
        newz = this.z + vector.z;
        return new Vector3(newx, newy, newz);
    }

    public Vector3 mult(double scalar) {
	double newx, newy, newz;
        newx = scalar * this.x;
        newy = scalar * this.y;
        newz = scalar * this.z;
        return new Vector3(newx, newy, newz);
    }

    public Vector3 sub(Vector3 vector) {
	double newx, newy, newz;
        newx = this.x - vector.x;
        newy = this.y - vector.y;
        newz = this.z - vector.z;
        return new Vector3(newx, newy, newz);
    }

    public double dot(Vector3 vector) {
	double newx, newy, newz;
        newx = this.x * vector.x;
        newy = this.y * vector.y;
        newz = this.z * vector.z;
        return (newx + newy + newz);
    }

    public double length() {
	return Math.sqrt(this.dot(this));
    }

    public Vector3 normalize() {
	double len;
	len = this.length();
	if (len == 0) return new Vector3(0.0, 0.0, -1.0);
	else return this.mult(1.0/len);
    }
}
