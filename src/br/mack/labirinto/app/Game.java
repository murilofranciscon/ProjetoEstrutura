package br.mack.labirinto.app;

import br.mack.labirinto.core.Board;
import br.mack.labirinto.io.MapLoader;
import br.mack.labirinto.io.CLI;
import br.mack.labirinto.core.Position;

public class Game {
    public static void main(String[] args) {
        String mapPath = "./mapas/map1.txt"; // valor padrão

        // Ler argumento --map=...
        for (String arg : args) {
            if (arg.startsWith("--map=")) {
                mapPath = arg.substring("--map=".length());
            }
        }

        try {
            // 1) Carregar tabuleiro
            Board board = MapLoader.load(mapPath);

            // 2) Criar CLI para comandos
            CLI cli = new CLI();

            // 3) Encontrar posição inicial (S)
            Position player = board.findStart();

            // 4) Loop de jogo
            boolean running = true;
            while (running) {
                board.printBoard(player); // mostrar tabuleiro com jogador
                String cmd = cli.nextCommand().toUpperCase();

                switch (cmd) {
                    case "W": player = board.move(player, -1, 0); break; // cima
                    case "S": player = board.move(player, 1, 0); break;  // baixo
                    case "A": player = board.move(player, 0, -1); break; // esquerda
                    case "D": player = board.move(player, 0, 1); break;  // direita
                    case "Q": running = false; break; // sair
                    default: System.out.println("Comando inválido!"); break;
                }

                if (board.isExit(player)) {
                    board.printBoard(player);
                    System.out.println(" Você saiu do labirinto!");
                    running = false;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao carregar o mapa: " + e.getMessage());
        }
    }
}
