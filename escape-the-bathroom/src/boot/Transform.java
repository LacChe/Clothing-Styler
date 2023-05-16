package boot;

public class Transform {

	public double[] position;
	public double[] scale;
	public double[] rotation;

	public Transform(double[] p, double[] s, double[] r) {
		position = p;
		scale = s;
		rotation = r;
	}

	public Transform() {
		position = new double[] { 0, 0 };
		scale = new double[] { 1, 1 };
		rotation = new double[] { 0 };
	}

	public Transform(double p1, double p2, double s1, double s2, double r) {
		position = new double[] { p1, p2 };
		scale = new double[] { s1, s2 };
		rotation = new double[] { r };
	}

	public Transform(double p1, double p2, double s1, double s2) {
		position = new double[] { p1, p2 };
		scale = new double[] { s1, s2 };
		rotation = new double[] { 0 };
	}

	public Transform(double p1, double p2) {
		position = new double[] { p1, p2 };
		scale = new double[] { 1, 1 };
		rotation = new double[] { 0 };
	}

}
