package br.mack.labirinto.core;

import java.util.Random;

public class RNG {
    private Random random;

    public RNG(long seed) {
        this.random = new Random(seed);
    }

    public int nextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
}
}