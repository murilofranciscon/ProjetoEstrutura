package br.mack.labirinto.core;

import br.mack.labirinto.model.Inventory;

public class Board {
    private char[][] grid;
    private Inventory inventory;
    private int score = 0;

    public Board(char[][] grid, int stackCapacity) {
        this.grid = grid;
        this.inventory = new Inventory(stackCapacity);
    }

    public void printBoard(Position player) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == player.getRow() && j == player.getCol()) {
                    System.out.print("P"); // jogador
                } else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("Inventário (topo): " + inventory.peekKey());
        System.out.println("Pontuação: " + score);
    }

    public Position findStart() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'S') {
                    return new Position(i, j);
                }
            }
        }
        return new Position(0, 0);
    }

    public Position move(Position player, int dRow, int dCol) {
        int newRow = player.getRow() + dRow;
        int newCol = player.getCol() + dCol;
        char target = grid[newRow][newCol];

        if (target == '#') {
            System.out.println(" Parede!");
            return player;
        }

        switch (target) {
            case '.': // piso
            case 'S': // início
                return new Position(newRow, newCol);

            case '$':
                score += 20;
                System.out.println("Tesouro encontrado! +20 pontos");
                grid[newRow][newCol] = '.'; // r
                return new Position(newRow, newCol);

            case 'T':
                score -= 20;
                System.out.println("☠️ Armadilha! -20 pontos");
                return new Position(newRow, newCol);

            case 'E':
                score += 100;
                System.out.println("Chegou à saída! Pontuação final: " + score);
                return new Position(newRow, newCol);

            default:
                if (Character.isLowerCase(target)) {
                    inventory.addKey(target);
                    System.out.println(" Pegou a chave " + target);
                    grid[newRow][newCol] = '.';
                    return new Position(newRow, newCol);
                }
                if (Character.isUpperCase(target)) {
                    char needed = Character.toLowerCase(target);
                    Character top = inventory.peekKey();
                    if (top != null && top == needed) {
                        inventory.useKey();
                        score += 15;
                        System.out.println("Abriu a porta " + target + " (+15 pontos)");
                        grid[newRow][newCol] = '.';
                        return new Position(newRow, newCol);
                    } else {
                        System.out.println(" Porta " + target + " bloqueada. Precisa da chave " + needed);
                        return player;
                    }
                }
        }
        return player;
    }

    public boolean isExit(Position pos) {
        return grid[pos.getRow()][pos.getCol()] == 'E';
    }

    public int getScore() {
        return score;
    }
}
