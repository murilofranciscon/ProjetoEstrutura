package br.mack.labirinto.model;

import br.mack.labirinto.util.Sorts;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Scoreboard {
    private ScoreEntry[] entries = new ScoreEntry[64];
    private int count = 0;

    public static Scoreboard loadOrCreate(String path) throws IOException {
        Scoreboard sb = new Scoreboard();
        Path p = Path.of(path);
        if (!Files.exists(p)) { Files.createFile(p); return sb; }
        for (String line : Files.readAllLines(p, StandardCharsets.UTF_8)) {
            String t = line.trim(); if (t.isEmpty()) continue;
            int i = t.lastIndexOf(',');
            if (i <= 0) continue;
            String name = t.substring(0, i);
            int sc = Integer.parseInt(t.substring(i+1));
            sb.add(new ScoreEntry(name, sc));
        }
        return sb;
    }

    public void save(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(entries[i].getPlayer()).append(',').append(entries[i].getScore()).append('\n');
        }
        Files.writeString(Path.of(path), sb.toString(), StandardCharsets.UTF_8);
    }

    public void add(ScoreEntry entry) {
        if (count == entries.length) {
            ScoreEntry[] b = new ScoreEntry[entries.length * 2];
            System.arraycopy(entries, 0, b, 0, count);
            entries = b;
        }
        entries[count++] = entry;
    }

    public void sortByScoreDesc() {
        if (count < 30) Sorts.insertionSortScoreDesc(entries, count);
        else Sorts.quickSortScoreDesc(entries, 0, count-1);
    }

    public int size() { return count; }
    public ScoreEntry get(int i) { return entries[i]; }
}
