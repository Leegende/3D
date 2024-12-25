package Handlers;

import Content.Camera.Vertex;

import java.awt.*;

public class InitializeHandler {
    public static void init() {
        ContentHandler.vertices.add(new Vertex(
                new double[]{0, 0, 0},  // Origin
                new double[]{100, 0, 0}, // X-axis endpoint
                new double[]{0, 100, 0}, // Y-axis endpoint
                Color.RED
        ));
        ContentHandler.vertices.add(new Vertex(
                new double[]{0, 0, 0},  // Origin
                new double[]{0, 0, 100}, // Z-axis endpoint
                new double[]{0, 100, 0}, // Y-axis endpoint
                Color.GREEN
        ));
        ContentHandler.vertices.add(new Vertex(
                new double[]{0, 0, 0},  // Origin
                new double[]{100, 0, 0}, // X-axis endpoint
                new double[]{0, 0, 100}, // Z-axis endpoint
                Color.BLUE
        ));
    }

}