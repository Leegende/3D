package Handlers;

import Content.Camera.Chunk;

import java.awt.*;

public class InitializeHandler {

    public static void init() {
        final int numberOfChunks = 1;
        final double chunkStartX = 0;
        final double chunkStartY = 0;
        final double chunkStartZ = 0;
        final double voxelSize = 50;

        for (int x = 0; x < numberOfChunks; x++) {
            for (int y = 0; y < numberOfChunks; y++) {
                for (int z = 0; z < numberOfChunks; z++) {
                    double chunkX = chunkStartX + x * voxelSize ;
                    double chunkY = chunkStartY + y * voxelSize ;
                    double chunkZ = chunkStartZ + z * voxelSize ;
                    ContentHandler.chunks.add(new Chunk(chunkX, chunkY, chunkZ, voxelSize, Color.RED));
                }
            }
        }
    }
}