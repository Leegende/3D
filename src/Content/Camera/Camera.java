package Content.Camera;

import Handlers.ContentHandler;

import java.awt.*;

public class Camera {
    public Vector position = new Vector(100d, 100d, 100d);
    public double fov = 90;
    public double pitch = 0.0;
    public double yaw = 0.0;

    public void update() {
        for (Vertex vertex : ContentHandler.vertices) {
            vertex.update();
        }
    }

    public void render(Graphics g) {
        for (Vertex vertex : ContentHandler.vertices) {
            vertex.render(g);
        }
    }

    public double[] projectTo2D(double[] worldPos, int screenWidth, int screenHeight) {
        double inverseZ = 1.0 / Math.max(0.01, Math.cos(Math.toRadians(pitch)) * (Math.cos(Math.toRadians(yaw)) * (worldPos[2] - position.z) - Math.sin(Math.toRadians(yaw)) * (worldPos[0] - position.x)) + Math.sin(Math.toRadians(pitch)) * (worldPos[1] - position.y));

        return new double[]{((Math.cos(Math.toRadians(yaw)) * (worldPos[0] - position.x) + Math.sin(Math.toRadians(yaw)) * (worldPos[2] - position.z)) * (1.0 / Math.tan(Math.toRadians(fov) / 2)) * inverseZ + 1) * (screenWidth / 2.0),

                (1 - ((Math.cos(Math.toRadians(pitch)) * (worldPos[1] - position.y) - Math.sin(Math.toRadians(pitch)) * (Math.cos(Math.toRadians(yaw)) * (worldPos[2] - position.z) - Math.sin(Math.toRadians(yaw)) * (worldPos[0] - position.x))) * (1.0 / Math.tan(Math.toRadians(fov) / 2)) * inverseZ)) * (screenHeight / 2.0)};
    }

}