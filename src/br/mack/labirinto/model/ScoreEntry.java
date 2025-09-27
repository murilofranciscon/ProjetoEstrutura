package br.mack.labirinto.model;

public class ScoreEntry {
    private String player;
    private int score;

    public ScoreEntry(String player, int score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() { return player; }
    public int getScore() { return score; }

    @Override
    public String toString() {
        return player + " - " + score;
    }
}
