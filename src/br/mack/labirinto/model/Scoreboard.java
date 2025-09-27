package br.mack.labirinto.model;

public class Scoreboard {
    private ScoreEntry[] entries;
    private int count = 0;

    public Scoreboard(int capacity) {
        entries = new ScoreEntry[capacity];
    }

    public void add(ScoreEntry entry) {
        if (count < entries.length) {
            entries[count++] = entry;
        }
    }

    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println(entries[i]);
        }
    }
}
