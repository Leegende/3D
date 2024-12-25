package Content.Camera;

public class Vector {
    public double x;
    public double y;
    public double z;
    double length;

    public Vector(double x, double y, double z) {
        length = Math.sqrt(x * x + y * y + z * z);

        this.x = x / length;
        this.y = y / length;
        this.z = z / length;
    }

}
