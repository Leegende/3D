package Content.Camera;

import Handlers.ContentHandler;

import java.awt.*;

public class Chunk {
    public static final int CHUNK_SIZE = 3; // Size of the chunk (32x32x32)
    private final Voxel[][][] voxels = new Voxel[CHUNK_SIZE][CHUNK_SIZE][CHUNK_SIZE]; // 3D array to store voxels
    private final double startX, startY, startZ; // Chunk position
    private final double voxelSize; // Size of each voxel

    public Chunk(double startX, double startY, double startZ, double voxelSize, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.voxelSize = voxelSize;
        generateChunk(color);
    }

    private void generateChunk(Color color) {
        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < CHUNK_SIZE; y++) {
                for (int z = 0; z < CHUNK_SIZE; z++) {
                    // Position of this voxel in the world
                    double voxelX = startX + (x * voxelSize);
                    double voxelY = startY + (y * voxelSize);
                    double voxelZ = startZ + (z * voxelSize);

                    Voxel voxel = new Voxel(voxelX, voxelY, voxelZ, voxelSize, color);

                    if (isFaceExposed(x, y, z, -1, 0, 0)) voxel.addFace(Voxel.Face.LEFT, color);   // Left
                    if (isFaceExposed(x, y, z, 1, 0, 0)) voxel.addFace(Voxel.Face.RIGHT, color);  // Right
                    if (isFaceExposed(x, y, z, 0, -1, 0)) voxel.addFace(Voxel.Face.DOWN, color);  // Down
                    if (isFaceExposed(x, y, z, 0, 1, 0)) voxel.addFace(Voxel.Face.UP, color);    // Up
                    if (isFaceExposed(x, y, z, 0, 0, -1)) voxel.addFace(Voxel.Face.BACK, color); // Back
                    if (isFaceExposed(x, y, z, 0, 0, 1)) voxel.addFace(Voxel.Face.FRONT, color); // Front

                    voxels[x][y][z] = voxel; // Save the voxel into the 3D array
                }
            }
        }
    }


    // Debugging output for `isFaceExposed`
    private boolean isFaceExposed(int x, int y, int z, int offsetX, int offsetY, int offsetZ) {
        int neighborX = x + offsetX;
        int neighborY = y + offsetY;
        int neighborZ = z + offsetZ;

        if (neighborX >= 0 && neighborX < CHUNK_SIZE &&
                neighborY >= 0 && neighborY < CHUNK_SIZE &&
                neighborZ >= 0 && neighborZ < CHUNK_SIZE) {
            boolean exposed = voxels[neighborX][neighborY][neighborZ] == null;

            // Debugging output
            if (!exposed) {
                System.out.printf("Voxel at (%d, %d, %d) face is NOT exposed due to neighbor at (%d, %d, %d) within chunk.%n",
                        x, y, z, neighborX, neighborY, neighborZ);
            }

            return exposed;
        }

        // Check adjacent chunks
        Chunk neighborChunk = ContentHandler.getNeighborChunk(this, neighborX, neighborY, neighborZ);
        if (neighborChunk != null) {
            int wrappedX = (neighborX + CHUNK_SIZE) % CHUNK_SIZE;
            int wrappedY = (neighborY + CHUNK_SIZE) % CHUNK_SIZE;
            int wrappedZ = (neighborZ + CHUNK_SIZE) % CHUNK_SIZE;
            boolean exposed = neighborChunk.getVoxel(wrappedX, wrappedY, wrappedZ) == null;

            // Debugging output
            if (!exposed) {
                System.out.printf("Voxel at (%d, %d, %d) face is NOT exposed due to neighbor in adjacent chunk at (%d, %d, %d).%n",
                        x, y, z, wrappedX, wrappedY, wrappedZ);
            }

            return exposed;
        }

        // No neighbor chunk exists, face is exposed
        System.out.printf("Voxel at (%d, %d, %d) face is exposed as no neighbor exists.%n", x, y, z);
        return true;
    }

    // Get a voxel at local coordinates (without bounds checking)
    public Voxel getVoxel(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || x >= CHUNK_SIZE || y >= CHUNK_SIZE || z >= CHUNK_SIZE) {
            return null; // Return null if out of bounds
        }
        return voxels[x][y][z];
    }

    // Getters for chunk position
    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getStartZ() {
        return startZ;
    }

    public void render(Graphics g) {
        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < CHUNK_SIZE; y++) {
                for (int z = 0; z < CHUNK_SIZE; z++) {
                    Voxel voxel = voxels[x][y][z];
                    if (voxel != null) {
                        voxel.render(g);
                    }
                }
            }
        }
    }

    public int getVoxelSize() {
        return 100;
    }
}