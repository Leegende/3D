package Content.Camera;

import Defines.RunTimeDefines;
import Handlers.ContentHandler;

import java.awt.*;

public class Triangle {
    private final Color color;
    double[] a;
    double[] b;
    double[] c;
    double[] renderA;
    double[] renderB;
    double[] renderC;

    public Triangle(double[] a, double[] b, double[] c, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public void update() {
        int screenWidth = RunTimeDefines.FRAME.getWidth();
        int screenHeight = RunTimeDefines.FRAME.getHeight();

        renderA = ContentHandler.camera.projectTo2D(a, screenWidth, screenHeight);
        renderB = ContentHandler.camera.projectTo2D(b, screenWidth, screenHeight);
        renderC = ContentHandler.camera.projectTo2D(c, screenWidth, screenHeight);

        if (renderA == null && renderB == null && renderC == null) {
            renderA = renderB = renderC = new double[]{-1, -1};
        }
    }

    public void render(Graphics g) {
        if (renderA != null && renderB != null && renderC != null) {
            g.setColor(color);

            int[] xPoints = {(int) renderA[0], (int) renderB[0], (int) renderC[0]};
            int[] yPoints = {(int) renderA[1], (int) renderB[1], (int) renderC[1]};

            if (isValidPolygon(xPoints, yPoints)) {
                g.drawPolygon(xPoints, yPoints, 3);
            }
        }
    }

    private boolean isValidPolygon(int[] xPoints, int[] yPoints) {
        return (xPoints[0] != xPoints[1] || yPoints[0] != yPoints[1]) && (xPoints[1] != xPoints[2] || yPoints[1] != yPoints[2]) && (xPoints[0] != xPoints[2] || yPoints[0] != yPoints[2]);
    }

}