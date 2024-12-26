package Handlers.RunTimeHandler;

import Defines.RunTimeDefines;
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
        g.drawString(String.format("Position: X: %.2f, Y: %.2f, Z: %.2f", ContentHandler.camera.position.x, ContentHandler.camera.position.y, ContentHandler.camera.position.z), 20, 20);
        g.drawString(String.format("Pitch: %.2f, Yaw: %.2f", ContentHandler.camera.pitch, ContentHandler.camera.yaw), 20, 40);

        g.drawLine(RunTimeDefines.FRAME.getWidth() / 2, 0, RunTimeDefines.FRAME.getWidth() / 2, RunTimeDefines.FRAME.getHeight());
        g.drawLine(0, RunTimeDefines.FRAME.getHeight() / 2, RunTimeDefines.FRAME.getWidth(), RunTimeDefines.FRAME.getHeight() / 2);
    }
}