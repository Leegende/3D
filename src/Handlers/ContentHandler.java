package Handlers;

import Content.Camera.Camera;
import Content.Camera.Vertex;

import java.awt.*;
import java.util.ArrayList;

public class ContentHandler {
    public static ArrayList<Vertex> vertices = new ArrayList<>();
    public static Camera camera = new Camera();

    public static void render(Graphics g) {
        camera.render(g);
    }

    public static void update() {
        camera.update();
    }
}