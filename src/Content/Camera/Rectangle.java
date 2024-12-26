package Content.Camera;

import java.awt.*;

public class Rectangle {
    private final Color color;
    byte[] pos;


    public Rectangle(double[] a, double[] b, double[] c, Color color) {
        this.color = color;
    }

  /*  public void update() {
        int screenWidth = RunTimeDefines.FRAME.getWidth();
        int screenHeight = RunTimeDefines.FRAME.getHeight();

        renderA = ContentHandler.camera.projectTo2D(a, screenWidth, screenHeight);
        renderB = ContentHandler.camera.projectTo2D(b, screenWidth, screenHeight);
        renderC = ContentHandler.camera.projectTo2D(c, screenWidth, screenHeight);
        renderD = ContentHandler.camera.projectTo2D(d, screenWidth, screenHeight);

        if (renderA == null && renderB == null && renderC == null && renderD == null) {
            renderA = renderB = renderC = renderD = new double[]{-1, -1};
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

   */

}