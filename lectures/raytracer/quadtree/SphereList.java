public class SphereList {
    Sphere s;
    SphereList next;

    SphereList(Sphere s, SphereList next) {
	this.s = s;
	this.next = next;
    }

    public void print () {
	this.s.print();
	if (this.next != null) {
	    this.next.print();
	}
    }
    
    public SphereList cons(Sphere s) {
	return new SphereList(s, this);
    }

    public Sphere getSphere() {
	return this.s;
    }

    public SphereList getNext() {
	return this.next;
    }

    public int length() {
	if (this.next == null) {
	    return 1;
	} else {
	    return 1 + this.next.length();
	}
    }

}
	
	
