package Content.Camera;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Voxel {
    public enum Face {FRONT, BACK, LEFT, RIGHT, UP, DOWN}

    private final List<Triangle> faces = new ArrayList<>();

    private final double x, y, z;
    private final double size;
    // Voxel size

    public Voxel(double x, double y, double z, double size, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
    }

    public void addFace(Face face, Color color) {
        switch (face) {
            case FRONT -> {
                double[] p001 = {x, y, z + size};
                double[] p101 = {x + size, y, z + size};
                double[] p011 = {x, y + size, z + size};
                double[] p111 = {x + size, y + size, z + size};
                faces.add(createTriangle(p001, p101, p011, color));
                faces.add(createTriangle(p101, p111, p011, color));
            }
            case BACK -> {
                double[] p000 = {x, y, z};
                double[] p100 = {x + size, y, z};
                double[] p010 = {x, y + size, z};
                double[] p110 = {x + size, y + size, z};
                faces.add(createTriangle(p000, p100, p010, color));
                faces.add(createTriangle(p100, p110, p010, color));
            }
            case LEFT -> {
                double[] p000 = {x, y, z};
                double[] p001 = {x, y, z + size};
                double[] p010 = {x, y + size, z};
                double[] p011 = {x, y + size, z + size};
                faces.add(createTriangle(p000, p001, p010, color));
                faces.add(createTriangle(p001, p011, p010, color));
            }
            case RIGHT -> {
                double[] p100 = {x + size, y, z};
                double[] p101 = {x + size, y, z + size};
                double[] p110 = {x + size, y + size, z};
                double[] p111 = {x + size, y + size, z + size};
                faces.add(createTriangle(p100, p101, p110, color));
                faces.add(createTriangle(p101, p111, p110, color));
            }
            case UP -> {
                double[] p010 = {x, y + size, z};
                double[] p011 = {x, y + size, z + size};
                double[] p110 = {x + size, y + size, z};
                double[] p111 = {x + size, y + size, z + size};
                faces.add(createTriangle(p010, p110, p011, color));
                faces.add(createTriangle(p110, p111, p011, color));
            }
            case DOWN -> {
                double[] p000 = {x, y, z};
                double[] p001 = {x, y, z + size};
                double[] p100 = {x + size, y, z};
                double[] p101 = {x + size, y, z + size};
                faces.add(createTriangle(p000, p100, p001, color));
                faces.add(createTriangle(p100, p101, p001, color));
            }
        }
    }

    private Triangle createTriangle(double[] a, double[] b, double[] c, Color color) {
        return new Triangle(a, b, c, color);
    }

    public List<Triangle> getFaces() {
        return faces;
    }

    public void render(Graphics g) {
        for (Triangle face : faces) {
            face.render(g);
        }
    }
}