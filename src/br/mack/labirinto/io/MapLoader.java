package br.mack.labirinto.io;

import br.mack.labirinto.core.Board;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MapLoader {
    public static Board load(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));

        String[] header = lines.get(0).split(" ");
        int rows = Integer.parseInt(header[0]);
        int cols = Integer.parseInt(header[1]);
        int capacity = Integer.parseInt(header[2]);

        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i + 1).toCharArray();
        }

        return new Board(grid, capacity);
    }
}
