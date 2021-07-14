public class Planet {
    static final double G = 6.67 * Math.pow(10,-11); // constant of G
    public double xxPos; // Its current x position.
    public double yyPos; // Its current y position.
    public double xxVel; // Its current velocity in the x direction.
    public double yyVel; // Its current velocity in the y direction.
    public double mass; // Its mass.
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif).

    public Planet(double xP, double yP, double xV, 
                    double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** Calculates the distance between this Planet and a given Planet.
    @param the given Planet
    @return distance
     */
    public double calcDistance(Planet g){
        double rSquare = (this.xxPos - g.xxPos) * (this.xxPos - g.xxPos) 
                        + (this.yyPos - g.yyPos) * (this.yyPos - g.yyPos);
        double r = Math.sqrt(rSquare);
        return r;
    }

    /** Calculate the force exerted on this planet by the given planet.
    @param the given Planet
    @return the force
     */
    public double calcForceExertedBy(Planet g){
        double force;
        force = G * this.mass * g.mass / (this.calcDistance(g) * this.calcDistance(g));
        return force;
    }

    /**Calculate the force in the X direction exerted on this planet by the given planet.
    @param the given Planet
    @return the force in the X direction
     */
    public double calcForceExertedByX(Planet g){
        double forceX;
        double force = this.calcForceExertedBy(g);
        double dx = g.xxPos - this.xxPos;
        double r = this.calcDistance(g);
        forceX = force * dx / r;
        return forceX;
    }

    /**Calculate the force in the Y direction exerted on this planet by the given planet.
    @param the given Planet
    @return the force in the Y direction
     */
    public double calcForceExertedByY(Planet g){
        double forceY;
        double force = this.calcForceExertedBy(g);
        double dy = g.yyPos - this.yyPos;
        double r = this.calcDistance(g);
        forceY = force * dy / r;
        return forceY;
    }

    /** Calculate the net force in the X direction of current Planet
    exerted by all Planets in an array.
    @param an array of Planets
    @return the net force in the X direction
     */
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netForceX = 0.0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                double currentForce = this.calcForceExertedByX(p);
                netForceX = netForceX + currentForce;                
            }
        }
        return netForceX;
    }

    /** Calculate the net force in the Y direction of current Planet
    exerted by all Planets in an array.
    @param an array of Planets.
    @return the net force in the Y direction.
     */
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForceY = 0.0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                double currentForce = this.calcForceExertedByY(p);
                netForceY = netForceY + currentForce;               
            }
        }
        return netForceY;
    }

    /** Update the Planet's position and velocity instance variables.
    @param dt : time step.
    @param fx : the net force in the X direction exerted on this Planet. 
    @param fy : the net force in the X direction exerted on this Planet.
    @return void.
     */
    public void update(double dt, double fx, double fy){
        // First, calculate the Planet's net acceleration.
        double netAccX = fx / this.mass;
        double netAccY = fy / this.mass;
        // Second, calculate the Planet's new velocity.
        double newVelX = this.xxVel + dt * netAccX;
        double newVelY = this.yyVel + dt * netAccY;
        // Third, calculate the Planet's new position.
        double newPosX = this.xxPos + dt * newVelX;
        double newPosY = this.yyPos + dt * newVelY;
        // Update the Planet's velocity and position
        this.xxVel = newVelX;
        this.yyVel = newVelY;
        this.xxPos = newPosX;
        this.yyPos = newPosY;
    }

}