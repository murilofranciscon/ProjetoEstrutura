package br.mack.labirinto.core;

import br.mack.labirinto.model.Inventory;
import br.mack.labirinto.ds.SinglyLinkedList;

public class Board {
    private final char[][] grid;
    private final Inventory inventory;
    private int score = 0;
    private RNG rng; // para tesouro 10..50 com seed+posição
    private final SinglyLinkedList<String> log = new SinglyLinkedList<>();

    public Board(char[][] grid, int stackCapacity) {
        this.grid = grid;
        this.inventory = new Inventory(stackCapacity);
    }

    public void setRng(RNG rng) { this.rng = rng; }

    public void printBoard(Position player) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print((i == player.getRow() && j == player.getCol()) ? 'P' : grid[i][j]);
            }
            System.out.println();
        }
        System.out.println("Inventário (topo→base): " + inventory.asStringTopToBase());
        System.out.println("Pontuação: " + score);
    }

    public Position findStart() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == 'S') return new Position(i, j);
        return new Position(0, 0);
    }

    public Position move(Position player, int dRow, int dCol) {
        int nr = player.getRow() + dRow;
        int nc = player.getCol() + dCol;

        // bounds check
        if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length) {
            System.out.println("Parede (borda)!");
            return player;
        }

        char target = grid[nr][nc];
        if (target == '#') {
            System.out.println("Parede!");
            return player;
        }

        // Passo custa -1 (apenas em movimento válido)
        score -= 1;

        switch (target) {
            case '.': // piso
            case 'S': // início
                return new Position(nr, nc);

            case '$': {
                int val = (rng != null) ? rng.treasureValue(nr, nc) : 20; // fallback
                score += val;
                log.add("Tesouro em ("+nr+","+nc+"): +"+val);
                grid[nr][nc] = '.';
                return new Position(nr, nc);
            }

            case 'T': {
                score -= 20;
                log.add("Armadilha em ("+nr+","+nc+"): -20");
                // (opção) consumir para não penalizar de novo:
                grid[nr][nc] = '.';
                return new Position(nr, nc);
            }

            case 'E': {
                score += 100;
                log.add("Saída alcançada: +100");
                return new Position(nr, nc);
            }

            default:
                if (Character.isLowerCase(target)) {
                    if (!inventory.addKey(target)) {
                        System.out.println("Inventário cheio. Chave '"+target+"' não adicionada.");
                        // ainda assim pode entrar no tile: consumir para não tentar pegar sempre
                    } else {
                        System.out.println("Pegou a chave '"+target+"'.");
                    }
                    grid[nr][nc] = '.';
                    return new Position(nr, nc);
                }
                if (Character.isUpperCase(target)) {
                    char need = Character.toLowerCase(target);
                    Character top = inventory.peekKey();
                    if (top != null && top == need) {
                        inventory.useKey();
                        score += 15;
                        log.add("Abriu porta '"+target+"' com '"+need+"': +15");
                        grid[nr][nc] = '.';
                        return new Position(nr, nc);
                    } else {
                        System.out.println("Porta '"+target+"' bloqueada. Precisa da chave '"+need+"' no topo.");
                        return player;
                    }
                }
        }
        return player;
    }

    public boolean isExit(Position pos) { return grid[pos.getRow()][pos.getCol()] == 'E'; }
    public int getScore() { return score; }

    public void applyLeftoverKeysBonus() {
        int bonus = inventory.size() * 5;
        if (bonus > 0) {
            score += bonus;
            log.add("Bônus chaves não usadas: +" + bonus);
        }
    }

    public void printLog() {
        System.out.println("\nLog de eventos:");
        log.printAll();
    }
}
