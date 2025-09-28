//Beatriz Soares RA:10443916 Mariana Chiorboli RA:10435657 Murilo Franciscon RA:10438787
package br.mack.labirinto.app;

import java.io.File;
import java.util.Scanner;
import br.mack.labirinto.core.Board;
import br.mack.labirinto.io.MapLoader;
import br.mack.labirinto.io.CLI;
import br.mack.labirinto.core.Position;
import br.mack.labirinto.core.RNG;
import br.mack.labirinto.model.Scoreboard;
import br.mack.labirinto.model.ScoreEntry;

public class Game {
    public static void main(String[] args) {
        String mapPath = null;
        Long seed = null;
        String player = "Player";

        for (String arg : args) {
            if (arg.startsWith("--map="))    mapPath = arg.substring("--map=".length());
            else if (arg.startsWith("--seed="))  seed = Long.parseLong(arg.substring("--seed=".length()));
            else if (arg.startsWith("--player=")) player = arg.substring("--player=".length());
        }

        // Seleção de mapa se não veio por --map
        if (mapPath == null) {
            File mapasDir = new File("./mapas");
            String[] mapas = (mapasDir.exists() ? mapasDir.list((dir, name) -> name.endsWith(".txt")) : null);
            if (mapas == null || mapas.length == 0) {
                System.out.println("Nenhum mapa encontrado em ./mapas");
                return;
            }
            System.out.println("Mapas disponíveis:");
            for (int i = 0; i < mapas.length; i++) System.out.println((i+1) + ": " + mapas[i]);
            System.out.print("Escolha o número do mapa: ");
            Scanner in = new Scanner(System.in);
            int escolha = in.hasNextInt()? in.nextInt() : 1;
            if (escolha < 1 || escolha > mapas.length) {
                System.out.println("Escolha inválida. Usando o primeiro mapa.");
                escolha = 1;
            }
            mapPath = "./mapas/" + mapas[escolha-1];
        }

        // Seed padrão se não informada
        if (seed == null) seed = System.currentTimeMillis();

        try {
            RNG rng = new RNG(seed);
            Board board = MapLoader.load(mapPath); // lê grid e capacidade
            board.setRng(rng);                      // <- para tesouros 10..50 (seed+posição)

            CLI cli = new CLI();
            Position playerPos = board.findStart();

            boolean running = true;
            while (running) {
                board.printBoard(playerPos); 
                String cmd = cli.nextCommand().trim().toUpperCase();
                if (cmd.isEmpty()) continue;

                switch (cmd) {
                    case "W": playerPos = board.move(playerPos, -1, 0); break; 
                    case "S": playerPos = board.move(playerPos,  1, 0); break;
                    case "A": playerPos = board.move(playerPos,  0,-1); break; 
                    case "D": playerPos = board.move(playerPos,  0, 1); break;  
                    case "Q": running = false; continue; 
                    default: System.out.println("Comando inválido!"); continue;
                }

                if (board.isExit(playerPos)) {
                    // bônus +5 por chave sobrando
                    board.applyLeftoverKeysBonus();
                    board.printBoard(playerPos);
                    System.out.println("Você saiu do labirinto!");
                    running = false;
                }
            }

            // Persistir e mostrar ranking Top 10
            Scoreboard sb = Scoreboard.loadOrCreate("ranking.csv");
            sb.add(new ScoreEntry(player, board.getScore()));
            sb.sortByScoreDesc();
            sb.save("ranking.csv");
            System.out.println("\nTop 10:");
            for (int i = 0; i < Math.min(10, sb.size()); i++) {
                ScoreEntry e = sb.get(i);
                System.out.printf("%2d) %-16s %6d%n", (i+1), e.getPlayer(), e.getScore());
            }

            // (Opcional) imprimir o log (lista encadeada)
            board.printLog();

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
