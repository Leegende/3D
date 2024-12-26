package Handlers;

import Content.Camera.Camera;
import Content.Camera.Chunk;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContentHandler {
    public static CopyOnWriteArrayList<Chunk> chunks = new CopyOnWriteArrayList<>();
    public static Camera camera = new Camera();

    public static void render(Graphics g) {
        camera.render(g);
    }

    public static void update() {
        camera.update();
    }

    public static Chunk getNeighborChunk(Chunk currentChunk, int x, int y, int z) {
        // Determine the world position of the neighboring chunk
        double chunkStartX = currentChunk.getStartX();
        double chunkStartY = currentChunk.getStartY();
        double chunkStartZ = currentChunk.getStartZ();

        // Adjust the starting coordinates based on out-of-bounds conditions
        if (x < 0) chunkStartX -= Chunk.CHUNK_SIZE * currentChunk.getVoxelSize();
        if (x >= Chunk.CHUNK_SIZE) chunkStartX += Chunk.CHUNK_SIZE * currentChunk.getVoxelSize();
        if (y < 0) chunkStartY -= Chunk.CHUNK_SIZE * currentChunk.getVoxelSize();
        if (y >= Chunk.CHUNK_SIZE) chunkStartY += Chunk.CHUNK_SIZE * currentChunk.getVoxelSize();
        if (z < 0) chunkStartZ -= Chunk.CHUNK_SIZE * currentChunk.getVoxelSize();
        if (z >= Chunk.CHUNK_SIZE) chunkStartZ += Chunk.CHUNK_SIZE * currentChunk.getVoxelSize();

        // Search for the chunk at the specified world coordinates
        for (Chunk chunk : chunks) {
            if (chunk.getStartX() == chunkStartX &&
                    chunk.getStartY() == chunkStartY &&
                    chunk.getStartZ() == chunkStartZ) {
                return chunk;
            }
        }
        return null; // No neighboring chunk found
    }
}