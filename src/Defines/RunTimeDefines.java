package Defines;

import Handlers.InputHandler;
import Handlers.RunTimeHandler.RenderHandler;
import Handlers.RunTimeHandler.UpdateHandler;

import javax.swing.*;
import java.awt.*;

public final class RunTimeDefines {
    public static final JFrame FRAME = new JFrame();
    public static final short UPS_SET = 128;

    public static final InputHandler INPUT_HANDLER = new InputHandler();
    public static final RenderHandler RENDER_HANDLER = new RenderHandler();

    private RunTimeDefines() {
    }

    public static void start() {
        startThread(new UpdateHandler());
        startThread(RENDER_HANDLER);
        initFrames();
    }

    private static void startThread(Runnable handler) {
        Thread thread = new Thread(handler);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private static void initFrames() {
        FRAME.setUndecorated(true);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.add(RENDER_HANDLER);
        FRAME.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        FRAME.addKeyListener(INPUT_HANDLER);
        FRAME.addMouseListener(INPUT_HANDLER);
        FRAME.addMouseMotionListener(INPUT_HANDLER);
        FRAME.addMouseWheelListener(INPUT_HANDLER);
        FRAME.setResizable(false);
        FRAME.addFocusListener(INPUT_HANDLER);
        FRAME.setFocusable(true);
        FRAME.requestFocus();
        FRAME.setIgnoreRepaint(false);
        FRAME.setVisible(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.createImage(new byte[0]);
        Cursor blankCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "blank cursor");

        FRAME.setCursor(blankCursor);

        Dimension screenSize = FRAME.getSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        INPUT_HANDLER.setCenter(centerX, centerY);
    }
}