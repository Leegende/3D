package Content.Movement;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.awt.event.KeyEvent.*;

public enum MovementKeyMap {
    UP(new int[]{VK_W, VK_UP}, new Vector(0, -1)),
    DOWN(new int[]{VK_S, VK_DOWN}, new Vector(0, 1)),
    LEFT(new int[]{VK_A, VK_LEFT}, new Vector(-1, 0)),
    RIGHT(new int[]{VK_D, VK_RIGHT}, new Vector(1, 0));

    private final int[] keyEvents;
    private final Vector vector;

    MovementKeyMap(int[] keyEvents, Vector vector) {
        this.keyEvents = keyEvents;
        this.vector = vector;
    }

   public static Vector getVectorForKey(int keyCode) {
        return Stream.of(values())
                .filter(movement -> Arrays.stream(movement.keyEvents).anyMatch(code -> code == keyCode))
                .map(movement -> movement.vector)
                .findFirst()
                .orElse(Vector.ZERO);
    }
}
