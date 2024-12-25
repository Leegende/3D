package Handlers;

import Defines.RunTimeDefines;

import java.awt.*;
import java.awt.event.*;

public class InputHandler implements KeyListener, MouseWheelListener, MouseListener, MouseMotionListener, FocusListener, WindowStateListener {

    private final Robot robot;
    private int centerX;
    private int centerY;
    private final boolean[] movementFlags = new boolean[4];
    private boolean spacePressed = false; // Track if Space is pressed
    private boolean shiftPressed = false; // Track if Shift is pressed
    private boolean inputsEnabled = true; // Flag to enable/disable inputs

    public InputHandler() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        // Add listeners to the game's JFrame from RunTimeDefines
        RunTimeDefines.FRAME.addFocusListener(this);
        RunTimeDefines.FRAME.addWindowStateListener(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!inputsEnabled) return; // Ignore input if disabled
        handleMouseMovement(e);
    }

    private void handleMouseMovement(MouseEvent e) {
        int deltaX = e.getX() - centerX;
        int deltaY = e.getY() - centerY;
        final double sensitivity = 0.1;

        ContentHandler.camera.yaw = (ContentHandler.camera.yaw - deltaX * sensitivity) % 360;
        if (ContentHandler.camera.yaw < 0) {
            ContentHandler.camera.yaw += 360;
        }

        ContentHandler.camera.pitch -= deltaY * sensitivity;
        ContentHandler.camera.pitch = Math.max(-90, Math.min(90, ContentHandler.camera.pitch));

        robot.mouseMove(centerX, centerY);
    }

    public void setCenter(int x, int y) {
        this.centerX = x;
        this.centerY = y;
        robot.mouseMove(centerX, centerY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!inputsEnabled) return; // Ignore input if disabled

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> movementFlags[0] = true;
            case KeyEvent.VK_S -> movementFlags[1] = true;
            case KeyEvent.VK_A -> movementFlags[2] = true;
            case KeyEvent.VK_D -> movementFlags[3] = true;
            case KeyEvent.VK_SPACE -> spacePressed = true; // Space bar for upward movement
            case KeyEvent.VK_SHIFT -> shiftPressed = true; // Shift key for downward movement
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!inputsEnabled) return; // Ignore input if disabled

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> movementFlags[0] = false;
            case KeyEvent.VK_S -> movementFlags[1] = false;
            case KeyEvent.VK_A -> movementFlags[2] = false;
            case KeyEvent.VK_D -> movementFlags[3] = false;
            case KeyEvent.VK_SPACE -> spacePressed = false; // Reset Space bar flag
            case KeyEvent.VK_SHIFT -> shiftPressed = false; // Reset Shift key flag
        }
    }

    public void updateMovement() {
        if (!inputsEnabled) return; // Ignore updates if inputs are disabled

        final double moveSpeed = 0.5; // Movement speed
        final double verticalSpeed = 0.5; // Speed for vertical movement
        final double yawRadians = Math.toRadians(ContentHandler.camera.yaw);

        final double sinYaw = Math.sin(yawRadians);
        final double cosYaw = Math.cos(yawRadians);

        final double rightZ = -cosYaw;

        // Horizontal movement (W, A, S, D)
        if (movementFlags[0]) {
            ContentHandler.camera.position.x -= sinYaw * moveSpeed;
            ContentHandler.camera.position.z -= rightZ * moveSpeed;
        }
        if (movementFlags[1]) {
            ContentHandler.camera.position.x += sinYaw * moveSpeed;
            ContentHandler.camera.position.z += rightZ * moveSpeed;
        }
        if (movementFlags[2]) {
            ContentHandler.camera.position.x -= cosYaw * moveSpeed;
            ContentHandler.camera.position.z -= sinYaw * moveSpeed;
        }
        if (movementFlags[3]) {
            ContentHandler.camera.position.x += cosYaw * moveSpeed;
            ContentHandler.camera.position.z += sinYaw * moveSpeed;
        }

        // Vertical movement (Space and Shift)
        if (spacePressed) {
            ContentHandler.camera.position.y += verticalSpeed; // Move up
        }
        if (shiftPressed) {
            ContentHandler.camera.position.y -= verticalSpeed; // Move down
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Resume inputs when the window regains focus
        System.out.println("Game regained focus.");
        inputsEnabled = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Pause inputs when the window loses focus
        System.out.println("Game lost focus.");
        inputsEnabled = false;
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        // Detect if the window has been minimized
        if (e.getNewState() == Frame.ICONIFIED) {
            System.out.println("Game window minimized.");
            inputsEnabled = false;
        } else if (e.getNewState() == Frame.NORMAL || e.getNewState() == Frame.MAXIMIZED_BOTH) {
            System.out.println("Game window restored.");
            inputsEnabled = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!inputsEnabled) return; // Ignore input if disabled
        handleMouseMovement(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (!inputsEnabled) return; // Ignore input if disabled
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}