package Content.Movement;

public record Vector(double x, double y) {

    public static final Vector ZERO = new Vector(0, 0);

    public Vector add(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y);
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    public Vector invert() {
        return new Vector(-x, -y);
    }

    public Vector normalize() {
        double magnitude = magnitude();
        return (magnitude == 0) ? this : multiply(1.0 / magnitude);
    }

    public Vector multiply(double multiplier) {
        return new Vector(x * multiplier, y * multiplier);
    }

    private double magnitude() {
        return Math.sqrt(x * x + y * y);
    }
}
