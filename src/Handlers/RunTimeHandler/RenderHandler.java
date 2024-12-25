package Handlers.RunTimeHandler;

import Handlers.ContentHandler;

import javax.swing.*;
import java.awt.*;

public final class RenderHandler extends JPanel implements Runnable {

    public RenderHandler() {
        this.setBackground(Color.BLACK);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ContentHandler.render(g);

        g.setColor(Color.WHITE);

        g.drawString(String.format("Position: X: %.2f,Y: %.2f, Z: %.2f",
                ContentHandler.camera.position.x, ContentHandler.camera.position.y, ContentHandler.camera.position.z), 20, 20);
    }
}