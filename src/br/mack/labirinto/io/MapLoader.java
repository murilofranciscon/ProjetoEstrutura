package br.mack.labirinto.io;

import br.mack.labirinto.core.Board;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    public static Board load(String path) throws IOException {
        List<String> raw = Files.readAllLines(Path.of(path));
        List<String> lines = new ArrayList<>();
        for (String l : raw) {
            String t = l.trim();
            if (t.isEmpty() || t.startsWith(";")) continue; // ignora comentários e vazias
            lines.add(l);
        }
        if (lines.isEmpty()) throw new IOException("Mapa vazio");

        String[] header = lines.get(0).trim().split("\\s+");
        if (header.length < 3) throw new IOException("Cabeçalho inválido. Esperado: L C CAP");
        int rows = Integer.parseInt(header[0]);
        int cols = Integer.parseInt(header[1]);
        int capacity = Integer.parseInt(header[2]);

        if (lines.size() < rows + 1) throw new IOException("Linhas de tabuleiro insuficientes");

        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String row = lines.get(i + 1);
            if (row.length() < cols) throw new IOException("Linha "+i+" com colunas insuficientes");
            for (int j = 0; j < cols; j++) grid[i][j] = row.charAt(j);
        }

        return new Board(grid, capacity);
    }
}
