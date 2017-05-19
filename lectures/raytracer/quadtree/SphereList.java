public class SphereList {
    Sphere s;
    SphereList next;

    SphereList(Sphere s, SphereList next) {
	this.s = s;
	this.next = next;
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
	
	
