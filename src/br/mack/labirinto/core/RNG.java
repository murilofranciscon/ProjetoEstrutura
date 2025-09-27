package br.mack.labirinto.core;

import java.util.Random;

public class RNG {
    private final long seed;
    public RNG(long seed) { this.seed = seed; }

    // valor no intervalo [10,50], estável para (row,col,seed)
    public int treasureValue(int row, int col) {
        long mix = seed ^ (((long)row) * 73856093L) ^ (((long)col) * 19349663L);
        Random r = new Random(mix);
        return 10 + r.nextInt(41);
    }
}
