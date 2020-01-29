public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;
	public Body(double xxPos, double yyPos, double xxVel, 
		double yyVel, double mass, String imgFileName) {
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;
	}

	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body comparedBody) {
		double dx = this.xxPos - comparedBody.xxPos;
		double dy = this.yyPos - comparedBody.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Body comparedBody) {
		double dist = calcDistance(comparedBody);
		return G * this.mass * comparedBody.mass / (dist * dist);
	}

	public double calcForceExertedByX(Body body) {
		return -this.calcForceExertedBy(body) * (this.xxPos - body.xxPos) / this.calcDistance(body);
	}

	public double calcForceExertedByY(Body body) {
		return -this.calcForceExertedBy(body) * (this.yyPos - body.yyPos) / this.calcDistance(body);
	}

	public double calcNetForceExertedByX(Body[] comparedBodies) {
		double res = 0;
		for(Body body : comparedBodies) {
			if(!this.equals(body))
				res += this.calcForceExertedByX(body); 
		}
		return res;
	}

	public double calcNetForceExertedByY(Body[] comparedBodies) {
		double res = 0;
		for(Body body : comparedBodies) {
			if(!this.equals(body))
				res += this.calcForceExertedByY(body); 
		}
		return res;
	}

	public void update(double dt, double fX, double fY) {
		double X = fX / this.mass;
		double Y = fY / this.mass;
		this.xxVel += dt * X;
		this.yyVel += dt * Y;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}