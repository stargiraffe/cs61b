public class NBody {
	// int N = 0;
	// double R = readRadius(filename);

	public static double readRadius(String path) {
		In in = new In(path);
		int N = in.readInt();		
		return in.readDouble();
	}

	public static Body[] readBodies(String path) {
		In in = new In(path);
		int N = in.readInt();
		double R = in.readDouble();
		Body[] bodies = new Body[N];
		for(int i=0;i<N;i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String image = in.readString();
			bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, image);
		}
		return bodies;
	} 

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double R = readRadius(filename);
		Body[] bodies = readBodies(filename);
		int N = bodies.length;

		StdDraw.setScale(-R, R);
		// StdDraw.clear();

		StdDraw.enableDoubleBuffering();

		double time = 0;
		while(time < T) {
			double[] xForce = new double[N];
			double[] yForce = new double[N];
			for(int i=0;i<N;i++) {
				xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for(int i=0;i<N;i++) {
				bodies[i].update(dt, xForce[i], yForce[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i=0;i<N;i++) {
				bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", N);
		StdOut.printf("%.2e\n", R);
		for(int i=0;i<bodies.length;i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos,
				bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
		}
	}
}  