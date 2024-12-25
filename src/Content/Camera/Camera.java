package Content.Camera;

import Handlers.ContentHandler;

import java.awt.*;

public class Camera {
    public Vector position = new Vector(0.0, 0.0, -50.0); // Camera starts at origin
    public double fov = 90.0; // Field-of-view
    public double pitch = 0.0; // Vertical rotation (clamped to [-90, 90])
    public double yaw = 0.0; // Horizontal rotation

    public void update() {
        for (Vertex v : ContentHandler.vertices) {
            v.update();
        }
    }

    public void render(Graphics g) {
        for (Vertex v : ContentHandler.vertices) {
            v.render(g);
        }
    }

    public double[] projectTo2D(double[] worldPos, int screenWidth, int screenHeight) {
        double dx = worldPos[0] - position.x;
        double dy = worldPos[1] - position.y;
        double dz = worldPos[2] - position.z;

        double cosYaw = Math.cos(Math.toRadians(yaw));
        double sinYaw = Math.sin(Math.toRadians(yaw));
        double cosPitch = Math.cos(Math.toRadians(pitch));
        double sinPitch = Math.sin(Math.toRadians(pitch));

        // Apply yaw rotation
        double rotatedX = cosYaw * dx + sinYaw * dz;
        double rotatedZ = -sinYaw * dx + cosYaw * dz;

        // Apply pitch rotation
        double rotatedY = cosPitch * dy - sinPitch * rotatedZ;
        rotatedZ = sinPitch * dy + cosPitch * rotatedZ;

        // Clip points too close to or behind the camera
        final double nearPlane = 0.1;
        if (rotatedZ < nearPlane) {
            return null;
        }

        // Perspective projection
        double fovFactor = Math.tan(Math.toRadians(fov) / 2);
        double projectedX = rotatedX / (rotatedZ * fovFactor);
        double projectedY = rotatedY / (rotatedZ * fovFactor);

        // Map to screen coordinates
        double screenX = (projectedX + 1) * (screenWidth / 2.0);
        double screenY = (1 - projectedY) * (screenHeight / 2.0);

        return new double[]{screenX, screenY};
    }

}