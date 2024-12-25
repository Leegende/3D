package Handlers.RunTimeHandler;

import Defines.RunTimeDefines;
import Handlers.ContentHandler;

public final class UpdateHandler implements Runnable {
    private static long lastUpdateTime = System.nanoTime();

    public void run() {
        while (true) {
            if (System.nanoTime() - lastUpdateTime >= 1_000_000_000 / RunTimeDefines.UPS_SET) {
                update();
                lastUpdateTime = System.nanoTime();
            }
        }
    }

    private static void update() {
        ContentHandler.update();
        RunTimeDefines.INPUT_HANDLER.updateMovement(); // Call movement update here
    }
}